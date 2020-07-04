package com.gyh.mall.controller;

import com.gyh.mall.model.bo.AdminAddBo;
import com.gyh.mall.model.bo.AdminUpdateBo;
import com.gyh.mall.utils.HttpUtils;
import com.google.gson.Gson;
import com.gyh.mall.model.Admin;
import com.gyh.mall.model.Result;
import com.gyh.mall.model.bo.AdminLoginBo;
import com.gyh.mall.model.vo.AdminLoginVo;
import com.gyh.mall.service.AdminService;
import com.gyh.mall.service.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        // 添加管理员
        if ("addAdminss".equals(action)) {
            addAdminss(request, response);
        }

        // 修改管理员信息
        if ("updateAdminss".equals(action)) {
            updateAdminss(request, response);
        }
    }

    /**
     * 修改管理员信息
     * 1. 获取管理员信息
     * 2. 修改管理员信息
     * @param request
     * @param response
     */
    private void updateAdminss(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 读取请求体，返回 json 字符串
        String requestBody = HttpUtils.getRequestBody(request);

        // json 转 Java 对象
        AdminUpdateBo updateBo = gson.fromJson(requestBody, AdminUpdateBo.class);

        int code = adminService.updateAdminss(updateBo);
        if (code == 0) {
            response.getWriter().println(Result.error("修改失败"));
        } else {
            response.getWriter().println(gson.toJson(Result.ok(updateBo)));
        }

    }

    /**
     * 添加管理员
     * @param request
     * @param response
     */
    private void addAdminss(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 读取请求体，返回 json 字符串
        String requestBody = HttpUtils.getRequestBody(request);

        // json 转 Java 对象
        AdminAddBo addBo = gson.fromJson(requestBody, AdminAddBo.class);

        int code = adminService.addAdminss(addBo);
        if (code == 0) {
            response.getWriter().println(Result.error("添加失败"));
        } else {
            response.getWriter().println(gson.toJson(Result.ok(addBo)));
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
        AdminLoginBo loginBo = gson.fromJson(requestBody, AdminLoginBo.class);

        Admin login = adminService.login(loginBo);

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

        // 删除管理员
        if ("deleteAdmins".equals(action)) {
            deleteAdmins(request, response);
        }

        // 获取管理员信息
        if ("getAdminsInfo".equals(action)) {
            getAdminsInfo(request, response);
        }
    }

    /**
     * 获取管理员信息
     * @param request
     * @param response
     */
    private void getAdminsInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Admin admin = adminService.getAdminsInfo(id);

        if (admin != null) {
            response.getWriter().println(gson.toJson(Result.ok(admin)));
        } else {
            response.getWriter().println(Result.error("获取失败"));
        }
    }

    /**
     * 删除管理员
     * @param request
     * @param response
     */
    private void deleteAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Admin admin = adminService.deleteAdmins(id);
        
        Result result = new Result();
        result.setCode(0);
        result.setData(admin);
        response.getWriter().println(gson.toJson(result));
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
