package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 收货地址
 */
public class Address extends BaseBean {

    private List<AddrBean> data;

    public List<AddrBean> getData() {
        return data;
    }

    public void setData(List<AddrBean> data) {
        this.data = data;
    }

}
