package com.gyh.project1.controller;

import com.google.gson.Gson;
import com.gyh.project1.model.bo.AdminLoginBO;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@WebServlet("/api/admin/admin/*")
public class AdminServlet extends HttpServlet {

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
        // 取出请求参数
        ServletInputStream inputStream = request.getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte[] bytes = new byte[1024];
        int length = 0;
        while ((length = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0 , length);
        }

        // json 字符串 -> Java 对象
        String requestBody = outputStream.toString("utf-8");
        AdminLoginBO loginBO = gson.fromJson(requestBody, AdminLoginBO.class);



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
