package com.gyh.mall.service;

import com.gyh.mall.model.Admin;
import com.gyh.mall.model.bo.AdminLoginBO;

import java.util.List;

public interface AdminService {
    Admin login(AdminLoginBO loginBO);

    List<Admin> allAdmins();
}
