package com.gyh.mall.controller;

import com.google.gson.Gson;
import com.gyh.mall.model.Admin;
import com.gyh.mall.model.Result;
import com.gyh.mall.model.User;
import com.gyh.mall.service.AdminUserService;
import com.gyh.mall.service.AdminUserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/admin/user/*")
public class AdminUserServlet extends HttpServlet {

    private AdminUserService adminUserService = new AdminUserServiceImpl();
    Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 解析请求
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/user/", "");
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 解析请求
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/user/", "");

        // action
        if ("allUser".equals(action)) {  // 获取所有用户
            allUser(request, response);
        } else if ("deleteUser".equals(action)) {   // 删除指定用户
            deleteUser(request, response);
        }
    }

    /**
     * 删除指定用户
     * @param request
     * @param response
     */
    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        adminUserService.deleteUser(id);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    /**
     * 获取所有用户
     * @param request
     * @param response
     */
    private void allUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<User> users = adminUserService.allUser();
        response.getWriter().println(gson.toJson(Result.ok(users)));
    }
}
