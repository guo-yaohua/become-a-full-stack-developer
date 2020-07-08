package com.gyh.mall.service.mall;

import com.gyh.mall.model.vo.mall.GoodsCommentVO;
import com.gyh.mall.model.vo.mall.GoodsMsgVO;

import java.util.List;

public interface GoodsService {
    List<GoodsMsgVO> getGoodsMsg(int id);

    GoodsCommentVO getGoodsComment(int goodsId);
}
