package com.gyh.mall.service.admin;

import com.gyh.mall.model.bo.admin.OrderChangeBO;
import com.gyh.mall.model.bo.admin.OrderPageBO;
import com.gyh.mall.model.vo.admin.OrderInfoVO;
import com.gyh.mall.model.vo.admin.OrderPageVO;

import java.util.List;

public interface OrderService {
    OrderPageVO ordersByPage(OrderPageBO orderPageBO);

    OrderInfoVO order(String id);

    void changeOrder(OrderChangeBO changeBO);

    void deleteOrder(int id);
}
