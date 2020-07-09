package com.gyh.mall.dao.mall;

import com.gyh.mall.model.*;
import com.gyh.mall.model.bo.mall.OrderCart;
import com.gyh.mall.model.bo.mall.OrderCommentBO;
import com.gyh.mall.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao{

    /**
     * 查看购物车
     * @param state
     * @param token
     * @return
     */
    @Override
    public List<Order> getOrders(int state, String token) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Order> orders = new ArrayList<>();
        try {
            if (state == -1) {
                orders = runner.query("select * from orders where nickname = ?",
                        new BeanListHandler<Order>(Order.class),
                        token);
            } else {
                orders = runner.query("select * from orders where stateId = ? and nickname = ?",
                        new BeanListHandler<Order>(Order.class),
                        state,
                        token);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orders;
    }

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    @Override
    public User getUserByNickame(String token) {
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
     * 获取规格
     * @param goodsDetailId
     * @return
     */
    @Override
    public Spec getSepcById(Integer goodsDetailId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Spec spec = null;
        try {
            spec = runner.query("select * from spec where id = ?",
                    new BeanHandler<>(Spec.class),
                    goodsDetailId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return spec;
    }

    /**
     * 获取商品信息
     * @param goodsId
     * @return
     */
    @Override
    public Goods getGoodsById(Integer goodsId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Goods goods = null;
        try {
            goods = runner.query("select * from goods where id = ?",
                    new BeanHandler<>(Goods.class),
                    goodsId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return goods;
    }

    /**
     * 下单
     * @param order
     */
    @Override
    public void addOrder(Order order) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("insert into orders (nickname, userId, name, address, phone, goods, goodsId, spec, goodsDetailId, num, amount, stateId, hasComment, createtime) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    order.getNickname(),
                    order.getUserId(),
                    order.getName(),
                    order.getAddress(),
                    order.getPhone(),
                    order.getGoods(),
                    order.getGoodsId(),
                    order.getSpec(),
                    order.getGoodsDetailId(),
                    order.getNum(),
                    order.getAmount(),
                    order.getStateId(),
                    0,
                    order.getCreatetime());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 付款
     * @param cartList
     */
    @Override
    public void settleAccounts(List<OrderCart> cartList) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        for (OrderCart orderCart : cartList) {
            try {
                runner.update("update orders set stateId = ?, num = ?, amount = ? where id = ?",
                        1,
                        orderCart.getGoodsNum(),
                        orderCart.getAmount(),
                        orderCart.getId());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * 确认收货
     * @param id
     */
    @Override
    public void confirmReceive(int id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("update orders set stateId = ? where id = ?",
                    3,
                    id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 评论
     * @param comment
     */
    @Override
    public void sendComment(Comment comment) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("insert into comment (id, score, specName, comment, createtime, userId, goodsId, orderId) " +
                            "values (null, ?, ?, ?, ?, ?, ?, ?)",
                    (Double) comment.getScore(),
                    comment.getSpecName(),
                    comment.getComment(),
                    comment.getCreatetime(),
                    comment.getUserId(),
                    comment.getGoodsId(),
                    comment.getOrderId());

            runner.update("update orders set hasComment = ? where id = ?",
                    1,
                    comment.getOrderId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 删除订单
     * @param id
     */
    @Override
    public void deleteOrder(int id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());

        try {
            runner.update("delete from orders where id = ?", id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    /**
     * 获取商品信息
     * @param goodsId
     * @return
     */
    @Override
    public Goods getGoodsInfo(Integer goodsId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Goods goods = null;
        try {
            goods = runner.query("select * from goods where id = ?",
                    new BeanHandler<Goods>(Goods.class),
                    goodsId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return goods;
    }
}
