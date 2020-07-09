package com.gyh.mall.service.mall;

import com.gyh.mall.dao.mall.OrderDao;
import com.gyh.mall.dao.mall.OrderDaoImpl;
import com.gyh.mall.model.*;
import com.gyh.mall.model.bo.mall.OrderAddBO;
import com.gyh.mall.model.bo.mall.OrderCart;
import com.gyh.mall.model.bo.mall.OrderCommentBO;
import com.gyh.mall.model.vo.mall.OrderGoodsVO;
import com.gyh.mall.model.vo.mall.OrderVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();

    /**
     * 查看购物车
     * @param state
     * @param token
     * @return
     */
    @Override
    public List<OrderVO> getOrderByState(int state, String token) {
        // 获取订单
        List<Order> orders = orderDao.getOrders(state, token);
        List<OrderVO> orderVOList = new ArrayList<>();
        for (Order order: orders) {
            Goods goodsInfo = orderDao.getGoodsInfo(order.getGoodsId());
            OrderGoodsVO goods = new OrderGoodsVO(
                    goodsInfo.getId(),
                    goodsInfo.getImg(),
                    goodsInfo.getName(),
                    order.getGoodsDetailId(),
                    order.getSpec(),
                    order.getAmount()/order.getNum()
            );
            OrderVO orderVO = new OrderVO(
                    order.getId(),
                    state,
                    order.getNum(),
                    order.getAmount(),
                    order.getGoodsDetailId(),
                    order.getCreatetime(),
                    (order.getHasComment() == 0) ? false : true,
                    goods
            );
            orderVOList.add(orderVO);
        }
        return orderVOList;
    }

    /**
     * 下单
     * @param orderAddBO
     * @return
     */
    @Override
    public int addOrder(OrderAddBO orderAddBO) {
        User user = orderDao.getUserByNickame(orderAddBO.getToken());
        Spec spec = orderDao.getSepcById(orderAddBO.getGoodsDetailId());
        Goods goods = orderDao.getGoodsById(spec.getGoodsId());
        if (spec.getStockNum() < orderAddBO.getNum()) return 0;
        Date date=new Date();
        Order order = new Order(
                user.getId(),
                user.getNickname(),
                user.getRecipient(),
                user.getAddress(),
                user.getPhone(),
                goods.getName(),
                spec.getSpecName(),
                spec.getGoodsId(),
                spec.getId(),
                orderAddBO.getNum(),
                orderAddBO.getAmount(),
                orderAddBO.getState(),
                new java.sql.Date(date.getTime())
        );

        orderDao.addOrder(order);

        return 1;
    }

    /**
     * 付款
     * @param cartList
     * @return
     */
    @Override
    public int settleAccounts(List<OrderCart> cartList) {
        List<OrderCart> carts = new ArrayList<>();
        int okCount = 0;
        // 检查库存
        for (OrderCart cart : cartList) {
            Order order = orderDao.getOrderById(cart.getId());
            Spec spec = orderDao.getSepcById(order.getGoodsDetailId());
            if (spec.getStockNum() - cart.getGoodsNum() >= 0) { // 库存足够
                carts.add(cart);
                okCount++;
                orderDao.changeStockNum(spec.getId(), spec.getStockNum() - cart.getGoodsNum());
            }
        }


        // 付款
        orderDao.settleAccounts(carts);

        return okCount;
    }

    /**
     * 确认收货
     * @param id
     */
    @Override
    public void confirmReceive(int id) {
        orderDao.confirmReceive(id);
    }

    /**
     * 评论
     * @param commentBO
     */
    @Override
    public void sendComment(OrderCommentBO commentBO) {
        Spec spec = orderDao.getSepcById(commentBO.getGoodsDetailId());
        User user = orderDao.getUserByNickame(commentBO.getToken());
        Date date=new Date();
        Comment comment = new Comment(
                commentBO.getScore(),
                spec.getSpecName(),
                commentBO.getContent(),
                new java.sql.Date(date.getTime()),
                user.getId(),
                commentBO.getGoodsId(),
                commentBO.getOrderId()
        );
        orderDao.sendComment(comment);
    }

    /**
     * 删除订单
     * @param id
     */
    @Override
    public void deleteOrder(int id) {
        Order order = orderDao.getOrderById(id);
        Spec spec = orderDao.getSepcById(order.getGoodsDetailId());
        orderDao.deleteOrder(id);
        orderDao.changeStockNum(spec.getId(), spec.getStockNum() + order.getNum());
    }

    /**
     * 确认付款
     * @param id
     * @return
     */
    @Override
    public int pay(int id) {
        Order order = orderDao.getOrderById(id);
        Spec spec = orderDao.getSepcById(order.getGoodsDetailId());

        if (spec.getStockNum() < order.getNum()) {
            return 0;
        }
        orderDao.changeStockNum(spec.getId(), spec.getStockNum() - order.getNum());
        orderDao.pay(id);
        return 1;
    }
}
