package com.gyh.mall.service.admin;

import com.gyh.mall.dao.admin.OrderDao;
import com.gyh.mall.dao.admin.OrderDaoImpl;
import com.gyh.mall.model.Orders;
import com.gyh.mall.model.bo.admin.OrderChangeBO;
import com.gyh.mall.model.bo.admin.OrderPageBO;
import com.gyh.mall.model.enumaration.OrderState;
import com.gyh.mall.model.vo.admin.*;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService{
    private OrderDao orderDao = new OrderDaoImpl();

    /**
     * 分页显示订单
     * 根据不同的参数，执行不同查询
     * @param orderPageBO
     * @return
     */
    @Override
    public OrderPageVO ordersByPage(OrderPageBO orderPageBO) {
        int totalCounts = orderDao.getTotalCounts(orderPageBO);

        // 查询分页结果
        List<OrderPageInfoVO> orderPageInfoVOList = orderDao.ordersByPage(orderPageBO);

        OrderPageVO orderPageVO = new OrderPageVO();
        orderPageVO.setTotal(totalCounts);
        orderPageVO.setOrders(orderPageInfoVOList);
        return orderPageVO;
    }

    /**
     * 获取订单详细信息
     * @param id
     * @return
     */
    @Override
    public OrderInfoVO order(String id) {
        // 获取 states
        List<OrderStateVO> states = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            OrderStateVO stateVO = new OrderStateVO();
            stateVO.setId(i);
            if (i == OrderState.UN_PAID.getCode()) {
                stateVO.setName(OrderState.UN_PAID.getValue());
            } else if (i == OrderState.UN_SHIPED.getCode()) {
                stateVO.setName(OrderState.UN_SHIPED.getValue());
            } else if (i == OrderState.DELIVERED.getCode()) {
                stateVO.setName(OrderState.DELIVERED.getValue());
            } else if (i == OrderState.RECEIVED.getCode()) {
                stateVO.setName(OrderState.RECEIVED.getValue());
            }
            states.add(stateVO);
        }

        // 获取 order 信息
        Orders order = orderDao.order(id);

        List<OrderSpecVO> spec = orderDao.getSpecList(order.getGoodsId());

        OrderCurState curState = new OrderCurState();
        curState.setId(order.getStateId());

        OrderCurSpec curSpec = new OrderCurSpec();
        curSpec.setId(order.getGoodsDetailId());

        return new OrderInfoVO(
                Integer.parseInt(id),
                order.getAmount(),
                order.getNum(),
                order.getGoodsDetailId(),
                order.getStateId(),
                order.getGoods(),
                spec,
                states,
                curState,
                curSpec
        );
    }

    /**
     * 修改订单
     * @param changeBO
     */
    @Override
    public void changeOrder(OrderChangeBO changeBO) {
        orderDao.changeOrder(changeBO);
    }

    @Override
    public void deleteOrder(int id) {
        orderDao.deleteOrder(id);
    }
}
