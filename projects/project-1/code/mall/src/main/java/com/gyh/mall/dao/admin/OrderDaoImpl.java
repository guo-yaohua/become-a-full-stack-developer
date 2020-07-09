package com.gyh.mall.dao.admin;

import com.alibaba.druid.util.StringUtils;
import com.gyh.mall.model.Order;
import com.gyh.mall.model.bo.admin.OrderChangeBO;
import com.gyh.mall.model.bo.admin.OrderPageBO;
import com.gyh.mall.model.vo.admin.OrderPageInfoVO;
import com.gyh.mall.model.vo.admin.OrderSpecVO;
import com.gyh.mall.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao{

    /**
     * 分页显示订单
     * @param orderPageBO
     * @return
     */
    @Override
    public List<OrderPageInfoVO> ordersByPage(OrderPageBO orderPageBO) {
        Map<String, Object> results = getDynamicSql(orderPageBO);

        List<Object> params = (List<Object>) results.get("params");
        params.add(orderPageBO.getPagesize());
        params.add((orderPageBO.getCurrentPage() - 1) * orderPageBO.getPagesize());

        String base = (String) results.get("sql");
        String prefix_sql = "select id, userId, goodsDetailId, goods, spec, num as goodsNum," +
                " amount, stateId, nickname, name, address, phone";
        String limit = " limit ? offset ?"; // 分页

        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<OrderPageInfoVO> orderPageInfoVOList = null;
        try {
            orderPageInfoVOList = runner.query(prefix_sql + base + limit,
                    new BeanListHandler<OrderPageInfoVO>(OrderPageInfoVO.class),
                    params.toArray());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orderPageInfoVOList;
    }

    /**
     * 获取查询到的订单总数
     * @param orderPageBO
     * @return
     */
    @Override
    public int getTotalCounts(OrderPageBO orderPageBO) {
        Map<String, Object> results = getDynamicSql(orderPageBO);
        String base = (String) results.get("sql");
        List<Object> params = (List<Object>) results.get("params");
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        String prefix_sql = "select count(id)";
        Long query = null;
        try {
            query = runner.query(prefix_sql + base,
                    new ScalarHandler<>(),
                    params.toArray());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return query.intValue();
    }

    /**
     * 获取订单详细信息
     * @param id
     * @return
     */
    @Override
    public Order order(String id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Order order = null;
        try {
            order = runner.query("select * from orders where id = ?",
                    new BeanHandler<Order>(Order.class),
                    id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return order;
    }

    /**
     * 根据 goodsId，返回 orderSpecVO 列表
     * @param goodsId
     * @return
     */
    @Override
    public List<OrderSpecVO> getSpecList(Integer goodsId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<OrderSpecVO> spec = null;
        try {
            spec = runner.query("select * from spec where goodsId = ?",
                    new BeanListHandler<OrderSpecVO>(OrderSpecVO.class),
                    goodsId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return spec;
    }

    /**
     * 修改订单
     * @param changeBO
     */
    @Override
    public void changeOrder(OrderChangeBO changeBO) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());

        try {
            runner.update("update orders set num = ?, goodsDetailId = ?, stateId = ? where id = ?",
                    changeBO.getNum(),
                    changeBO.getSpec(),
                    changeBO.getState(),
                    changeBO.getId());
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
     * 动态 sql 思想
     * @param orderPageBO
     * @return
     */
    private Map<String, Object> getDynamicSql(OrderPageBO orderPageBO) {
        String base = " from orders where 1 = 1";

        List<Object> list = new ArrayList<>();
        if (orderPageBO.getState() != -1) { // 状态码
            base = base + " and stateId = ?";
            list.add(orderPageBO.getState());
        }
        if (!StringUtils.isEmpty(orderPageBO.getMoneyLimit1())) {    // 金额上限
            base = base + " and amount < ?";
            list.add(orderPageBO.getMoneyLimit1());
        }
        if (!StringUtils.isEmpty(orderPageBO.getMoneyLimit2())) {    // 金额下限
            base = base + " and amount > ?";
            list.add(orderPageBO.getMoneyLimit2());
        }
        if (!StringUtils.isEmpty(orderPageBO.getGoods())) { // 商品名称
            base = base + " and goods like ?";
            list.add(" %" + orderPageBO.getGoods() + "%");
        }
        if (!StringUtils.isEmpty(orderPageBO.getAddress())) {   // 收货地址
            base = base + " and address like ?";
            list.add(" %" + orderPageBO.getAddress() + "%");
        }
        if (!StringUtils.isEmpty(orderPageBO.getName())) {  // 收件人
            base = base + " and name like ?";
            list.add(" %" + orderPageBO.getName() + "%");
        }
        if (!StringUtils.isEmpty(orderPageBO.getId())) {    // 订单号
            base = base + " and id = ?";
            list.add(Integer.parseInt(orderPageBO.getId()));
        }
        Map<String, Object> map = new HashMap<>();
        map.put("sql", base);
        map.put("params", list);
        return map;
    }
}
