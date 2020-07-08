package com.gyh.mall.service.mall;

import com.gyh.mall.dao.mall.UserDao;
import com.gyh.mall.dao.mall.UserDaoImpl;
import com.gyh.mall.model.User;
import com.gyh.mall.model.bo.mall.UserLoginBO;
import com.gyh.mall.model.bo.mall.UserSignupBO;
import com.gyh.mall.model.vo.mall.UserLoginVO;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    /**
     * 注册
     * @param signupBo
     * @return
     */
    @Override
    public int signup(UserSignupBO signupBo) {
        User user = new User();
        user.setEmail(signupBo.getEmail());
        user.setNickname(signupBo.getNickname());
        user.setPwd(signupBo.getPwd());
        user.setRecipient(signupBo.getRecipient());
        user.setAddress(signupBo.getAddress());
        user.setPhone(signupBo.getPhone());
        return userDao.signup(user);
    }

    /**
     * 登录
     * @param loginBO
     * @return
     */
    @Override
    public UserLoginVO login(UserLoginBO loginBO) {
        return userDao.login(loginBO);
    }
}
