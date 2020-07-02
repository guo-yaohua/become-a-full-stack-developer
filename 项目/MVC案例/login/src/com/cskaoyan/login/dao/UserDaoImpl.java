package com.cskaoyan.login.dao;

import com.cskaoyan.login.model.User;
import com.cskaoyan.login.utils.DruidUtils;
import com.cskaoyan.login.utils.UserStatus;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

//数据库相关操作写在这里
public class UserDaoImpl implements UserDao {
    @Override
    public boolean isUsernameExists(User user) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        User u = null;
        try {
            u = runner.query("select * from user where username = ?", new BeanHandler<>(User.class), user.getUsername());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(u == null){
            return false;
        }
        return true;
    }

    @Override
    public void register(User user) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("insert into user values (null, ?, ?)", user.getUsername(), user.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int login(User user) {
        //QueryRunner 那一套
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        User u = null;
        try {
            u = runner.query("select * from user where username = ? and password = ?", new BeanHandler<>(User.class),
                    user.getUsername(),
                    user.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(u == null){
            //用户名密码错误
            return UserStatus.INCORRECT_NAME_OR_PASSWORD;
        }
        return UserStatus.LOGIN_SUCCESS;
    }

    @Override
    public User query(String userId) {
        return null;
    }
}
