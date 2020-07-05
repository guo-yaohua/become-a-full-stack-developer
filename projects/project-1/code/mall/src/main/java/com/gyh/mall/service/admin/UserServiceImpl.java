package com.gyh.mall.service.admin;

import com.gyh.mall.dao.admin.UserDao;
import com.gyh.mall.dao.admin.UserDaoImpl;
import com.gyh.mall.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    /**
     * 获取所有用户
     * @return
     */
    @Override
    public List<User> allUser() {
        return userDao.allUser();
    }

    @Override
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    @Override
    public List<User> searchUser(String word) {
        return userDao.searchUser(word);
    }
}
