package com.gyh.mall.controller.admin;

import com.google.gson.Gson;
import com.gyh.mall.model.Result;
import com.gyh.mall.model.User;
import com.gyh.mall.service.admin.UserService;
import com.gyh.mall.service.admin.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/admin/user/*")
public class UserServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    private Gson gson = new Gson();

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
        } else if ("searchUser".equals(action)) {   // 搜索用户
            searchUser(request, response);
        }
    }

    /**
     * 搜索用户
     * @param request
     * @param response
     */
    private void searchUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String word = request.getParameter("word");

        List<User> users = userService.searchUser(word);

        response.getWriter().println(gson.toJson(Result.ok(users)));
    }

    /**
     * 删除指定用户
     * @param request
     * @param response
     */
    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        userService.deleteUser(id);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    /**
     * 获取所有用户
     * @param request
     * @param response
     */
    private void allUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<User> users = userService.allUser();
        response.getWriter().println(gson.toJson(Result.ok(users)));
    }
}
