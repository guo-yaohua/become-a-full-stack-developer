package com.gyh.mall.model.vo.admin;

import java.util.List;

/**
 * 用于后台管理系统显示分页订单
 */
public class OrderPageVO {

    private Integer total;

    private List<OrderPageInfoVO> orders;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<OrderPageInfoVO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderPageInfoVO> orders) {
        this.orders = orders;
    }
}
