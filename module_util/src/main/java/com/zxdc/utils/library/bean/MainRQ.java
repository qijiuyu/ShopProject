package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 首页人气推荐
 */
public class MainRQ extends BaseBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        //专题id
        private int topicid;
        //专题图片
        private String topicimg;
        private List<DataList> skulist;

        public int getTopicid() {
            return topicid;
        }

        public void setTopicid(int topicid) {
            this.topicid = topicid;
        }

        public String getTopicimg() {
            return topicimg;
        }

        public void setTopicimg(String topicimg) {
            this.topicimg = topicimg;
        }

        public List<DataList> getSkulist() {
            return skulist;
        }

        public void setSkulist(List<DataList> skulist) {
            this.skulist = skulist;
        }
    }


    public static class DataList implements Serializable{
        //商品名称
        private String name;
        //商品id
        private int spuid;
        //商品图片
        private String imgurl;
        //商品金额
        private double price;
        //商品原价
        private double oldprice;
        //商品类型(1:机床 2:配件)
        private int type;

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

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
