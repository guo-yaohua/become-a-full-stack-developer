package com.gyh.mall.filter;

import com.google.gson.Gson;
import com.gyh.mall.model.Admin;
import com.gyh.mall.model.Result;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/api/mall/*")
public class MallFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        // 字符
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        // 跨域
        response.setHeader("Access-Control-Allow-Origin","http://localhost:8085");
        response.setHeader("Access-Control-Allow-Methods","POST,GET,OPTIONS,PUT,DELETE");
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,Authorization,Content-Type");
        response.setHeader("Access-Control-Allow-Credentials","true");

        // 权限管理
        String requestURI = request.getRequestURI();
        /*
            1. 需要拦截哪些
            2. 针对需要拦截的，判断有无 session 数据
            3. 如果没用，则拦截
         */
//        if (!request.getMethod().equals("OPTIONS")) {
//            if (auth(requestURI)) {
//                Admin admin = (Admin) request.getSession().getAttribute("admin");
//                if (admin == null) {
//                    response.getWriter().println(new Gson().toJson(Result.error("当前接口仅登陆后可以访问")));
//                    return;
//                }
//            }
//        }

        chain.doFilter(request, response);
    }

    /**
     * 当前请求是否需要权限
     * @param requestURI
     * @return
     */
    private boolean auth(String requestURI) {
        if ("/api/admin/admin/login".equals(requestURI) ||
            "/api/admin/admin/logoutAdmin".equals(requestURI)) {

            return false;
        }
        return true;
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
