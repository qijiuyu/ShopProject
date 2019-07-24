package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 配件商品详情
 */
public class PJGoodDetails extends BaseBean {

    private goodBean data;

    public goodBean getData() {
        return data;
    }

    public void setData(goodBean data) {
        this.data = data;
    }

    public static class goodBean implements Serializable{

        //商品id
        private int spuid;
        //商品名称
        private String name;
        //商品图片
        private String img;
        //商品描述
        private String discription;
        //是否收藏(0:否 1:是)
        private int iscollect;
        //商品价格
        private double price;
        //商品原价
        private double oldprice;
        //商品副标题
        private String subtitle;
        //商品图片集合
        private List<imgList> spuImgList=new ArrayList<>();
        //商品规格集合
        private List<proSpecsBean> proSpecsList=new ArrayList<>();
        //商品sku集合
        private List<skuBean> skuList=new ArrayList<>();

        //运费
        private double freigth;

        //前台自己设置商品数量
        private int count;

        //选中的skuid
        private int skuid;

        public double getFreigth() {
            return freigth;
        }

        public void setFreigth(double freigth) {
            this.freigth = freigth;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getSpuid() {
            return spuid;
        }

        public void setSpuid(int spuid) {
            this.spuid = spuid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDiscription() {
            return discription;
        }

        public void setDiscription(String discription) {
            this.discription = discription;
        }

        public int getIscollect() {
            return iscollect;
        }

        public void setIscollect(int iscollect) {
            this.iscollect = iscollect;
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

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public List<imgList> getSpuImgList() {
            return spuImgList;
        }

        public void setSpuImgList(List<imgList> spuImgList) {
            this.spuImgList = spuImgList;
        }

        public List<proSpecsBean> getProSpecsList() {
            return proSpecsList;
        }

        public void setProSpecsList(List<proSpecsBean> proSpecsList) {
            this.proSpecsList = proSpecsList;
        }

        public List<skuBean> getSkuList() {
            return skuList;
        }

        public void setSkuList(List<skuBean> skuList) {
            this.skuList = skuList;
        }

        public int getSkuid() {
            return skuid;
        }

        public void setSkuid(int skuid) {
            this.skuid = skuid;
        }
    }


    //图片集合
    public static class imgList implements Serializable{
        //图片类型(0:图片 1:视频)
        private int type;
        //链接地址
        private String url;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }


    public static class skuBean implements Serializable{
        //商品skuid
        private int id;
        //商品sku价格
        private double price;
        //商品库存数量
        private int stock;
        private tgdtoBean tgdto;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public tgdtoBean getTgdto() {
            return tgdto;
        }

        public void setTgdto(tgdtoBean tgdto) {
            this.tgdto = tgdto;
        }
    }


    public static class tgdtoBean implements Serializable{
        private int tgid;
        private int tgcount;
        private double tgprice;
        private String starttime;
        private String endtime;

        public int getTgid() {
            return tgid;
        }

        public void setTgid(int tgid) {
            this.tgid = tgid;
        }

        public int getTgcount() {
            return tgcount;
        }

        public void setTgcount(int tgcount) {
            this.tgcount = tgcount;
        }

        public double getTgprice() {
            return tgprice;
        }

        public void setTgprice(double tgprice) {
            this.tgprice = tgprice;
        }

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }
    }


    public static class proSpecsBean implements Serializable{
        //商品规格名称id
        private int specsid;
        //商品规格名称
        private String specsname;
        private List<proSpecsVal> proSpecsVals=new ArrayList<>();

        public int getSpecsid() {
            return specsid;
        }

        public void setSpecsid(int specsid) {
            this.specsid = specsid;
        }

        public String getSpecsname() {
            return specsname;
        }

        public void setSpecsname(String specsname) {
            this.specsname = specsname;
        }

        public List<proSpecsVal> getProSpecsVals() {
            return proSpecsVals;
        }

        public void setProSpecsVals(List<proSpecsVal> proSpecsVals) {
            this.proSpecsVals = proSpecsVals;
        }
    }

    public  static class proSpecsVal implements Serializable{
        //商品skuid
        private String skuid;
        //商品规格值名称
        private String valuename;
        //是否默认选中(0:否 1:是)
        private int ischeck;

        public String getSkuid() {
            return skuid;
        }

        public void setSkuid(String skuid) {
            this.skuid = skuid;
        }

        public String getValuename() {
            return valuename;
        }

        public void setValuename(String valuename) {
            this.valuename = valuename;
        }

        public int getIscheck() {
            return ischeck;
        }

        public void setIscheck(int ischeck) {
            this.ischeck = ischeck;
        }
    }
}
