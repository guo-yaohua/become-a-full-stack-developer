package com.gyh.mall.dao;

import com.gyh.mall.model.User;

import java.util.List;

public interface AdminUserDao {
    List<User> allUser();

    void deleteUser(int id);
}
