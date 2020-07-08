package com.gyh.mall.service.mall;

import com.gyh.mall.model.vo.mall.OrderVO;

import java.util.List;

public interface OrderService {
    List<OrderVO> getOrderByState(int state, String token);
}
