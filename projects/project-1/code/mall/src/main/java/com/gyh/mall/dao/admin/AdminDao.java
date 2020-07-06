package com.gyh.mall.dao.admin;

import com.gyh.mall.model.Admin;
import com.gyh.mall.model.bo.admin.AdminChangePwdBO;

import java.util.List;

public interface AdminDao {
    Admin login(Admin admin);

    List<Admin> allAdmins();

    void addAdminss(Admin admin);

    Admin deleteAdmins(int id);

    void updateAdminss(Admin admin);

    Admin getAdminsInfo(int id);

    List<Admin> getSearchAdmins(Admin admin);

    void changePwd(AdminChangePwdBO changePwdBo);
}
