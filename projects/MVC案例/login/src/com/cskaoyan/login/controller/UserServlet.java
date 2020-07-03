package com.cskaoyan.login.controller;

import com.cskaoyan.login.model.User;
import com.cskaoyan.login.service.UserService;
import com.cskaoyan.login.service.UserServiceImpl;
import com.cskaoyan.login.utils.UserStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getParameter("op");
        if("register".equals(op)){
            register(request, response);
        }else if("login".equals(op)){
            login(request, response);
        }


        //
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //校验
        //封装
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        int code = userService.login(user);
        if(code == UserStatus.INCORRECT_NAME_OR_PASSWORD){
            response.getWriter().println("用户名或者密码错误");
            return;
        }
        response.getWriter().println("登录成功，即将跳转至个人主页");
        request.getSession().setAttribute("username", user.getUsername());
        response.setHeader("refresh","2;url=" + request.getContextPath() + "/info.jsp");
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //校验
        //封装
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        //真正去注册  校验用户名 注册
        // controller调用service，service调用dao
        // se多态
        int code = userService.register(user);
        if(code == UserStatus.EXISTS){
            response.getWriter().println("当前用户名已存在，请更换用户名");
            return;
        }
        response.getWriter().println("注册成功，即将跳转至登录页");
        //调用视图

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getParameter("op");
        if("query".equals(op)){
            query(request, response);
        }else if("logout".equals(op)){
            logout(request, response);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.getWriter().println("注销成功，跳转回登录页");
        response.setHeader("refresh","2;url=" + request.getContextPath() + "/login.jsp");
    }

    private void query(HttpServletRequest request, HttpServletResponse response) {

    }
}
