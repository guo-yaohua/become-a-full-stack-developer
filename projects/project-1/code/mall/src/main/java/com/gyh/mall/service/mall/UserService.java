package com.gyh.mall.service.mall;

import com.gyh.mall.model.bo.mall.UserDataBO;
import com.gyh.mall.model.bo.mall.UserLoginBO;
import com.gyh.mall.model.bo.mall.UserPwdBO;
import com.gyh.mall.model.bo.mall.UserSignupBO;
import com.gyh.mall.model.vo.mall.UserDataVO;
import com.gyh.mall.model.vo.mall.UserLoginVO;

public interface UserService {
    int signup(UserSignupBO signupBo);

    UserLoginVO login(UserLoginBO loginBO);


    UserDataVO data(String token);

    void updateUserData(UserDataBO dataBO);

    void updatePwd(UserPwdBO pwdBO);
}
