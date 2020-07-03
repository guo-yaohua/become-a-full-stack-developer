package com.gyh.mall.service;

import com.gyh.mall.model.Admin;
import com.gyh.mall.model.bo.AdminAddBo;
import com.gyh.mall.model.bo.AdminLoginBo;

import java.util.List;

public interface AdminService {
    Admin login(AdminLoginBo loginBO);

    List<Admin> allAdmins();

    int addAdminss(AdminAddBo addBo);

    Admin deleteAdmins(int id);
}
