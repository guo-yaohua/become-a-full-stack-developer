package com.gyh.mall.controller.admin;

import com.google.gson.Gson;
import com.gyh.mall.model.Result;
import com.gyh.mall.model.Spec;
import com.gyh.mall.model.Type;
import com.gyh.mall.model.bo.admin.GoodsAddBO;
import com.gyh.mall.model.bo.admin.SpecBO;
import com.gyh.mall.model.bo.admin.SpecDeleteBO;
import com.gyh.mall.model.bo.admin.TypeBO;
import com.gyh.mall.model.vo.admin.GoodsInfoVO;
import com.gyh.mall.model.vo.admin.TypeGoodsVO;
import com.gyh.mall.service.admin.GoodsService;
import com.gyh.mall.service.admin.GoodsServiceImpl;
import com.gyh.mall.utils.FileUploadUtils;
import com.gyh.mall.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/api/admin/goods/*")
public class GoodsServlet extends HttpServlet {

    private GoodsService goodsService = new GoodsServiceImpl();

    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 解析请求
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/goods/", "");

        // action
        if ("imgUpload".equals(action)) {   // 上传图片
            imgUpload(request, response);
        } else if ("addGoods".equals(action)) { // 新增商品
            addGoods(request, response);
        } else if ("addType".equals(action)) {  // 添加类目
            addType(request, response);
        } else if ("addSpec".equals(action)) {  // 添加商品规格
            addSpec(request, response);
        } else if ("deleteSpec".equals(action)) {   // 删除规格
            deleteSpec(request, response);
        }
    }

    /**
     * 删除规格
     * @param request
     * @param response
     */
    private void deleteSpec(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);

        SpecDeleteBO specDeleteBO = gson.fromJson(requestBody, SpecDeleteBO.class);

        goodsService.deleteSpec(specDeleteBO);

        response.getWriter().println(gson.toJson(Result.ok()));
    }

    /**
     * 添加商品规格
     * 注意将 规格名和商品id 组合设为 unique
     * @param request
     * @param response
     */
    private void addSpec(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);

        SpecBO specBO = gson.fromJson(requestBody, SpecBO.class);

        int code = goodsService.addSpec(specBO);

        if (code == 0) {
            response.getWriter().println(gson.toJson(Result.error("规格重复")));
        } else {
            response.getWriter().println(gson.toJson(Result.ok(specBO)));
        }
    }

    /**
     * 添加类目
     * @param request
     * @param response
     */
    private void addType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);

        TypeBO typeBO = gson.fromJson(requestBody, TypeBO.class);

        goodsService.addType(typeBO);

        response.getWriter().println(gson.toJson(Result.ok()));
    }

    /**
     * 新增商品
     * @param request
     * @param response
     */
    private void addGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取请求体
        String requestBody = HttpUtils.getRequestBody(request);

        GoodsAddBO goodsAddBO = gson.fromJson(requestBody, GoodsAddBO.class);

        goodsService.addGoods(goodsAddBO);

        response.getWriter().println(gson.toJson(Result.ok()));
    }

    /**
     * 上传图片
     * 使用 commons-fileUpload 组件
     * @param request
     * @param response
     */
    private void imgUpload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> map = FileUploadUtils.parseRequest(request);

        String file = (String) map.get("file");

        /*
         注意：如果服务器上面的路径没用携带域名端口号，那么就表示该图片就从当前域名端口去取
         如果图片和页面不在一个域内，那么就应当指明图片文件所在的域
         */
        String domain = (String) getServletContext().getAttribute("domain");
        response.getWriter().println(gson.toJson(Result.ok(domain + file)));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 解析请求
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/goods/", "");

        // action
        if ("getType".equals(action)) { // 获取商品种类
            getType(request, response);
        } else if ("getGoodsByType".equals(action)) {   // 获取某个分类下的商品信息
            getGoodsByType(request, response);
        } else if ("getGoodsInfo".equals(action)) { // 获取商品信息
            getGoodsInfo(request, response);
        }
    }

    /**
     * 获取商品信息
     * @param request
     * @param response
     */
    private void getGoodsInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Map<String, Object> map = goodsService.getGoodsInfo(id);

        response.getWriter().println(gson.toJson(Result.ok(map)));

    }

    /**
     * 获取某个分类下的商品信息
     * @param request
     * @param response
     */
    private void getGoodsByType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String typeId = request.getParameter("typeId");

        // 校验

        List<TypeGoodsVO> goodsVOS = goodsService.getGoodsByType(typeId);

        response.getWriter().println(gson.toJson(Result.ok(goodsVOS)));
    }

    /**
     * 获取商品种类
     * @param request
     * @param response
     */
    private void getType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Type> typeList = goodsService.getType();

        response.getWriter().println(gson.toJson(Result.ok(typeList)));
    }
}
