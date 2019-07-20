package com.zxdc.utils.library.bean.json;

import java.io.Serializable;
import java.util.List;

/**
 * 下单时的订单json
 */
public class OrderJson implements Serializable{
    //订单总金额
    private double totalMoney;
    //优惠金额
    private double delMoney=0;
    //优惠券id
    private int couponID;
    //运费金额
    private double freightMoney;
    //是否要发票(0不要发票 1要发票)
    private int isInvoice;
    //订单备注
    private String remark;
    private List<skuList> details;

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public double getDelMoney() {
        return delMoney;
    }

    public void setDelMoney(double delMoney) {
        this.delMoney = delMoney;
    }

    public int getCouponID() {
        return couponID;
    }

    public void setCouponID(int couponID) {
        this.couponID = couponID;
    }

    public double getFreightMoney() {
        return freightMoney;
    }

    public void setFreightMoney(double freightMoney) {
        this.freightMoney = freightMoney;
    }

    public int getIsInvoice() {
        return isInvoice;
    }

    public void setIsInvoice(int isInvoice) {
        this.isInvoice = isInvoice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<skuList> getDetails() {
        return details;
    }

    public void setDetails(List<skuList> details) {
        this.details = details;
    }

    public static class skuList implements Serializable{

        private int skuID;
        private int proCount;
        private int spuID;

        public skuList(){}

        public skuList(int skuID,int proCount,int spuID){
            this.skuID=skuID;
            this.proCount=proCount;
            this.spuID=spuID;
        }

        public int getSkuID() {
            return skuID;
        }

        public void setSkuID(int skuID) {
            this.skuID = skuID;
        }

        public int getProCount() {
            return proCount;
        }

        public void setProCount(int proCount) {
            this.proCount = proCount;
        }

        public int getSpuID() {
            return spuID;
        }

        public void setSpuID(int spuID) {
            this.spuID = spuID;
        }
    }
}
