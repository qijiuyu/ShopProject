package com.zxdc.utils.library.bean;

import java.io.Serializable;

/**
 * 机构认证
 */
public class Certification extends BaseBean {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        private int id;
        private String address;
        private String instname;
        private String createtime;
        private String licenseimg;
        private String name;
        private String permitimg;
        private String phone;
        private int status;
        private String proname;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getInstname() {
            return instname;
        }

        public void setInstname(String instname) {
            this.instname = instname;
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

        public String getProname() {
            return proname;
        }

        public void setProname(String proname) {
            this.proname = proname;
        }
    }
}
