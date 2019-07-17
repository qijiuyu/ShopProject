package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 我的优惠券
 */
public class Coupon extends BaseBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        //优惠券id
        private int couponid;
        private int id;
        //使用对象类型 0-商品 3-通用
        private int couponusetype;
        //优惠券名称
        private String couponname;
        //优惠卷类型 0-通用 1-金额限制
        private int coupontype;
        //优惠券面值
        private double facevalue;
        //优惠券满减金额
        private double fullreductionvalue;
        //开始时间
        private String providetime;
        //结束时间
        private String outtime;

        public int getCouponid() {
            return couponid;
        }

        public void setCouponid(int couponid) {
            this.couponid = couponid;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCouponusetype() {
            return couponusetype;
        }

        public void setCouponusetype(int couponusetype) {
            this.couponusetype = couponusetype;
        }

        public String getCouponname() {
            return couponname;
        }

        public void setCouponname(String couponname) {
            this.couponname = couponname;
        }

        public int getCoupontype() {
            return coupontype;
        }

        public void setCoupontype(int coupontype) {
            this.coupontype = coupontype;
        }

        public double getFacevalue() {
            return facevalue;
        }

        public void setFacevalue(double facevalue) {
            this.facevalue = facevalue;
        }

        public double getFullreductionvalue() {
            return fullreductionvalue;
        }

        public void setFullreductionvalue(double fullreductionvalue) {
            this.fullreductionvalue = fullreductionvalue;
        }

        public String getProvidetime() {
            return providetime;
        }

        public void setProvidetime(String providetime) {
            this.providetime = providetime;
        }

        public String getOuttime() {
            return outtime;
        }

        public void setOuttime(String outtime) {
            this.outtime = outtime;
        }
    }

}
