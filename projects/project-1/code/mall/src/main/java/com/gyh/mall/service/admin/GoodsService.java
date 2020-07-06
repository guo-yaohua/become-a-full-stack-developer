package com.gyh.mall.service.admin;

import com.gyh.mall.model.Type;
import com.gyh.mall.model.bo.admin.*;
import com.gyh.mall.model.vo.admin.TypeGoodsVO;

import java.util.List;
import java.util.Map;

public interface GoodsService {
    List<Type> getType();

    List<TypeGoodsVO> getGoodsByType(String typeId);

    void addGoods(GoodsAddBO goodsAddBO);

    void addType(TypeBO typeBO);

    Map<String, Object> getGoodsInfo(int id);

    int addSpec(SpecBO specBO);

    void deleteSpec(SpecDeleteBO specDeleteBO);

    void updateGoods(GoodsUpdateBO goodsUpdateBO);

    void deleteType(int typeId, String domain);
}
