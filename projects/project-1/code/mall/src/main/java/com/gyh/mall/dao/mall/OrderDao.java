package com.gyh.mall.dao.mall;

import com.gyh.mall.model.*;
import com.gyh.mall.model.bo.mall.OrderCart;
import com.gyh.mall.model.bo.mall.OrderCommentBO;

import java.util.List;

public interface OrderDao {
    Goods getGoodsInfo(Integer goodsId);

    List<Order> getOrders(int state, String token);

    User getUserByNickame(String token);

    Spec getSepcById(Integer goodsDetailId);

    Goods getGoodsById(Integer goodsId);

    void addOrder(Order order);

    void settleAccounts(List<OrderCart> cartList);

    void confirmReceive(int id);

    void sendComment(Comment comment);

    void deleteOrder(int id);
}
