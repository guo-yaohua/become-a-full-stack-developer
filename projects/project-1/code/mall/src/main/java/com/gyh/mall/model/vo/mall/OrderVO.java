package com.gyh.mall.model.vo.mall;

import java.sql.Date;

public class OrderVO {

    private Integer id;

    private Integer state;

    private Integer goodsNum;

    private Double amount;

    private Integer goodsDetailId;

    private Date createtime;

    private Boolean hasComment;

    private OrderGoodsVO goods;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getGoodsDetailId() {
        return goodsDetailId;
    }

    public void setGoodsDetailId(Integer goodsDetailId) {
        this.goodsDetailId = goodsDetailId;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Boolean getHasComment() {
        return hasComment;
    }

    public void setHasComment(Boolean hasComment) {
        this.hasComment = hasComment;
    }

    public OrderGoodsVO getGoods() {
        return goods;
    }

    public void setGoods(OrderGoodsVO goods) {
        this.goods = goods;
    }

    public OrderVO() {
    }

    public OrderVO(Integer id, Integer state, Integer goodsNum, Double amount, Integer goodsDetailId, Date createtime, Boolean hasComment, OrderGoodsVO goods) {
        this.id = id;
        this.state = state;
        this.goodsNum = goodsNum;
        this.amount = amount;
        this.goodsDetailId = goodsDetailId;
        this.createtime = createtime;
        this.hasComment = hasComment;
        this.goods = goods;
    }
}
