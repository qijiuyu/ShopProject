package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 首页广告
 */
public class Abvert extends BaseBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        //广告id
        private int id;
        //广告图片
        private String imgurl;
        //跳转类型(0:无跳转 1:跳转外链 2:跳转机床商品)
        private int jumptype;
        //广告名称
        private String name;
        //商品id
        private int spuid;
        //跳转外链地址
        private String url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public int getJumptype() {
            return jumptype;
        }

        public void setJumptype(int jumptype) {
            this.jumptype = jumptype;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSpuid() {
            return spuid;
        }

        public void setSpuid(int spuid) {
            this.spuid = spuid;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
