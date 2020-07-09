package com.gyh.mall.service.mall;

import com.gyh.mall.model.bo.mall.OrderAddBO;
import com.gyh.mall.model.bo.mall.OrderCart;
import com.gyh.mall.model.bo.mall.OrderCommentBO;
import com.gyh.mall.model.vo.mall.OrderVO;

import java.util.List;

public interface OrderService {
    List<OrderVO> getOrderByState(int state, String token);

    int addOrder(OrderAddBO orderAddBO);

    int settleAccounts(List<OrderCart> cartList);

    void confirmReceive(int id);

    void sendComment(OrderCommentBO commentBO);

    void deleteOrder(int id);

    int pay(int id);
}
