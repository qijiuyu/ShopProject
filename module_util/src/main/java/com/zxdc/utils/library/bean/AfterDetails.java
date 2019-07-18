package com.zxdc.utils.library.bean;

/**
 * 申请售后的详情
 */
public class AfterDetails extends BaseBean {
    //子订单id
    private int id;
    //订单id
    private int orderid;
    //商品图片
    private String proimg;
    //商品名称
    private String proname;
    //商品id
    private int spuid;
    //申请时间
    private String applytime;
    //售后状态(待审核(0), 审核通过(1), 审核不通过(-1), 商家待收货(2), 商家待发货(3), 买家待收货(4), 售后完成(5))
    private int afstatus;
    //售后申请类型(4-退货 5-换货)
    private int type;
    //价格
    private double proprice;
    //商品数量
    private int procount;
    //图片集合(用,分隔)
    private String imgs;
    //问题描述
    private String reason;
    //门店地址
    private String shopname;
    //寄回地址
    private String address;
    //联系电话
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getProimg() {
        return proimg;
    }

    public void setProimg(String proimg) {
        this.proimg = proimg;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public int getSpuid() {
        return spuid;
    }

    public void setSpuid(int spuid) {
        this.spuid = spuid;
    }

    public String getApplytime() {
        return applytime;
    }

    public void setApplytime(String applytime) {
        this.applytime = applytime;
    }

    public int getAfstatus() {
        return afstatus;
    }

    public void setAfstatus(int afstatus) {
        this.afstatus = afstatus;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getProprice() {
        return proprice;
    }

    public void setProprice(double proprice) {
        this.proprice = proprice;
    }

    public int getProcount() {
        return procount;
    }

    public void setProcount(int procount) {
        this.procount = procount;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
