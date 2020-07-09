package com.gyh.mall.dao.mall;

import com.gyh.mall.model.User;
import com.gyh.mall.model.bo.mall.UserDataBO;
import com.gyh.mall.model.bo.mall.UserLoginBO;
import com.gyh.mall.model.bo.mall.UserPwdBO;
import com.gyh.mall.model.vo.mall.UserLoginVO;
import com.gyh.mall.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

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

    /**
     * 用户登录
     * @param loginBO
     * @return
     */
    @Override
    public UserLoginVO login(UserLoginBO loginBO) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        UserLoginVO loginVO = null;
        try {
            loginVO = runner.query("select nickname as name, nickname as token from user where email = ? and pwd = ?",
                    new BeanHandler<UserLoginVO>(UserLoginVO.class),
                    loginBO.getEmail(),
                    loginBO.getPwd());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return loginVO;
    }

    /**
     * 修改用户信息
     * @param dataBO
     */
    @Override
    public void updateUserData(UserDataBO dataBO) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("update user set address = ?, nickname = ?, phone = ?, recipient = ? where id = ?",
                    dataBO.getAddress(),
                    dataBO.getNickname(),
                    dataBO.getPhone(),
                    dataBO.getRecipient(),
                    dataBO.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 修改用户密码
     * @param pwdBO
     */
    @Override
    public void updatePwd(UserPwdBO pwdBO) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("update user set pwd = ? where id = ? and pwd = ?",
                    pwdBO.getNewPwd(),
                    pwdBO.getId(),
                    pwdBO.getOldPwd());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
