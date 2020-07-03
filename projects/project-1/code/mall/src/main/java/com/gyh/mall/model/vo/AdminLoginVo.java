package com.gyh.mall.model.vo;

public class AdminLoginVo {

    private String token;

    private String name;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AdminLoginVo{" +
                "token='" + token + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
