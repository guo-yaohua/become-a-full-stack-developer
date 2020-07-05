package com.gyh.mall.service;

import com.gyh.mall.model.User;

import java.util.List;

public interface AdminUserService {
    List<User> allUser();

    void deleteUser(int id);

    List<User> searchUser(String word);
}
