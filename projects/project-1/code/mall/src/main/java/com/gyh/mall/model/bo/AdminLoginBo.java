package com.gyh.mall.model.bo;

/**
 * bo:business object
 * 请求报文中需要用到的对象
 * vo:view object
 * 响应报文中需要用到的对象
 */
public class AdminLoginBo {

    private String email;

    private String pwd;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "AdminLoginBO{" +
                "email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
