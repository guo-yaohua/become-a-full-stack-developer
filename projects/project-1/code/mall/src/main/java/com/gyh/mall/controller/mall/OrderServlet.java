package com.gyh.mall.controller.mall;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.gyh.mall.model.Result;
import com.gyh.mall.model.bo.mall.OrderAddBO;
import com.gyh.mall.model.bo.mall.OrderCart;
import com.gyh.mall.model.bo.mall.OrderCommentBO;
import com.gyh.mall.model.vo.mall.OrderVO;
import com.gyh.mall.service.mall.OrderService;
import com.gyh.mall.service.mall.OrderServiceImpl;
import com.gyh.mall.utils.HttpUtils;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/api/mall/order/*")
public class OrderServlet extends HttpServlet {
    private OrderService orderService = new OrderServiceImpl();

    Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 解析请求
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/order/", "");

        // action
        if ("addOrder".equals(action)) {    // 下单
            addOrder(request, response);
        } else if ("settleAccounts".equals(action)) {   // 付款
            settleAccounts(request, response);
        } else if ("sendComment".equals(action)) {  // 评论
            sendComment(request, response);
        }
    }

    /**
     * 评价
     * @param request
     * @param response
     */
    private void sendComment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);

        OrderCommentBO commentBO = gson.fromJson(requestBody, OrderCommentBO.class);

        orderService.sendComment(commentBO);

        response.getWriter().println(gson.toJson(Result.ok()));
    }

    /**
     * 付款
     * @param request
     * @param response
     */
    private void settleAccounts(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);

        JsonElement jsonElement = new JsonParser().parse(requestBody);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        List<OrderCart> cartList = new ArrayList<OrderCart>();
        JsonArray cartBoList = jsonObject.getAsJsonArray("cartList");
        for (JsonElement element : cartBoList) {
            OrderCart cart = gson.fromJson(element,OrderCart.class);
            cartList.add(cart);
        }

        int code = orderService.settleAccounts(cartList);

        if (code != cartBoList.size()) {
            response.getWriter().println(gson.toJson(Result.error("完成付款！（库存不足的商品，未能完成付款）")));
        } else {
            response.getWriter().println(gson.toJson(Result.ok()));
        }
    }

    /**
     * 下单
     * @param request
     * @param response
     */
    private void addOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);

        OrderAddBO orderAddBO = gson.fromJson(requestBody, OrderAddBO.class);

        int code = orderService.addOrder(orderAddBO);

        if (code == 0) {
            response.getWriter().println(gson.toJson(Result.error("库存不足")));
        } else {
            response.getWriter().println(gson.toJson(Result.ok()));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 解析请求
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/order/", "");

        // action
        if ("getOrderByState".equals(action)) { // 查看购物车
            getOrderByState(request, response);
        } else if ("confirmReceive".equals(action)) {   // 确认收货
            confirmReceive(request, response);
        } else if ("deleteOrder".equals(action)) {  // 删除订单
            deleteOrder(request, response);
        } else if ("pay".equals(action)) {  // 确认付款
            pay(request, response);
        }
    }

    /**
     * 确认付款
     * @param request
     * @param response
     */
    private void pay(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id= Integer.parseInt(request.getParameter("id"));

        int code = orderService.pay(id);

        if (code == 0) {
            response.getWriter().println(gson.toJson(Result.error("库存不足")));
        } else {
            response.getWriter().println(gson.toJson(Result.ok()));
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
     * 确认收货
     * @param request
     * @param response
     */
    private void confirmReceive(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        orderService.confirmReceive(id);

        response.getWriter().println(gson.toJson(Result.ok()));
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
