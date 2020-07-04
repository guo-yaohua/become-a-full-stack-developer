package com.gyh.mall.service;

import com.gyh.mall.dao.MallUserDao;
import com.gyh.mall.dao.MallUserDaoImpl;
import com.gyh.mall.model.User;
import com.gyh.mall.model.bo.MallUserSignupBo;

public class MallUserServiceImpl implements MallUserService {

    private MallUserDao mallUserDao = new MallUserDaoImpl();

    @Override
    public int signup(MallUserSignupBo signupBo) {
        User user = new User();
        user.setEmail(signupBo.getEmail());
        user.setNickname(signupBo.getNickname());
        user.setPwd(signupBo.getPwd());
        user.setRecipient(signupBo.getRecipient());
        user.setAddress(signupBo.getAddress());
        user.setPhone(signupBo.getPhone());
        return mallUserDao.signup(user);
    }
}
