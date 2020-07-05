package com.gyh.mall.controller.mall;

import com.google.gson.Gson;
import com.gyh.mall.model.Result;
import com.gyh.mall.model.bo.mall.MallUserSignupBo;
import com.gyh.mall.model.vo.mall.MallUserSignupVo;
import com.gyh.mall.service.mall.UserService;
import com.gyh.mall.service.mall.UserServiceImpl;
import com.gyh.mall.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/mall/user/*")
public class UserServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 解析请求
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/user/", "");

        // action
        if ("signup".equals(action)) {  // 注册
            signup(request, response);
        }
    }

    /**
     * 用户注册
     * @param request
     * @param response
     */
    private void signup(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 读取请求体，返回 json 字符串
        String requestBody = HttpUtils.getRequestBody(request);

        MallUserSignupBo signupBo = gson.fromJson(requestBody, MallUserSignupBo.class);

        int code = userService.signup(signupBo);

        if (code == 0) {
            response.getWriter().println(Result.error("注册失败"));
        } else {
            MallUserSignupVo signupVo = new MallUserSignupVo();
            signupVo.setName(signupBo.getNickname());
            signupVo.setNickname(signupBo.getNickname());
            response.getWriter().println(gson.toJson(Result.ok(signupVo)));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
