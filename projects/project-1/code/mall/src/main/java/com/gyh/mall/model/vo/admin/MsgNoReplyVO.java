package com.gyh.mall.model.vo.admin;

import java.util.Date;

public class MsgNoReplyVO {
    private Integer id;

    private Integer userId;

    private Integer goodsId;

    private String content;

    private int status;

    private Date createtime;

    private GoodsNameVO goods;

    private UserNameVO user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public GoodsNameVO getGoods() {
        return goods;
    }

    public void setGoods(GoodsNameVO goods) {
        this.goods = goods;
    }

    public UserNameVO getUser() {
        return user;
    }

    public void setUser(UserNameVO user) {
        this.user = user;
    }

    public MsgNoReplyVO() {
    }

    public MsgNoReplyVO(Integer id, Integer userId, Integer goodsId, String content, int status, Date createtime, GoodsNameVO goods, UserNameVO user) {
        this.id = id;
        this.userId = userId;
        this.goodsId = goodsId;
        this.content = content;
        this.status = status;
        this.createtime = createtime;
        this.goods = goods;
        this.user = user;
    }
}
