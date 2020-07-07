package com.gyh.mall.controller.admin;

import com.google.gson.Gson;
import com.gyh.mall.model.Result;
import com.gyh.mall.model.bo.admin.OrderChangeBO;
import com.gyh.mall.model.bo.admin.OrderPageBO;
import com.gyh.mall.model.vo.admin.OrderInfoVO;
import com.gyh.mall.model.vo.admin.OrderPageVO;
import com.gyh.mall.service.admin.OrderService;
import com.gyh.mall.service.admin.OrderServiceImpl;
import com.gyh.mall.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/admin/order/*")
public class OrderServlet extends HttpServlet {

    private OrderService orderService = new OrderServiceImpl();
    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 解析请求
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/order/", "");

        // action
        if ("ordersByPage".equals(action)) {    // 分页显示订单
            ordersByPage(request, response);
        } else if ("changeOrder".equals(action)) {  // 修改订单
            changeOrder(request, response);
        }
    }

    /**
     * 修改订单
     * @param request
     * @param response
     */
    private void changeOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);

        OrderChangeBO changeBO = gson.fromJson(requestBody, OrderChangeBO.class);

        orderService.changeOrder(changeBO);

        response.getWriter().println(gson.toJson(Result.ok()));
    }

    /**
     * 分页显示订单
     * @param request
     * @param response
     */
    private void ordersByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 解析请求体
        String requestBody = HttpUtils.getRequestBody(request);

        // 请求数据转 Java 对象
        OrderPageBO orderPageBO = gson.fromJson(requestBody, OrderPageBO.class);

        OrderPageVO orders = orderService.ordersByPage(orderPageBO);

        response.getWriter().println(gson.toJson(Result.ok(orders)));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 解析请求
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/order/", "");

        // action
        if ("order".equals(action)) {   // 获取订单详情
            order(request, response);
        } else if ("deleteOrder".equals(action)) {    // 删除订单
            deleteOrder(request, response);
        }

    }

    /**
     * 删除订单
     * @param request
     * @param response
     */
    private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        orderService.deleteOrder(id);

        response.getWriter().println(gson.toJson(Result.ok()));
    }


    /**
     * 获取订单详情
     * @param request
     * @param response
     */
    private void order(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");

        OrderInfoVO infoVO = orderService.order(id);

        response.getWriter().println(gson.toJson(Result.ok(infoVO)));
    }
}
