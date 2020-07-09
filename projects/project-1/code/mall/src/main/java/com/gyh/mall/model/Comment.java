package com.gyh.mall.model;

import java.sql.Date;

public class Comment {

    private Integer id;

    private Double score;

    private String specName;

    private String comment;

    private Date createtime;

    private Integer userId;

    private Integer goodsId;

    private Integer orderId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Comment() {
    }

    public Comment(Double score, String specName, String comment, Date createtime, Integer userId, Integer goodsId, Integer orderId) {
        this.score = score;
        this.specName = specName;
        this.comment = comment;
        this.createtime = createtime;
        this.userId = userId;
        this.goodsId = goodsId;
        this.orderId = orderId;
    }
}
