package com.zxdc.utils.library.bean;

import java.io.Serializable;

public class OrderNum extends BaseBean {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        private int dfk;
        private int dfh;
        private int dsh;
        private int dpj;
        private int shsq;

        public int getDfk() {
            return dfk;
        }

        public void setDfk(int dfk) {
            this.dfk = dfk;
        }

        public int getDfh() {
            return dfh;
        }

        public void setDfh(int dfh) {
            this.dfh = dfh;
        }

        public int getDsh() {
            return dsh;
        }

        public void setDsh(int dsh) {
            this.dsh = dsh;
        }

        public int getDpj() {
            return dpj;
        }

        public void setDpj(int dpj) {
            this.dpj = dpj;
        }

        public int getShsq() {
            return shsq;
        }

        public void setShsq(int shsq) {
            this.shsq = shsq;
        }
    }
}
