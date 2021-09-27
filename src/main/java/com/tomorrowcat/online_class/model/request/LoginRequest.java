package com.tomorrowcat.online_class.model.request;

/**
 * @description: 登录  dto
 * @author: kim
 * @create: 2021-03-28 08:33
 * @version: 1.0.0
 */
public class LoginRequest {
    private String phone;

    private String pwd;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}