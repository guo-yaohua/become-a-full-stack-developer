package com.gyh.mall.model.vo.mall;

import java.util.List;

public class GoodsCommentVO {

    private List<GoodsCommentInfoVO> commentList;

    private Double rate;

    public List<GoodsCommentInfoVO> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<GoodsCommentInfoVO> commentList) {
        this.commentList = commentList;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
