package com.gyh.mall.controller.mall;

import com.google.gson.Gson;
import com.gyh.mall.model.Result;
import com.gyh.mall.model.vo.mall.OrderVO;
import com.gyh.mall.service.mall.OrderService;
import com.gyh.mall.service.mall.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/mall/order/*")
public class OrderServlet extends HttpServlet {
    private OrderService orderService = new OrderServiceImpl();

    Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 解析请求
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/order/", "");

        // action
        if ("getOrderByState".equals(action)) { // 查看购物车
            getOrderByState(request, response);
        }
    }

    /**
     * 查看购物车
     * @param request
     * @param response
     */
    private void getOrderByState(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int state = Integer.parseInt(request.getParameter("state"));
        String token = request.getParameter("token");

        List<OrderVO> orderVOList = orderService.getOrderByState(state, token);

        response.getWriter().println(gson.toJson(Result.ok(orderVOList)));
    }
}
