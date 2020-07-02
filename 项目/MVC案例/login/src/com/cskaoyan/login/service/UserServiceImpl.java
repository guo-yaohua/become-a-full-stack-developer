package com.cskaoyan.login.service;

import com.cskaoyan.login.dao.UserDao;
import com.cskaoyan.login.dao.UserDaoImpl;
import com.cskaoyan.login.model.User;
import com.cskaoyan.login.utils.UserStatus;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    /**
     *
     * @param user
     * @return
     */
    @Override
    public int register(User user) {
        boolean exists = userDao.isUsernameExists(user);
        if(exists){
            //JSP PageContext.PAGE_SCOPE
            return UserStatus.EXISTS;
        }
        userDao.register(user);
        return UserStatus.REGISTER_SUCCESS;
    }

    @Override
    public int login(User user) {
        return userDao.login(user);
    }

    @Override
    public User query(String userId) {
        return userDao.query(userId);
    }
}
