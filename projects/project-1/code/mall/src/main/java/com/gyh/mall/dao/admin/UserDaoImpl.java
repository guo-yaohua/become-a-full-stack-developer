package com.gyh.mall.dao.admin;

import com.gyh.mall.model.User;
import com.gyh.mall.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {

    /**
     * 获取全部用户
     * @return
     */
    @Override
    public List<User> allUser() {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<User> users = null;
        try {
            users = runner.query("select * from user", new BeanListHandler<User>(User.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    /**
     * 删除指定用户
     * @param id
     * @return
     */
    @Override
    public void deleteUser(int id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("delete from user where id = ?", id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<User> searchUser(String word) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<User> users = null;
        try {
            users = runner.query("select * from user where nickname like ?",
                    new BeanListHandler<User>(User.class),
                    "%" + word + "%");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }
}
