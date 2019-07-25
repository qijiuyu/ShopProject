package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 我的拼团详情
 */
public class MyTuanDetails extends BaseBean{

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        //团购活动id
        private int gid;
        //已发起的团购信息编号
        private String ugnum;
        //几人成团
        private int gcount;
        //已参团人数
        private int count;
        //本次团购状态 0：团购进行中，1：团购成功，2:团购失败
        private int status;
        //本次团购结束时间
        private String endtime;
        //商品id
        private int proid;
        //商品名称
        private String proname;
        //商品图片
        private String proimg;
        //商品活动价格
        private double price;
        //商品原价格
        private double oldprice;
        //支付方式 支付宝支付(6),微信支付(7)，对公支付(15)
        private int paytype;
        //运费
        private double freight;
        //还缺几人成团
        private int lackcount;
        private String paytime;
        private List<UserList> userslist;

        public String getPaytime() {
            return paytime;
        }

        public void setPaytime(String paytime) {
            this.paytime = paytime;
        }

        public int getGid() {
            return gid;
        }

        public void setGid(int gid) {
            this.gid = gid;
        }

        public String getUgnum() {
            return ugnum;
        }

        public void setUgnum(String ugnum) {
            this.ugnum = ugnum;
        }

        public int getGcount() {
            return gcount;
        }

        public void setGcount(int gcount) {
            this.gcount = gcount;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public int getProid() {
            return proid;
        }

        public void setProid(int proid) {
            this.proid = proid;
        }

        public String getProname() {
            return proname;
        }

        public void setProname(String proname) {
            this.proname = proname;
        }

        public String getProimg() {
            return proimg;
        }

        public void setProimg(String proimg) {
            this.proimg = proimg;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getOldprice() {
            return oldprice;
        }

        public void setOldprice(double oldprice) {
            this.oldprice = oldprice;
        }

        public int getPaytype() {
            return paytype;
        }

        public void setPaytype(int paytype) {
            this.paytype = paytype;
        }

        public double getFreight() {
            return freight;
        }

        public void setFreight(double freight) {
            this.freight = freight;
        }

        public int getLackcount() {
            return lackcount;
        }

        public void setLackcount(int lackcount) {
            this.lackcount = lackcount;
        }

        public List<UserList> getUserslist() {
            return userslist;
        }

        public void setUserslist(List<UserList> userslist) {
            this.userslist = userslist;
        }
    }


    public static class UserList implements Serializable{
        private String imgurl;
        private String nickname;
        //是否是团长 1-是 0-否
        private int islaunch;

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getIslaunch() {
            return islaunch;
        }

        public void setIslaunch(int islaunch) {
            this.islaunch = islaunch;
        }
    }
}
