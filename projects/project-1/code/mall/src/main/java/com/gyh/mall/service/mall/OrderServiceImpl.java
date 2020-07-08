package com.gyh.mall.service.mall;

import com.gyh.mall.dao.mall.OrderDao;
import com.gyh.mall.dao.mall.OrderDaoImpl;
import com.gyh.mall.model.Goods;
import com.gyh.mall.model.Orders;
import com.gyh.mall.model.vo.mall.OrderGoodsVO;
import com.gyh.mall.model.vo.mall.OrderVO;

import java.util.ArrayList;
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
        List<Orders> orders = orderDao.getOrders(state, token);
        List<OrderVO> orderVOList = new ArrayList<>();
        for (Orders order: orders) {
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
}
