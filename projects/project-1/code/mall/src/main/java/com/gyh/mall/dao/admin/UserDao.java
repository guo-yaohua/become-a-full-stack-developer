package com.gyh.mall.dao.admin;

import com.gyh.mall.model.User;

import java.util.List;

public interface UserDao {
    List<User> allUser();

    void deleteUser(int id);

    List<User> searchUser(String word);
}
