package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 收货地址
 */
public class OrderAddr extends BaseBean {

    private AddrBean data;

    public AddrBean getData() {
        return data;
    }

    public void setData(AddrBean data) {
        this.data = data;
    }

}
