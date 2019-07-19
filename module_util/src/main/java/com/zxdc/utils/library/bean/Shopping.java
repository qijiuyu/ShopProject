package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车
 */
public class Shopping extends BaseBean {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        //总价格
        private double allmoney;
        private List<goodList> cartInfos;

        public double getAllmoney() {
            return allmoney;
        }

        public void setAllmoney(double allmoney) {
            this.allmoney = allmoney;
        }

        public List<goodList> getCartInfos() {
            return cartInfos;
        }

        public void setCartInfos(List<goodList> cartInfos) {
            this.cartInfos = cartInfos;
        }
    }


    public static class goodList implements Serializable{
        //购物车id
        private int cartid;
        //商品图片
        private String img;
        //商品数量
        private int procount;
        //商品名称
        private String proname;
        //商品skuid
        private int skuid;
        //商品spuid
        private int spuid;
        //规格起始价格
        private double price;
        //商品原价
        private double oldprice;
        //运费
        private double freigth;
        //商品规格值
        private String specsvalue;
        //是否选择(0:否 1:是)
        private int isselect;

        public int getCartid() {
            return cartid;
        }

        public void setCartid(int cartid) {
            this.cartid = cartid;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getProcount() {
            return procount;
        }

        public void setProcount(int procount) {
            this.procount = procount;
        }

        public String getProname() {
            return proname;
        }

        public void setProname(String proname) {
            this.proname = proname;
        }

        public int getSkuid() {
            return skuid;
        }

        public void setSkuid(int skuid) {
            this.skuid = skuid;
        }

        public int getSpuid() {
            return spuid;
        }

        public void setSpuid(int spuid) {
            this.spuid = spuid;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getSpecsvalue() {
            return specsvalue;
        }

        public void setSpecsvalue(String specsvalue) {
            this.specsvalue = specsvalue;
        }

        public int getIsselect() {
            return isselect;
        }

        public void setIsselect(int isselect) {
            this.isselect = isselect;
        }

        public double getOldprice() {
            return oldprice;
        }

        public void setOldprice(double oldprice) {
            this.oldprice = oldprice;
        }

        public double getFreigth() {
            return freigth;
        }

        public void setFreigth(double freigth) {
            this.freigth = freigth;
        }
    }
}
