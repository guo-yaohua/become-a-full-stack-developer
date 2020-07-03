package com.gyh.mall.controller;

import com.gyh.mall.utils.HttpUtils;
import com.google.gson.Gson;
import com.gyh.mall.model.Admin;
import com.gyh.mall.model.Result;
import com.gyh.mall.model.bo.AdminLoginBO;
import com.gyh.mall.model.vo.AdminLoginVo;
import com.gyh.mall.service.AdminService;
import com.gyh.mall.service.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/admin/admin/*")
public class AdminServlet extends HttpServlet {

    private AdminService adminService = new AdminServiceImpl();

    Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 解析请求
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/admin/", "");

        // 登录
        if ("login".equals(action)) {
            login(request, response);
        }
    }

    /**
     * 管理员登录逻辑
     * 1. 浏览器向 8084 发送请求，请求体中携带了用户名密码参数（json 字符串形式）
     * 2. 查询数据库，检验当前用户名密码是否正确
     * 3. 根据密码返回不同的响应
     * @param request
     * @param response
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 读取请求体，返回 json 字符串
        String requestBody = HttpUtils.getRequestBody(request);

        // json 转 Java 对象
        AdminLoginBO loginBO = gson.fromJson(requestBody, AdminLoginBO.class);

        Admin login = adminService.login(loginBO);

        if (login != null) {
            AdminLoginVo loginVo = new AdminLoginVo();
            loginVo.setToken(login.getNickname());
            loginVo.setName(login.getNickname());
            response.getWriter().println(gson.toJson(Result.ok(loginVo)));
        } else {
            response.getWriter().println(Result.error("用户名或者密码错误"));
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 解析请求
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/admin/", "");

        // 获取所有管理员信息
        if ("allAdmins".equals(action)) {
            allAdmins(request, response);
        }
    }

    /**
     * 显示所有的 admin 信息
     * 1. 查询数据库，返回数据
     * 2. 做出响应
     * @param request
     * @param response
     */
    private void allAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Admin> adminList = adminService.allAdmins();
        Result result = new Result();
        result.setCode(0);
        result.setData(adminList);
        response.getWriter().println(gson.toJson(result));
    }
}
