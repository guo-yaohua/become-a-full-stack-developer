package com.gyh.mall.dao.mall;

import com.gyh.mall.model.Comment;
import com.gyh.mall.model.Msg;
import com.gyh.mall.model.User;
import com.gyh.mall.model.bo.mall.GoodsMsgBO;
import com.gyh.mall.model.vo.mall.GoodsSerachVO;

import java.util.List;

public interface GoodsDao {
    List<Msg> getGoodsMsg(int id);

    String getUserName(Integer userId);

    List<Comment> getGoodsComment(int goodsId);

    List<GoodsSerachVO> searchGoods(String keyword);

    User getUserByNickname(String token);

    void askGoodsMsg(Msg msg);
}
