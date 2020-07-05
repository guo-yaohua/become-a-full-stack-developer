package com.gyh.mall.service.admin;

import com.gyh.mall.model.Admin;
import com.gyh.mall.model.bo.admin.*;

import java.util.List;

public interface AdminService {
    Admin login(AdminLoginBo loginBO);

    List<Admin> allAdmins();

    int addAdminss(AdminAddBo addBo);

    Admin deleteAdmins(int id);

    int updateAdminss(AdminUpdateBo updateBo);

    Admin getAdminsInfo(int id);

    List<Admin> getSearchAdmins(AdminSearchBo searchBo);

    int changePwd(AdminChangePwdBo changePwdBo);
}
