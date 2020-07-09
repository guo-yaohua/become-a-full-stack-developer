package com.gyh.mall.dao.mall;

import com.gyh.mall.model.Comment;
import com.gyh.mall.model.Msg;
import com.gyh.mall.model.User;
import com.gyh.mall.model.bo.mall.GoodsMsgBO;
import com.gyh.mall.model.vo.mall.GoodsSerachVO;
import com.gyh.mall.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDaoImpl implements GoodsDao {

    /**
     * 获取商品留言
     * @param id
     * @return
     */
    @Override
    public List<Msg> getGoodsMsg(int id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Msg> msgs = null;
        try {
            msgs = runner.query("select * from msg where goodsId = ?",
                    new BeanListHandler<Msg>(Msg.class),
                    id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return msgs;
    }

    /**
     * 获取用户名
     * @param userId
     * @return
     */
    @Override
    public String getUserName(Integer userId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        String name = null;
        try {
            name = runner.query("select nickname from user where id = ?",
                    new ScalarHandler<>(),
                    userId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return name;
    }

    /**
     * 获取商品评论
     * @param goodsId
     * @return
     */
    @Override
    public List<Comment> getGoodsComment(int goodsId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Comment> comments = new ArrayList<>();
        try {
            comments = runner.query("select * from comment where goodsId = ?",
                    new BeanListHandler<Comment>(Comment.class),
                    goodsId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return comments;
    }

    /**
     * 搜索商品
     * @param keyword
     * @return
     */
    @Override
    public List<GoodsSerachVO> searchGoods(String keyword) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<GoodsSerachVO> serachVOList = new ArrayList<>();
        try {
            serachVOList = runner.query("select id, img, name, price, typeId from goods where name like ?",
                    new BeanListHandler<>(GoodsSerachVO.class),
                    "%" + keyword + "%");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return serachVOList;
    }

    /**
     * 通过nickname 获取 user
     * @param token
     * @return
     */
    @Override
    public User getUserByNickname(String token) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        User user = null;
        try {
            user = runner.query("select * from user where nickname = ?",
                    new BeanHandler<>(User.class),
                    token);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    /**
     * 商品提问
     * @param msg
     */
    @Override
    public void askGoodsMsg(Msg msg) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("insert into msg (userId, goodsId, content, state, createtime) " +
                    "values (?, ?, ?, ?, ?)",
                    msg.getUserId(),
                    msg.getGoodsId(),
                    msg.getContent(),
                    msg.getStatus(),
                    msg.getCreatetime());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
