package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 筛选
 */
public class Secreening extends BaseBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        //规格名称
        private String name;
        //是否多选(0:否 1:是)
        private int ischeckbox;
        private List<searchValsList> searchVals;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIscheckbox() {
            return ischeckbox;
        }

        public void setIscheckbox(int ischeckbox) {
            this.ischeckbox = ischeckbox;
        }

        public List<searchValsList> getSearchVals() {
            return searchVals;
        }

        public void setSearchVals(List<searchValsList> searchVals) {
            this.searchVals = searchVals;
        }
    }


    public static class searchValsList implements Serializable{
        private String valname;

        public String getValname() {
            return valname;
        }

        public void setValname(String valname) {
            this.valname = valname;
        }
    }
}
