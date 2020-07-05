package com.gyh.mall.dao.admin;

import com.gyh.mall.model.Admin;
import com.gyh.mall.model.bo.admin.AdminChangePwdBo;

import java.util.List;

public interface AdminDao {
    Admin login(Admin admin);

    List<Admin> allAdmins();

    int addAdminss(Admin admin);

    Admin deleteAdmins(int id);

    int updateAdminss(Admin admin);

    Admin getAdminsInfo(int id);

    List<Admin> getSearchAdmins(Admin admin);

    int changePwd(AdminChangePwdBo changePwdBo);
}
