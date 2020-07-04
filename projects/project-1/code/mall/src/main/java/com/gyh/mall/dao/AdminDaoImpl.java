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

    /**
     * 查询所有管理员，返回 adminList
     * @return
     */
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

    /**
     * 添加管理员，返回添加的 admin 对象
     * @param admin
     * @return
     */
    @Override
    public int addAdminss(Admin admin) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            int code = runner.update("insert into admin(email, pwd, nickname) values(? , ? , ?)",
                    admin.getEmail(),
                    admin.getPwd(),
                    admin.getNickname());
            return code;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    /**
     * 删除管理员，返回删除的 admin 对象（仅包含 id 信息）
     * @param id
     * @return
     */
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

    /**
     * 更新管理员信息，返回数据变动数目
     * @param admin
     * @return
     */
    @Override
    public int updateAdminss(Admin admin) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            int code = runner.update("update admin set email = ?, nickname = ?, pwd = ? WHERE id = ?",
                    admin.getEmail(),
                    admin.getNickname(),
                    admin.getPwd(),
                    admin.getId());
            return code;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取管理员信息，返回 admin
     * @param id
     * @return
     */
    @Override
    public Admin getAdminsInfo(int id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Admin query = null;
        try {
            query = runner.query("select * from admin where id = ?",
                    new BeanHandler<>(Admin.class),
                    id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }
}
