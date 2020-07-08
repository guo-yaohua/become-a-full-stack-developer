package com.gyh.mall.dao.mall;

import com.gyh.mall.model.User;
import com.gyh.mall.model.bo.mall.UserLoginBO;
import com.gyh.mall.model.vo.mall.UserLoginVO;
import com.gyh.mall.model.vo.mall.UserSignupVO;

public interface UserDao {
    int signup(User user);

    UserLoginVO login(UserLoginBO loginBO);
}
