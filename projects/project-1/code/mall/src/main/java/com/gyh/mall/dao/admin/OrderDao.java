package com.gyh.mall.dao.admin;

import com.gyh.mall.model.Order;
import com.gyh.mall.model.bo.admin.OrderChangeBO;
import com.gyh.mall.model.bo.admin.OrderPageBO;
import com.gyh.mall.model.vo.admin.OrderPageInfoVO;
import com.gyh.mall.model.vo.admin.OrderSpecVO;

import java.util.List;

public interface OrderDao {

    List<OrderPageInfoVO> ordersByPage(OrderPageBO orderPageBO);

    int getTotalCounts(OrderPageBO orderPageBO);

    Order order(String id);

    List<OrderSpecVO> getSpecList(Integer goodsId);

    void changeOrder(OrderChangeBO changeBO);

    void deleteOrder(int id);
}
