package com.zxdc.utils.library.bean;

import java.io.Serializable;

/**
 * 登录bean
 */
public class Login extends BaseBean{

    private LoginBean data;

    private String token;

    public LoginBean getData() {
        return data;
    }

    public void setData(LoginBean data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class LoginBean implements Serializable{
        private int id;
        //账号
        private String loginName;
        private int type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
