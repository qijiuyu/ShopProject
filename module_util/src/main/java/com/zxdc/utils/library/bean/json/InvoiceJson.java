package com.zxdc.utils.library.bean.json;

import java.io.Serializable;

/**
 * 下单时的发票json
 */
public class InvoiceJson implements Serializable {

    //发票抬头类型(0:个人 1:单位)
    private int titletype;
    //个人发票抬头
    private String title;
    //单位名称
    private String companyname;
    //纳税人识别号
    private String taxnum;
    //发票内容(0:商品明细 1:商品类别)
    private int contenttype;
    //收票人姓名
    private String username;
    //收票人手机号
    private String phone;
    //收票地址
    private String address;

    public int getTitletype() {
        return titletype;
    }

    public void setTitletype(int titletype) {
        this.titletype = titletype;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getTaxnum() {
        return taxnum;
    }

    public void setTaxnum(String taxnum) {
        this.taxnum = taxnum;
    }

    public int getContenttype() {
        return contenttype;
    }

    public void setContenttype(int contenttype) {
        this.contenttype = contenttype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
