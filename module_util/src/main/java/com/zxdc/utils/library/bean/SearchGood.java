package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 关键字搜索机床，配件
 */
public class SearchGood extends BaseBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        //商品分类id
        private int classid;
        //创建时间
        private String createtime;
        //商品描述
        private String description;
        //商品运费金额
        private double freight;
        //商品id
        private int id;
        //商品图片
        private String imgurl;
        //商品名称
        private String name;
        //商品原价
        private double oldprice;
        //商品价格
        private double price;
        //商品状态(0:下架 1:上架)
        private int status;
        private String subtitle;

        public int getClassid() {
            return classid;
        }

        public void setClassid(int classid) {
            this.classid = classid;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public double getFreight() {
            return freight;
        }

        public void setFreight(double freight) {
            this.freight = freight;
        }

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getOldprice() {
            return oldprice;
        }

        public void setOldprice(double oldprice) {
            this.oldprice = oldprice;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }
    }
}
