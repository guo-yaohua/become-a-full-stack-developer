package com.gyh.mall.dao.admin;

import com.gyh.mall.model.Goods;
import com.gyh.mall.model.Spec;
import com.gyh.mall.model.Type;
import com.gyh.mall.model.bo.admin.SpecBO;
import com.gyh.mall.model.bo.admin.SpecDeleteBO;
import com.gyh.mall.model.bo.admin.SpecUpdateBO;
import com.gyh.mall.model.bo.admin.TypeBO;
import com.gyh.mall.model.vo.admin.GoodsIdAndImgVO;
import com.gyh.mall.model.vo.admin.GoodsInfoVO;
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
}
