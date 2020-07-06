package com.gyh.mall.service.admin;

import com.gyh.mall.dao.admin.AdminDao;
import com.gyh.mall.dao.admin.AdminDaoImpl;
import com.gyh.mall.model.Admin;
import com.gyh.mall.model.bo.admin.*;

import java.util.List;

public class AdminServiceImpl implements AdminService{

    private AdminDao adminDao = new AdminDaoImpl();
    @Override
    public Admin login(AdminLoginBO loginBo) {
        Admin admin = new Admin();
        admin.setEmail(loginBo.getEmail());
        admin.setPwd(loginBo.getPwd());

        return adminDao.login(admin);
    }

    @Override
    public List<Admin> allAdmins() {
        return adminDao.allAdmins();
    }

    @Override
    public void addAdminss(AdminAddBO addBo) {
        Admin admin = new Admin();
        admin.setEmail(addBo.getEmail());
        admin.setNickname(addBo.getNickname());
        admin.setPwd(addBo.getPwd());

        adminDao.addAdminss(admin);
    }

    @Override
    public Admin deleteAdmins(int id) {
        return adminDao.deleteAdmins(id);
    }

    @Override
    public void updateAdminss(AdminUpdateBO updateBo) {
        Admin admin = new Admin();
        admin.setId(updateBo.getId());
        admin.setEmail(updateBo.getEmail());
        admin.setNickname(updateBo.getNickname());
        admin.setPwd(updateBo.getPwd());

        adminDao.updateAdminss(admin);
    }

    @Override
    public Admin getAdminsInfo(int id) {
        return adminDao.getAdminsInfo(id);
    }

    @Override
    public List<Admin> getSearchAdmins(AdminSearchBO searchBo) {
        Admin admin = new Admin();
        admin.setNickname(searchBo.getNickname());
        admin.setEmail(searchBo.getEmail());
        return adminDao.getSearchAdmins(admin);
    }

    @Override
    public void changePwd(AdminChangePwdBO changePwdBo) {
        adminDao.changePwd(changePwdBo);
    }
}
