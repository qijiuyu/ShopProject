package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class City extends BaseBean {

    private List<CityBean> data;

    public List<CityBean> getData() {
        return data;
    }

    public void setData(List<CityBean> data) {
        this.data = data;
    }

    public static class CityBean implements Serializable{
        private String code;
        private String name;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
