package com.gyh.mall.dao.mall;

import com.gyh.mall.model.Comment;
import com.gyh.mall.model.Msg;

import java.util.List;

public interface GoodsDao {
    List<Msg> getGoodsMsg(int id);

    String getUserName(Integer userId);

    List<Comment> getGoodsComment(int goodsId);
}
