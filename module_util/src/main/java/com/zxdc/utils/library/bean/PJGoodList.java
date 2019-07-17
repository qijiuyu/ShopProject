package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 配件商品的列表
 */
public class PJGoodList extends BaseBean {

    private List<GoodList> data;

    public List<GoodList> getData() {
        return data;
    }

    public void setData(List<GoodList> data) {
        this.data = data;
    }

    public static class GoodList implements Serializable{
        //商品spuid
        private int spuid;
        //图片地址
        private String img;
        //商品名称
        private String name;
        //商品价格
        private double price;
        //商品原价
        private double oldprice;

        public int getSpuid() {
            return spuid;
        }

        public void setSpuid(int spuid) {
            this.spuid = spuid;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
    }
}
