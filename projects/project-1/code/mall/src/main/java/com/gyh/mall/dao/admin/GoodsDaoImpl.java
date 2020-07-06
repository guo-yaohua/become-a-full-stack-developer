package com.gyh.mall.dao.admin;

import com.gyh.mall.model.Goods;
import com.gyh.mall.model.Msg;
import com.gyh.mall.model.Spec;
import com.gyh.mall.model.Type;
import com.gyh.mall.model.bo.admin.*;
import com.gyh.mall.model.vo.admin.*;
import com.gyh.mall.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.*;

public class GoodsDaoImpl implements GoodsDao {

    /**
     * 获取商品种类
     * @return
     */
    @Override
    public List<Type> getType() {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Type> typeList = null;
        try {
            typeList = runner.query("select * from type", new BeanListHandler<Type>(Type.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return typeList;
    }

    /**
     * 获取某个分类下的商品信息
     * @param typeId
     * @return
     */
    @Override
    public List<TypeGoodsVO> getGoodsByType(String typeId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<TypeGoodsVO> goodsVOS = null;
        try {
            goodsVOS = runner.query("select id, img, name, price, typeId, stockNum from goods where typeId = ?",
                    new BeanListHandler<TypeGoodsVO>(TypeGoodsVO.class),
                    typeId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return goodsVOS;
    }

    /**
     * 新增商品
     * @param goods
     */
    @Override
    public void addGoods(Goods goods) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("insert into goods values (null, ?, ?, ?, ?, ?, ?)",
                    goods.getImg(),
                    goods.getName(),
                    goods.getPrice(),
                    goods.getTypeId(),
                    goods.getStockNum(),
                    goods.getDesc());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 返回最近更新的 id
     */
    @Override
    public int lastInsertId() {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        BigInteger query = null;
        try {
            query = runner.query("select last_insert_id()", new ScalarHandler<>());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return query.intValue();
    }

    /**
     * 新增规格
     * @param specs
     */
    @Override
    public void addSpecs(List<Spec> specs) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        for (Spec spec : specs) {
            try {
                runner.update("insert into spec values (null, ?, ?, ?, ?)",
                        spec.getSpecName(),
                        spec.getStockNum(),
                        spec.getUnitPrice(),
                        spec.getGoodsId());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * 添加类目
     * @param typeBO
     */
    @Override
    public void addType(TypeBO typeBO) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());

        try {
            runner.update("insert into type (name) values (?)", typeBO.getName());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 返回商品信息
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> getGoodsInfo(int id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());

        Map<String, Object> map = new HashMap<>();
        List<SpecVO> specVOList = new ArrayList<>();
        GoodsInfoVO infoVO = new GoodsInfoVO();
        try {
            specVOList = runner.query("select * from spec where goodsId = ?",
                    new BeanListHandler<SpecVO>(SpecVO.class),
                    id);
            map.put("specs", specVOList);

            infoVO = runner.query("select * from goods where id = ?",
                    new BeanHandler<GoodsInfoVO>(GoodsInfoVO.class),
                    id);
            map.put("goods", infoVO);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return map;
    }

    /**
     * 添加规格
     * @param specBO
     */
    @Override
    public int addSpec(SpecBO specBO) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            return runner.update("insert into spec values (null, ?, ?, ?, ?)",
                    specBO.getSpecName(),
                    specBO.getStockNum(),
                    specBO.getUnitPrice(),
                    specBO.getGoodsId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    /**
     * 删除规格
     * @param specDeleteBO
     */
    @Override
    public void deleteSpec(SpecDeleteBO specDeleteBO) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());

        try {
            runner.update("delete from spec where specName = ? and goodsId = ?",
                    specDeleteBO.getSpecName(),
                    specDeleteBO.getGoodsId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 更新商品
     * @param goods
     */
    @Override
    public void updateGoods(Goods goods) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());

        try {
            runner.update("update goods set `desc` = ?, img = ?, name = ?, typeId = ?, price = ?, stockNum = ? where id = ?",
                    goods.getDesc(),
                    goods.getImg(),
                    goods.getName(),
                    goods.getTypeId(),
                    goods.getPrice(),
                    goods.getStockNum(),
                    goods.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 更新规格
     * @param specList
     */
    @Override
    public void updateSpecs(List<SpecUpdateBO> specList) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());

        for (SpecUpdateBO  specUpdateBO : specList) {
            try {
                runner.update("update spec set specName = ?, stockNum = ?, unitPrice = ? where id = ?",
                        specUpdateBO.getSpecName(),
                        specUpdateBO.getStockNum(),
                        specUpdateBO.getUnitPrice(),
                        specUpdateBO.getId());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * 删除类目
     * @param typeId
     */
    @Override
    public void deleteType(int typeId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());

        try {
            runner.update("delete from type where id = ?", typeId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 获取某一类目下的所有商品id、img
     * @param typeId
     * @return
     */
    @Override
    public List<GoodsIdAndImgVO> getIdAndImg(int typeId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());

        List<GoodsIdAndImgVO> goodsIdAndImgVOList = new ArrayList<>();
        try {
            goodsIdAndImgVOList = runner.query("select id, img from goods where typeId = ?",
                    new BeanListHandler<GoodsIdAndImgVO>(GoodsIdAndImgVO.class),
                    typeId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return goodsIdAndImgVOList;
    }

    /**
     * 根据 type 返回的 idList，删除 goods 及其规格
     * @param goodsIdAndImgVOList
     */
    @Override
    public void deleteSpecByType(List<GoodsIdAndImgVO> goodsIdAndImgVOList) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());

        for (GoodsIdAndImgVO goodsIdAndImgVO : goodsIdAndImgVOList) {
            try {
                runner.update("delete from goods where id = ?", goodsIdAndImgVO.getId());
                runner.update("delete from spec where goodsId = ?", goodsIdAndImgVO.getId());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * 删除指定商品
     * @param id
     */
    @Override
    public void deleteGoods(int id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());

        try {
            runner.update("delete from goods where id = ?", id);
            runner.update("delete from spec where goodsId = ?", id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 返回已经回复的留言
     * @return
     */
    @Override
    public List<MsgReplyVO> repliedMsg() {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<MsgReplyVO> msgReplyVOList = new ArrayList<>();
        List<Msg> msgList = new ArrayList<>();
        try {
            msgList = runner.query("select * from msg where state = 0", new BeanListHandler<Msg>(Msg.class));
            for (Msg msg : msgList) {
                GoodsNameVO goods = runner.query("select name from goods where id = ?",
                        new BeanHandler<GoodsNameVO>(GoodsNameVO.class),
                        msg.getGoodsId());
                UserNameVO user = runner.query("select nickname from user where id = ?",
                        new BeanHandler<UserNameVO>(UserNameVO.class),
                        msg.getUserId());
                MsgReplyVO msgReplyVO = new MsgReplyVO(msg.getId(),
                        msg.getUserId(),
                        msg.getGoodsId(),
                        msg.getContent(),
                        msg.getReplyContent(),
                        msg.getStatus(),
                        msg.getCreatetime(),
                        goods,
                        user);
                msgReplyVOList.add(msgReplyVO);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return msgReplyVOList;
    }

    /**
     * 返回未得到回复的留言
     * @return
     */
    @Override
    public List<MsgNoReplyVO> noReplyMsg() {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<MsgNoReplyVO> msgNoReplyVOList = new ArrayList<>();
        List<Msg> msgList = new ArrayList<>();
        try {
            msgList = runner.query("select * from msg where state = 1", new BeanListHandler<Msg>(Msg.class));
            for (Msg msg : msgList) {
                GoodsNameVO goods = runner.query("select name from goods where id = ?",
                        new BeanHandler<GoodsNameVO>(GoodsNameVO.class),
                        msg.getGoodsId());
                UserNameVO user = runner.query("select nickname from user where id = ?",
                        new BeanHandler<UserNameVO>(UserNameVO.class),
                        msg.getUserId());
                MsgNoReplyVO msgNoReplyVO = new MsgNoReplyVO(msg.getId(),
                        msg.getUserId(),
                        msg.getGoodsId(),
                        msg.getContent(),
                        msg.getStatus(),
                        msg.getCreatetime(),
                        goods,
                        user);
                msgNoReplyVOList.add(msgNoReplyVO);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return msgNoReplyVOList;
    }

    /**
     * 回复留言
     * @param msgReplyBO
     */
    @Override
    public void reply(MsgReplyBO msgReplyBO) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());

        try {
            runner.update("update msg set replyContent = ?, state = 0 where id = ?",
                    msgReplyBO.getContent(),
                    msgReplyBO.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
