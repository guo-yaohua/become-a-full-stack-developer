package com.gyh.mall.service.admin;

import com.gyh.mall.dao.admin.GoodsDao;
import com.gyh.mall.dao.admin.GoodsDaoImpl;
import com.gyh.mall.model.Goods;
import com.gyh.mall.model.Spec;
import com.gyh.mall.model.Type;
import com.gyh.mall.model.bo.admin.*;
import com.gyh.mall.model.vo.admin.GoodsIdAndImgVO;
import com.gyh.mall.model.vo.admin.MsgNoReplyVO;
import com.gyh.mall.model.vo.admin.MsgReplyVO;
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
        goodsRefresh();
        return goodsDao.getGoodsByType(typeId);
    }

    /**
     * 刷新商品信息
     */
    private void goodsRefresh() {
        List<Goods> goodsList = goodsDao.getGoodsList();
        for (Goods goods : goodsList) {
            List<Spec> specList = goodsDao.getSpecListByGoodsId(goods.getId());

            // 设置商品默认展示规格
            double price = specList.get(0).getUnitPrice();
            int stockNum = specList.get(0).getStockNum();
            for (int i = 1; i < specList.size(); i++) {
                if ((price > specList.get(i).getUnitPrice() || stockNum == 0) && specList.get(i).getStockNum() > 0) {  // 最便宜且有货的
                    price = specList.get(i).getUnitPrice();
                    stockNum = specList.get(i).getStockNum();
                }
            }

            goodsDao.goodsRefresh(goods.getId(), price, stockNum);
        }
    }

    /**
     * 添加商品、添加规格
     * price、stockNum 需要通过 specList 得到
     * @param goodsAddBO
     */
    @Override
    public void addGoods(GoodsAddBO goodsAddBO) {
        List<SpecBO> specList = goodsAddBO.getSpecList();

        // 设置商品默认展示规格
        double price = specList.get(0).getUnitPrice();
        int stockNum = specList.get(0).getStockNum();
        for (int i = 1; i < specList.size(); i++) {
            if ((price > specList.get(i).getUnitPrice() || stockNum == 0) && specList.get(i).getStockNum() > 0) {  // 最便宜且有货的
                price = specList.get(i).getUnitPrice();
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

    /**
     * 更新商品，更新规格
     * @param goodsUpdateBO
     */
    @Override
    public void updateGoods(GoodsUpdateBO goodsUpdateBO) {
        List<SpecUpdateBO> specList = goodsUpdateBO.getSpecList();
        double price = specList.get(0).getUnitPrice();
        int stockNum = specList.get(0).getStockNum();

        for (int i = 0; i < specList.size(); i++) {
            if (price > specList.get(i).getUnitPrice()) {
                price = specList.get(i).getUnitPrice();
            }
            if (stockNum < specList.get(i).getStockNum()) {
                stockNum = specList.get(i).getStockNum();
            }
        }

        Goods goods = new Goods(goodsUpdateBO.getId(),
                goodsUpdateBO.getImg(),
                goodsUpdateBO.getName(),
                price,
                goodsUpdateBO.getTypeId(),
                stockNum,
                goodsUpdateBO.getDesc());
        goodsDao.updateGoods(goods);

        goodsDao.updateSpecs(specList);
    }

    /**
     * 删除指定类目，并删除与其关联的 goods、spec
     * @param typeId
     * @param domain
     */
    @Override
    public void deleteType(int typeId, String domain) {
        goodsDao.deleteType(typeId);

        // 获取该类目下的所有商品 id、img
        List<GoodsIdAndImgVO> goodsIdAndImgVOList = goodsDao.getIdAndImg(typeId);

        // 删除 goods 记录和相关联的 spec
        goodsDao.deleteSpecByType(goodsIdAndImgVOList);

        // 删除图片
    }

    @Override
    public void deleteGoods(int id) {
        goodsDao.deleteGoods(id);
    }

    @Override
    public List<MsgReplyVO> repliedMsg() {
        return goodsDao.repliedMsg();
    }

    @Override
    public List<MsgNoReplyVO> noReplyMsg() {
        return goodsDao.noReplyMsg();
    }

    @Override
    public void reply(MsgReplyBO msgReplyBO) {
        goodsDao.reply(msgReplyBO);
    }
}
