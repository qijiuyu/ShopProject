package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 我的收藏
 */
public class Collection extends BaseBean {

    private List<DataList> data;

    public List<DataList> getData() {
        return data;
    }

    public void setData(List<DataList> data) {
        this.data = data;
    }

    public static class DataList implements Serializable{
        //商品spuid
        private int spuid;
        //商品图片
        private String imgurl;
        //商品名称
        private String name;
        //商品原价
        private double oldprice;
        //商品价格
        private double price;

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
    }
}
