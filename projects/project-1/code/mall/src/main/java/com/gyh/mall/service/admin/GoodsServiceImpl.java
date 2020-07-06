package com.gyh.mall.service.admin;

import com.gyh.mall.dao.admin.GoodsDao;
import com.gyh.mall.dao.admin.GoodsDaoImpl;
import com.gyh.mall.model.Goods;
import com.gyh.mall.model.Spec;
import com.gyh.mall.model.Type;
import com.gyh.mall.model.bo.admin.GoodsAddBO;
import com.gyh.mall.model.bo.admin.SpecBO;
import com.gyh.mall.model.bo.admin.SpecDeleteBO;
import com.gyh.mall.model.bo.admin.TypeBO;
import com.gyh.mall.model.vo.admin.TypeGoodsVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GoodsServiceImpl implements GoodsService{
    private GoodsDao goodsDao = new GoodsDaoImpl();

    @Override
    public List<Type> getType() {
        return goodsDao.getType();
    }

    @Override
    public List<TypeGoodsVO> getGoodsByType(String typeId) {
        return goodsDao.getGoodsByType(typeId);
    }

    /**
     * 添加商品、添加规格
     * price、stockNum 需要通过 specList 得到
     * @param goodsAddBO
     */
    @Override
    public void addGoods(GoodsAddBO goodsAddBO) {
        List<SpecBO> specList = goodsAddBO.getSpecList();
        double price = specList.get(0).getUnitPrice();
        int stockNum = specList.get(0).getStockNum();
        for (int i = 1; i < specList.size(); i++) {
            if (price > specList.get(i).getUnitPrice()) {
                price = specList.get(i).getUnitPrice();
            }
            if (stockNum < specList.get(i).getStockNum()) {
                stockNum = specList.get(i).getStockNum();
            }
        }
        Goods goods = new Goods(null,
                goodsAddBO.getImg(),
                goodsAddBO.getName(),
                price,
                goodsAddBO.getTypeId(),
                stockNum,
                goodsAddBO.getDesc());
        goodsDao.addGoods(goods);

        // 返回 goods 表新增商品的 id
        int id = goodsDao.lastInsertId();
        List<Spec> specs = new ArrayList<>();
        for (SpecBO specBO: specList) {
            Spec spec = new Spec(null,
                    specBO.getSpecName(),
                    specBO.getStockNum(),
                    specBO.getUnitPrice(),
                    id);
            specs.add(spec);
        }
        goodsDao.addSpecs(specs);
    }

    @Override
    public void addType(TypeBO typeBO) {
        goodsDao.addType(typeBO);
    }

    @Override
    public Map<String, Object> getGoodsInfo(int id) {
        return goodsDao.getGoodsInfo(id);
    }

    @Override
    public int addSpec(SpecBO specBO) {
        return goodsDao.addSpec(specBO);
    }

    @Override
    public void deleteSpec(SpecDeleteBO specDeleteBO) {
        goodsDao.deleteSpec(specDeleteBO);
    }
}
