package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 增值服务的分类
 */
public class ZzfuType extends BaseBean {

    private List<dataBean> data;

    public List<dataBean> getData() {
        return data;
    }

    public void setData(List<dataBean> data) {
        this.data = data;
    }

    public static class dataBean implements Serializable{
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
