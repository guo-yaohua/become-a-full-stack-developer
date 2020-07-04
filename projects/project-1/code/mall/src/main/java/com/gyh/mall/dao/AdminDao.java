package com.gyh.mall.dao;

import com.gyh.mall.model.Admin;

import java.util.List;

public interface AdminDao {
    Admin login(Admin admin);

    List<Admin> allAdmins();

    int addAdminss(Admin admin);

    Admin deleteAdmins(int id);

    int updateAdminss(Admin admin);

    Admin getAdminsInfo(int id);

    List<Admin> getSearchAdmins(Admin admin);
}
