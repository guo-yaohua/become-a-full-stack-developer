package com.cskaoyan.login.dao;

import com.cskaoyan.login.model.User;

public interface UserDao {
    boolean isUsernameExists(User user);

    void register(User user);

    int login(User user);

    User query(String userId);
}
