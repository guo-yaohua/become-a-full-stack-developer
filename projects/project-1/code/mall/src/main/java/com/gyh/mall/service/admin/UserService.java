package com.gyh.mall.service.admin;

import com.gyh.mall.model.User;

import java.util.List;

public interface UserService {
    List<User> allUser();

    void deleteUser(int id);

    List<User> searchUser(String word);
}
