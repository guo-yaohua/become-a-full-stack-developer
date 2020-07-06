package com.gyh.mall.service.admin;

import com.gyh.mall.model.Admin;
import com.gyh.mall.model.bo.admin.*;

import java.util.List;

public interface AdminService {
    Admin login(AdminLoginBO loginBO);

    List<Admin> allAdmins();

    void addAdminss(AdminAddBO addBo);

    Admin deleteAdmins(int id);

    void updateAdminss(AdminUpdateBO updateBo);

    Admin getAdminsInfo(int id);

    List<Admin> getSearchAdmins(AdminSearchBO searchBo);

    void changePwd(AdminChangePwdBO changePwdBo);
}
