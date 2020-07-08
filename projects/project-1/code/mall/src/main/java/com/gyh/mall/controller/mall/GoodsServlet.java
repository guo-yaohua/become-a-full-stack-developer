package com.gyh.mall.controller.mall;

import com.google.gson.Gson;
import com.gyh.mall.model.Result;
import com.gyh.mall.model.vo.admin.SpecVO;
import com.gyh.mall.model.vo.admin.TypeGoodsVO;
import com.gyh.mall.model.vo.mall.GoodsCommentInfoVO;
import com.gyh.mall.model.vo.mall.GoodsCommentVO;
import com.gyh.mall.model.vo.mall.GoodsInfoVO;
import com.gyh.mall.model.vo.mall.GoodsMsgVO;
import com.gyh.mall.service.admin.GoodsService;
import com.gyh.mall.service.admin.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/api/mall/goods/*")
public class GoodsServlet extends HttpServlet {
    private Gson gson = new Gson();

    private GoodsService adminGoodsService = new GoodsServiceImpl();

    private com.gyh.mall.service.mall.GoodsService goodsService = new com.gyh.mall.service.mall.GoodsServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 解析请求
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/goods/", "");

        if ("getGoodsByType".equals(action)) {  // 获取商品列表
            getGoodsByType(request, response);
        } else if ("getGoodsInfo".equals(action)) { // 获取商品信息
            getGoodsInfo(request, response);
        } else if ("getGoodsMsg".equals(action)) {  // 获取商品留言
            getGoodsMsg(request, response);
        } else if ("getGoodsComment".equals(action)) {  // 获取商品评价
            getGoodsComment(request, response);
        }
    }

    /**
     * 获取商品评价
     * @param request
     * @param response
     */
    private void getGoodsComment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int goodsId = Integer.parseInt(request.getParameter("goodsId"));

        GoodsCommentVO commentVO = goodsService.getGoodsComment(goodsId);

        response.getWriter().println(gson.toJson(Result.ok(commentVO)));
    }

    /**
     * 获取商品留言
     * @param request
     * @param response
     */
    private void getGoodsMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        List<GoodsMsgVO> msgList = goodsService.getGoodsMsg(id);

        response.getWriter().println(gson.toJson(Result.ok(msgList)));
    }

    /**
     * 获取商品信息
     * @param request
     * @param response
     */
    private void getGoodsInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Map<String, Object> goodsInfo = adminGoodsService.getGoodsInfo(id);
        com.gyh.mall.model.vo.admin.GoodsInfoVO goods = (com.gyh.mall.model.vo.admin.GoodsInfoVO) goodsInfo.get("goods");

        GoodsInfoVO goodsInfoVO = new GoodsInfoVO(
                goods.getImg(),
                goods.getName(),
                goods.getDesc(),
                goods.getTypeId(),
                (List<SpecVO>) goodsInfo.get("specs")
        );
        response.getWriter().println(gson.toJson(Result.ok(goodsInfoVO)));

    }

    /**
     * 获取商品列表
     * @param request
     * @param response
     */
    private void getGoodsByType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String typeId = request.getParameter("typeId");

        List<TypeGoodsVO> goodsByType = adminGoodsService.getGoodsByType(typeId);

        response.getWriter().println(gson.toJson(Result.ok(goodsByType)));

    }
}
