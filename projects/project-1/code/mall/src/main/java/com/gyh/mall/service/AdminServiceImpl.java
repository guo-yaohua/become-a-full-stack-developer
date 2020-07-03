package com.gyh.mall.service;

import com.gyh.mall.dao.AdminDao;
import com.gyh.mall.dao.AdminDaoImpl;
import com.gyh.mall.model.Admin;
import com.gyh.mall.model.bo.AdminLoginBO;

import java.util.List;

public class AdminServiceImpl implements AdminService{

    private AdminDao adminDao = new AdminDaoImpl();
    @Override
    public Admin login(AdminLoginBO loginBO) {
        Admin admin = new Admin();
        admin.setEmail(loginBO.getEmail());
        admin.setPwd(loginBO.getPwd());

        return adminDao.login(admin);
    }

    @Override
    public List<Admin> allAdmins() {
        return adminDao.allAdmins();
    }
}
