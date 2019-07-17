package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 收货地址
 */
public class Address extends BaseBean {

    private List<AddressBean> data;

    public List<AddressBean> getData() {
        return data;
    }

    public void setData(List<AddressBean> data) {
        this.data = data;
    }

    public static class AddressBean implements Serializable{
        private int id;
        //详细地址
        private String address;
        //区号
        private String areacode;
        //区名称
        private String areaname;
        //市号
        private String citycode;
        //市名称
        private String cityname;
        //公司名称
        private String companyname;
        //是否设置默认
        private int isdefault;
        //手机号
        private String mobile;
        //收货人名称
        private String name;
        //省号
        private String provincecode;
        //省名称
        private String provincename;
        private int userid;

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

        public String getAreacode() {
            return areacode;
        }

        public void setAreacode(String areacode) {
            this.areacode = areacode;
        }

        public String getAreaname() {
            return areaname;
        }

        public void setAreaname(String areaname) {
            this.areaname = areaname;
        }

        public String getCitycode() {
            return citycode;
        }

        public void setCitycode(String citycode) {
            this.citycode = citycode;
        }

        public String getCityname() {
            return cityname;
        }

        public void setCityname(String cityname) {
            this.cityname = cityname;
        }

        public String getCompanyname() {
            return companyname;
        }

        public void setCompanyname(String companyname) {
            this.companyname = companyname;
        }

        public int getIsdefault() {
            return isdefault;
        }

        public void setIsdefault(int isdefault) {
            this.isdefault = isdefault;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProvincecode() {
            return provincecode;
        }

        public void setProvincecode(String provincecode) {
            this.provincecode = provincecode;
        }

        public String getProvincename() {
            return provincename;
        }

        public void setProvincename(String provincename) {
            this.provincename = provincename;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }
    }
}
