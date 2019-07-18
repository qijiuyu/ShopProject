package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 订单详情
 */
public class OrderDetails extends BaseBean{

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        //订单id
        private int orderid;
        //省名称
        private String provincename;
        //市名称
        private String cityname;
        //区名称
        private String areaname;
        //详细地址
        private String address;
        //收货人姓名
        private String consignee;
        //收货人电话
        private String telPhone;
        //买家留言
        private String remark;
        //订单编号
        private String ordercode;
        //下单时间
        private String orderDate;
        //支付方式(支付宝支付(6),微信支付(7),对公支付(15))
        private int payType;
        //商品金额
        private double price;
        //运费金额
        private double freight;
        //优惠金额
        private double discount;
        //总金额
        private double actualpay;
        //订单状态(待付款(0), 待发货(1), 待收货(2), 已完成(3),已取消(5))
        private int status;
        //是否评价(0:未评论 1:已评论)
        private int iscomment;
        //物流公司名称
        private String logisticname;
        //物流单号
        private String logisiticcode;
        //是否开发票(0:否 1:是)
        private int isinvoice;
        //发票类型(1普通发票 2电子发票 3增值税发票)
        private int invoiceType;
        //发票抬头
        private String invoiceTitle;
        //发票内容
        private String invoiceContent;
        //纳税人识别号
        private String taxnum;
        private List<OrderList> detaillist;

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public String getProvincename() {
            return provincename;
        }

        public void setProvincename(String provincename) {
            this.provincename = provincename;
        }

        public String getCityname() {
            return cityname;
        }

        public void setCityname(String cityname) {
            this.cityname = cityname;
        }

        public String getAreaname() {
            return areaname;
        }

        public void setAreaname(String areaname) {
            this.areaname = areaname;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getTelPhone() {
            return telPhone;
        }

        public void setTelPhone(String telPhone) {
            this.telPhone = telPhone;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getOrdercode() {
            return ordercode;
        }

        public void setOrdercode(String ordercode) {
            this.ordercode = ordercode;
        }

        public String getOrderDate() {
            return orderDate;
        }

        public void setOrderDate(String orderDate) {
            this.orderDate = orderDate;
        }

        public int getPayType() {
            return payType;
        }

        public void setPayType(int payType) {
            this.payType = payType;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getFreight() {
            return freight;
        }

        public void setFreight(double freight) {
            this.freight = freight;
        }

        public double getDiscount() {
            return discount;
        }

        public void setDiscount(double discount) {
            this.discount = discount;
        }

        public double getActualpay() {
            return actualpay;
        }

        public void setActualpay(double actualpay) {
            this.actualpay = actualpay;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getIscomment() {
            return iscomment;
        }

        public void setIscomment(int iscomment) {
            this.iscomment = iscomment;
        }

        public String getLogisticname() {
            return logisticname;
        }

        public void setLogisticname(String logisticname) {
            this.logisticname = logisticname;
        }

        public String getLogisiticcode() {
            return logisiticcode;
        }

        public void setLogisiticcode(String logisiticcode) {
            this.logisiticcode = logisiticcode;
        }

        public int getIsinvoice() {
            return isinvoice;
        }

        public void setIsinvoice(int isinvoice) {
            this.isinvoice = isinvoice;
        }

        public int getInvoiceType() {
            return invoiceType;
        }

        public void setInvoiceType(int invoiceType) {
            this.invoiceType = invoiceType;
        }

        public String getInvoiceTitle() {
            return invoiceTitle;
        }

        public void setInvoiceTitle(String invoiceTitle) {
            this.invoiceTitle = invoiceTitle;
        }

        public String getInvoiceContent() {
            return invoiceContent;
        }

        public void setInvoiceContent(String invoiceContent) {
            this.invoiceContent = invoiceContent;
        }

        public String getTaxnum() {
            return taxnum;
        }

        public void setTaxnum(String taxnum) {
            this.taxnum = taxnum;
        }

        public List<OrderList> getDetaillist() {
            return detaillist;
        }

        public void setDetaillist(List<OrderList> detaillist) {
            this.detaillist = detaillist;
        }
    }


    public static class OrderList implements Serializable{
        //子订单id
        private int detailid;
        //商品id
        private int productid;
        //商品图片
        private String productimg;
        //商品名称
        private String productname;
        //商品数量
        private int productcount;
        //商品价格
        private double productprice;
        //商品原价
        private double oldprice;
        //商品运费金额
        private double freight;

        public int getDetailid() {
            return detailid;
        }

        public void setDetailid(int detailid) {
            this.detailid = detailid;
        }

        public int getProductid() {
            return productid;
        }

        public void setProductid(int productid) {
            this.productid = productid;
        }

        public String getProductimg() {
            return productimg;
        }

        public void setProductimg(String productimg) {
            this.productimg = productimg;
        }

        public String getProductname() {
            return productname;
        }

        public void setProductname(String productname) {
            this.productname = productname;
        }

        public int getProductcount() {
            return productcount;
        }

        public void setProductcount(int productcount) {
            this.productcount = productcount;
        }

        public double getProductprice() {
            return productprice;
        }

        public void setProductprice(double productprice) {
            this.productprice = productprice;
        }

        public double getOldprice() {
            return oldprice;
        }

        public void setOldprice(double oldprice) {
            this.oldprice = oldprice;
        }

        public double getFreight() {
            return freight;
        }

        public void setFreight(double freight) {
            this.freight = freight;
        }
    }
}
