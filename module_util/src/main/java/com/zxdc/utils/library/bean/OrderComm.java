package com.zxdc.utils.library.bean;

import java.io.Serializable;

/**
 * 订单评价内容
 */
public class OrderComm extends BaseBean {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        //评价图片(多图片用,分隔)
        private String imgurl;
        //商品名称
        private String name;
        //商品id
        private int sid;
        private String supimgurl;
        private int starnum;
        private String detail;

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }

        public String getSupimgurl() {
            return supimgurl;
        }

        public void setSupimgurl(String supimgurl) {
            this.supimgurl = supimgurl;
        }

        public int getStarnum() {
            return starnum;
        }

        public void setStarnum(int starnum) {
            this.starnum = starnum;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }
    }
}
