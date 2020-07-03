package com.gyh.mall.dao;

import com.gyh.mall.model.Admin;
import com.gyh.mall.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class AdminDaoImpl implements AdminDao {

    /**
     * 根据输入值，查询数据库，返回 Admin 对象
     * @param admin
     * @return
     */
    @Override
    public Admin login(Admin admin) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Admin query = null;
        String s = admin.getEmail();
        try {
            query = runner.query("select * from admin where email = ? and pwd = ?",
                    new BeanHandler<>(Admin.class),
                    admin.getEmail(),
                    admin.getPwd());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    public List<Admin> allAdmins() {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Admin> admins = null;
        try {
            admins = runner.query("select * from admin", new BeanListHandler<Admin>(Admin.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return admins;
    }
}
