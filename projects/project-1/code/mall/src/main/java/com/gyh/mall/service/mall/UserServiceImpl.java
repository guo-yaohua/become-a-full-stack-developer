package com.gyh.mall.service.mall;

import com.gyh.mall.dao.mall.UserDao;
import com.gyh.mall.dao.mall.UserDaoImpl;
import com.gyh.mall.model.User;
import com.gyh.mall.model.bo.mall.MallUserSignupBO;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public int signup(MallUserSignupBO signupBo) {
        User user = new User();
        user.setEmail(signupBo.getEmail());
        user.setNickname(signupBo.getNickname());
        user.setPwd(signupBo.getPwd());
        user.setRecipient(signupBo.getRecipient());
        user.setAddress(signupBo.getAddress());
        user.setPhone(signupBo.getPhone());
        return userDao.signup(user);
    }
}
