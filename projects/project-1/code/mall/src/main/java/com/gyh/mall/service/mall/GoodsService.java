package com.gyh.mall.service.mall;

import com.gyh.mall.model.bo.mall.GoodsMsgBO;
import com.gyh.mall.model.vo.mall.GoodsCommentVO;
import com.gyh.mall.model.vo.mall.GoodsMsgVO;
import com.gyh.mall.model.vo.mall.GoodsSerachVO;

import java.util.List;

public interface GoodsService {
    List<GoodsMsgVO> getGoodsMsg(int id);

    GoodsCommentVO getGoodsComment(int goodsId);

    List<GoodsSerachVO> searchGoods(String keyword);

    void askGoodsMsg(GoodsMsgBO msgBO);
}
