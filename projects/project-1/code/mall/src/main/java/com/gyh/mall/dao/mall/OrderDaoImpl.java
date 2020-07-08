package com.gyh.mall.dao.mall;

import com.gyh.mall.model.Goods;
import com.gyh.mall.model.Orders;
import com.gyh.mall.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl implements OrderDao{

    /**
     * 查看购物车
     * @param state
     * @param token
     * @return
     */
    @Override
    public List<Orders> getOrders(int state, String token) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Orders> orders = null;
        try {
            orders = runner.query("select * from orders where stateId = ? and nickname = ?",
                    new BeanListHandler<Orders>(Orders.class),
                    state,
                    token);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orders;
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
