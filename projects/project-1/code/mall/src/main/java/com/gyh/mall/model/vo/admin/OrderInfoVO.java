package com.gyh.mall.model.vo.admin;

import com.gyh.mall.model.enumaration.OrderState;

import java.util.ArrayList;
import java.util.List;

public class OrderInfoVO {

    private Integer id;

    private Double amount;

    private Integer num;

    private Integer goodsDetailId;

    private Integer state;

    private String goods;

    private List<OrderSpecVO> spec;

    private List<OrderStateVO> states;

    private OrderCurState curState;

    private OrderCurSpec curSpec;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getGoodsDetailId() {
        return goodsDetailId;
    }

    public void setGoodsDetailId(Integer goodsDetailId) {
        this.goodsDetailId = goodsDetailId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public List<OrderSpecVO> getSpec() {
        return spec;
    }

    public void setSpec(List<OrderSpecVO> spec) {
        this.spec = spec;
    }

    public List<OrderStateVO> getStates() {
        return states;
    }

    public void setStates(List<OrderStateVO> states) {
        this.states = states;
    }

    public OrderCurState getCurState() {
        return curState;
    }

    public void setCurState(OrderCurState curState) {
        this.curState = curState;
    }

    public OrderCurSpec getCurSpec() {
        return curSpec;
    }

    public void setCurSpec(OrderCurSpec curSpec) {
        this.curSpec = curSpec;
    }

    public OrderInfoVO() {
    }

    public OrderInfoVO(Integer id, Double amount, Integer num, Integer goodsDetailId, Integer state, String goods, List<OrderSpecVO> spec, List<OrderStateVO> states, OrderCurState curState, OrderCurSpec curSpec) {
        this.id = id;
        this.amount = amount;
        this.num = num;
        this.goodsDetailId = goodsDetailId;
        this.state = state;
        this.goods = goods;
        this.spec = spec;
        this.states = states;
        this.curState = curState;
        this.curSpec = curSpec;
    }
}
