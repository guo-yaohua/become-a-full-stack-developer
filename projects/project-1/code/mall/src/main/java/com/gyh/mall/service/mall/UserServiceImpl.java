package com.gyh.mall.service.mall;

import com.gyh.mall.dao.mall.OrderDaoImpl;
import com.gyh.mall.dao.mall.UserDao;
import com.gyh.mall.dao.mall.UserDaoImpl;
import com.gyh.mall.model.User;
import com.gyh.mall.model.bo.mall.UserDataBO;
import com.gyh.mall.model.bo.mall.UserLoginBO;
import com.gyh.mall.model.bo.mall.UserPwdBO;
import com.gyh.mall.model.bo.mall.UserSignupBO;
import com.gyh.mall.model.vo.mall.UserDataVO;
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

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    @Override
    public UserDataVO data(String token) {
        User user = new OrderDaoImpl().getUserByNickame(token);

        UserDataVO dataVO = new UserDataVO(
                0,
                user.getId().toString(),
                user.getEmail(),
                user.getNickname(),
                user.getRecipient(),
                user.getAddress(),
                user.getPhone()
        );
        return dataVO;
    }

    /**
     * 修改用户信息
     * @param dataBO
     */
    @Override
    public void updateUserData(UserDataBO dataBO) {
        userDao.updateUserData(dataBO);
    }

    /**
     * 修改用户密码
     * @param pwdBO
     */
    @Override
    public void updatePwd(UserPwdBO pwdBO) {
        userDao.updatePwd(pwdBO);
    }
}
