package com.gyh.mall.controller.mall;

import com.google.gson.Gson;
import com.gyh.mall.model.Result;
import com.gyh.mall.model.bo.mall.UserSignupBO;
import com.gyh.mall.model.bo.mall.UserLoginBO;
import com.gyh.mall.model.vo.mall.UserLoginVO;
import com.gyh.mall.model.vo.mall.UserSignupVO;
import com.gyh.mall.service.admin.GoodsService;
import com.gyh.mall.service.admin.GoodsServiceImpl;
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

    private GoodsService goodsService = new GoodsServiceImpl();

    Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 解析请求
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/user/", "");

        // action
        if ("signup".equals(action)) {  // 注册
            signup(request, response);
        } else if ("login".equals(action)) {    // 用户登录
            login(request, response);
        }
    }

    /**
     * 用户登录
     * @param request
     * @param response
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);

        UserLoginBO loginBO = gson.fromJson(requestBody, UserLoginBO.class);

        UserLoginVO loginVO = userService.login(loginBO);

        if (loginVO == null) {
            response.getWriter().println(gson.toJson(Result.error("用户名密码错误")));
        } else {
            // 设置权限
            // request.getSession().setAttribute("user", loginVO);

            response.getWriter().println(gson.toJson(Result.ok(loginVO)));
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

        UserSignupBO signupBo = gson.fromJson(requestBody, UserSignupBO.class);

        int code = userService.signup(signupBo);

        if (code == 0) {
            response.getWriter().println(Result.error("注册失败"));
        } else {
            UserSignupVO signupVO = new UserSignupVO();
            signupVO.setName(signupBo.getNickname());
            signupVO.setNickname(signupBo.getNickname());
            response.getWriter().println(gson.toJson(Result.ok(signupVO)));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 解析请求
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/user/", "");

    }
}
