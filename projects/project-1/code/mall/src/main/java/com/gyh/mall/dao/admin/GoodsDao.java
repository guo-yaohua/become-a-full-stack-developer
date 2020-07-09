package com.gyh.mall.dao.admin;

import com.gyh.mall.model.Goods;
import com.gyh.mall.model.Spec;
import com.gyh.mall.model.Type;
import com.gyh.mall.model.bo.admin.*;
import com.gyh.mall.model.vo.admin.GoodsIdAndImgVO;
import com.gyh.mall.model.vo.admin.MsgNoReplyVO;
import com.gyh.mall.model.vo.admin.MsgReplyVO;
import com.gyh.mall.model.vo.admin.TypeGoodsVO;

import java.util.List;
import java.util.Map;

public interface GoodsDao {
    List<Type> getType();

    List<TypeGoodsVO> getGoodsByType(String typeId);

    void addGoods(Goods goods);

    int lastInsertId();

    void addSpecs(List<Spec> specs);

    void addType(TypeBO typeBO);

    Map<String, Object> getGoodsInfo(int id);

    int addSpec(SpecBO specBO);

    void deleteSpec(SpecDeleteBO specDeleteBO);

    void updateGoods(Goods goods);

    void updateSpecs(List<SpecUpdateBO> specList);

    void deleteType(int typeId);

    List<GoodsIdAndImgVO> getIdAndImg(int typeId);

    void deleteSpecByType(List<GoodsIdAndImgVO> goodsIdAndImgVOList);

    void deleteGoods(int id);

    List<MsgReplyVO> repliedMsg();

    List<MsgNoReplyVO> noReplyMsg();

    void reply(MsgReplyBO msgReplyBO);

    List<Goods> getGoodsList();

    List<Spec> getSpecListByGoodsId(int goodsId);

    void goodsRefresh(int goodsId, double price, int stockNum);
}
