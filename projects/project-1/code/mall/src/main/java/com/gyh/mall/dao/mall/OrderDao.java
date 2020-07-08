package com.gyh.mall.dao.mall;

import com.gyh.mall.model.Goods;
import com.gyh.mall.model.Orders;

import java.util.List;

public interface OrderDao {
    Goods getGoodsInfo(Integer goodsId);

    List<Orders> getOrders(int state, String token);
}
