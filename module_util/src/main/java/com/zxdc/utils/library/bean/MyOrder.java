package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 我的订单
 */
public class MyOrder extends BaseBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        //订单id
        private int oid;
        //订单编号
        private String code;
        //下单时间
        private String orderdate;
        //订单状态(待付款(0), 待发货(1), 待收货(2), 已完成(3),已取消(5))
        private int status;
        //总件数
        private int ocount;
        //订单金额
        private double payprice;
        //支付类型(6:支付宝支付 7:微信支付 15:对公支付)
        private int paytype;
        private List<OrderList> pros=new ArrayList<>();

        public int getOid() {
            return oid;
        }

        public void setOid(int oid) {
            this.oid = oid;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getOrderdate() {
            return orderdate;
        }

        public void setOrderdate(String orderdate) {
            this.orderdate = orderdate;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getOcount() {
            return ocount;
        }

        public void setOcount(int ocount) {
            this.ocount = ocount;
        }

        public double getPayprice() {
            return payprice;
        }

        public void setPayprice(double payprice) {
            this.payprice = payprice;
        }

        public int getPaytype() {
            return paytype;
        }

        public void setPaytype(int paytype) {
            this.paytype = paytype;
        }

        public List<OrderList> getPros() {
            return pros;
        }

        public void setPros(List<OrderList> pros) {
            this.pros = pros;
        }
    }


    public static class OrderList implements Serializable{
        //商品id
        private int spuid;
        //商品名称
        private String proname;
        //商品图片
        private String proimg;
        //商品规格值信息
        private String specsvalue;

        public int getSpuid() {
            return spuid;
        }

        public void setSpuid(int spuid) {
            this.spuid = spuid;
        }

        public String getProname() {
            return proname;
        }

        public void setProname(String proname) {
            this.proname = proname;
        }

        public String getProimg() {
            return proimg;
        }

        public void setProimg(String proimg) {
            this.proimg = proimg;
        }

        public String getSpecsvalue() {
            return specsvalue;
        }

        public void setSpecsvalue(String specsvalue) {
            this.specsvalue = specsvalue;
        }
    }
}
