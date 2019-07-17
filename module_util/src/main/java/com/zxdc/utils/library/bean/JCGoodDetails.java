package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 机床商品详情
 */
public class JCGoodDetails extends BaseBean {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        //商品id
        private int spuid;
        //商品名称
        private String name;
        //商品描述
        private String discription;
        //是否收藏(0:否 1:是)
        private int iscollect;
        //商品价格
        private double price;
        //规格信息集合
        private List<machineAttrsList> machineAttrs=new ArrayList<>();

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

        public List<machineAttrsList> getMachineAttrs() {
            return machineAttrs;
        }

        public void setMachineAttrs(List<machineAttrsList> machineAttrs) {
            this.machineAttrs = machineAttrs;
        }
    }


    public static class machineAttrsList implements Serializable{
        //规格名称
        private String name;
        //规格起始价格
        private double price;
        //是否多选(0:否 1:是)
        private int ismany;
        //排版方向(0:横 1:竖 2:颜色)
        private String direction;
        //规格值集合
        private List<machineValueList> machineAttrValues=new ArrayList<>();

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

        public int getIsmany() {
            return ismany;
        }

        public void setIsmany(int ismany) {
            this.ismany = ismany;
        }

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        public List<machineValueList> getMachineAttrValues() {
            return machineAttrValues;
        }

        public void setMachineAttrValues(List<machineValueList> machineAttrValues) {
            this.machineAttrValues = machineAttrValues;
        }
    }


    public static class machineValueList implements Serializable{
        //规格值名称
        private String title;
        //规格值副标题
        private String subtitle;
        //规格值图片
        private String imgurl;
        //规格值价格
        private double price;
        //规格值图片集合
        private List<imgList> spuColorImgList=new ArrayList<>();

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
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

        public List<imgList> getSpuColorImgList() {
            return spuColorImgList;
        }

        public void setSpuColorImgList(List<imgList> spuColorImgList) {
            this.spuColorImgList = spuColorImgList;
        }
    }


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
}
