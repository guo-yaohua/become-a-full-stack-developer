package com.gyh.mall.dao.admin;

import com.alibaba.druid.util.StringUtils;
import com.gyh.mall.model.Admin;
import com.gyh.mall.model.bo.admin.AdminChangePwdBO;
import com.gyh.mall.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminDaoImpl implements AdminDao {

    /**
     * 根据输入值，查询数据库，返回 Admin 对象
     * @param admin
     * @return
     */
    @Override
    public Admin login(Admin admin) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Admin query = null;
        try {
            query = runner.query("select * from admin where email = ? and pwd = ?",
                    new BeanHandler<>(Admin.class),
                    admin.getEmail(),
                    admin.getPwd());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    /**
     * 查询所有管理员，返回 adminList
     * @return
     */
    @Override
    public List<Admin> allAdmins() {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Admin> admins = null;
        try {
            admins = runner.query("select * from admin", new BeanListHandler<Admin>(Admin.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return admins;
    }

    /**
     * 添加管理员，返回添加的 admin 对象
     * @param admin
     * @return
     */
    @Override
    public void addAdminss(Admin admin) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("insert into admin(email, pwd, nickname) values(? , ? , ?)",
                    admin.getEmail(),
                    admin.getPwd(),
                    admin.getNickname());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 删除管理员，返回删除的 admin 对象（仅包含 id 信息）
     * @param id
     * @return
     */
    @Override
    public Admin deleteAdmins(int id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Admin admin = new Admin();
        try {
            runner.update("delete from admin where id = ?", id);
            admin.setId(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return admin;
    }

    /**
     * 更新管理员信息，返回数据变动数目
     * @param admin
     * @return
     */
    @Override
    public void updateAdminss(Admin admin) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("update admin set email = ?, nickname = ?, pwd = ? where id = ?",
                    admin.getEmail(),
                    admin.getNickname(),
                    admin.getPwd(),
                    admin.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 获取管理员信息，返回 admin
     * @param id
     * @return
     */
    @Override
    public Admin getAdminsInfo(int id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Admin query = null;
        try {
            query = runner.query("select * from admin where id = ?",
                    new BeanHandler<>(Admin.class),
                    id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    /**
     * 查询管理员
     * @param admin
     * @return
     */
    @Override
    public List<Admin> getSearchAdmins(Admin admin) {
        Map<String, Object> result = getDynamicSql(admin);
        String sql = (String) result.get("sql");
        List<String> params = (List<String>) result.get("params");
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            List<Admin> admins = runner.query(sql, new BeanListHandler<Admin>(Admin.class), params.toArray());
            return admins;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * 修改管理员密码
     * @param changePwdBo
     */
    @Override
    public void changePwd(AdminChangePwdBO changePwdBo) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("update admin set pwd = ? where nickname = ? and pwd = ?",
                    changePwdBo.getNewPwd(),
                    changePwdBo.getAdminToken(),
                    changePwdBo.getOldPwd());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 动态 sql 思想
     * @param admin
     * @return
     */
    private Map<String, Object> getDynamicSql(Admin admin) {
        String base = "select * from admin where 1 = 1";
        List<String> params = new ArrayList<>();
        if (!StringUtils.isEmpty(admin.getEmail())) {
            base = base + " and email like ?";
            params.add("%" + admin.getEmail() + "%");
        }
        if (!StringUtils.isEmpty(admin.getNickname())) {
            base = base + " and nickname like ?";
            params.add("%" + admin.getNickname() + "%");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("sql", base);
        map.put("params", params);
        return map;
    }
}
