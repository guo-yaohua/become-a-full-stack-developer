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

    @Override
    public int addAdminss(Admin admin) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            int code = runner.update("insert into admin(email, pwd, nickname) values(? , ? , ?)",
                    admin.getEmail(),
                    admin.getNickname(),
                    admin.getPwd());
            return code;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public Admin deleteAdmins(int id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Admin admin = new Admin();
        try {
            runner.update("delete from admin where id = ?", id);
            admin.setId(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return admin;
    }
}
