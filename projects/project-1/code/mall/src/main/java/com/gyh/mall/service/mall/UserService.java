package com.gyh.mall.service.mall;

import com.gyh.mall.model.bo.mall.UserLoginBO;
import com.gyh.mall.model.bo.mall.UserSignupBO;
import com.gyh.mall.model.vo.mall.UserLoginVO;
import com.gyh.mall.model.vo.mall.UserSignupVO;

public interface UserService {
    int signup(UserSignupBO signupBo);

    UserLoginVO login(UserLoginBO loginBO);
}
