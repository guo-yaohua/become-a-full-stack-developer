package com.gyh.mall.service;

import com.gyh.mall.dao.AdminUserDao;
import com.gyh.mall.dao.AdminUserDaoImpl;
import com.gyh.mall.model.User;

import java.util.List;

public class AdminUserServiceImpl implements AdminUserService{

    private AdminUserDao adminUserDao = new AdminUserDaoImpl();

    /**
     * 获取所有用户
     * @return
     */
    @Override
    public List<User> allUser() {
        return adminUserDao.allUser();
    }
}
