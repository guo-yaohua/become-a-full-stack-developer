package com.cskaoyan.login.service;

import com.cskaoyan.login.model.User;

public interface UserService {
    int register(User user);

    int login(User user);

    User query(String userId);
}
