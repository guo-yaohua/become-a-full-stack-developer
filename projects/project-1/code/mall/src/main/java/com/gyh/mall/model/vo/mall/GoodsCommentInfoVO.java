package com.gyh.mall.model.vo.mall;

import java.sql.Date;

public class GoodsCommentInfoVO {

    private GoodsCommentUserVO user;

    private Double score;

    private Integer id;

    private String specName;

    private String comment;

    private Date time;

    private Integer userId;

    public GoodsCommentUserVO getUser() {
        return user;
    }

    public void setUser(GoodsCommentUserVO user) {
        this.user = user;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public GoodsCommentInfoVO() {
    }

    public GoodsCommentInfoVO(GoodsCommentUserVO user, Double score, Integer id, String specName, String comment, Date time, Integer userId) {
        this.user = user;
        this.score = score;
        this.id = id;
        this.specName = specName;
        this.comment = comment;
        this.time = time;
        this.userId = userId;
    }
}
