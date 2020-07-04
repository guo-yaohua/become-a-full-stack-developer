package com.gyh.mall.dao;

import com.gyh.mall.model.User;
import com.gyh.mall.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

public class MallUserDaoImpl implements MallUserDao {

    /**
     * 用户注册
     * @param user
     * @return
     */
    @Override
    public int signup(User user) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        int code = 0;
        try {
            code = runner.update("insert into user(email, nickname, pwd, recipient, address, phone) values(?, ?, ?, ?, ?, ?)",
                    user.getEmail(),
                    user.getNickname(),
                    user.getPwd(),
                    user.getRecipient(),
                    user.getAddress(),
                    user.getPhone());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return code;
    }
}
