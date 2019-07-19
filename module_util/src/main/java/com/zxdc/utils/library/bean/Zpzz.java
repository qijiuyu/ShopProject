package com.zxdc.utils.library.bean;

import com.zxdc.utils.library.bean.BaseBean;

import java.io.Serializable;

/**
 * 增票资质
 */
public class Zpzz extends BaseBean {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        private String address;
        private String bankcode;
        private String bankname;
        private String companyname;
        private String createtime;
        private String licenseimg;
        private String loginaddress;
        private String loginphone;
        private String name;
        private String permitimg;
        private String phone;
        private int status;
        private String taxnum;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBankcode() {
            return bankcode;
        }

        public void setBankcode(String bankcode) {
            this.bankcode = bankcode;
        }

        public String getBankname() {
            return bankname;
        }

        public void setBankname(String bankname) {
            this.bankname = bankname;
        }

        public String getCompanyname() {
            return companyname;
        }

        public void setCompanyname(String companyname) {
            this.companyname = companyname;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getLicenseimg() {
            return licenseimg;
        }

        public void setLicenseimg(String licenseimg) {
            this.licenseimg = licenseimg;
        }

        public String getLoginaddress() {
            return loginaddress;
        }

        public void setLoginaddress(String loginaddress) {
            this.loginaddress = loginaddress;
        }

        public String getLoginphone() {
            return loginphone;
        }

        public void setLoginphone(String loginphone) {
            this.loginphone = loginphone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPermitimg() {
            return permitimg;
        }

        public void setPermitimg(String permitimg) {
            this.permitimg = permitimg;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTaxnum() {
            return taxnum;
        }

        public void setTaxnum(String taxnum) {
            this.taxnum = taxnum;
        }
    }
}
