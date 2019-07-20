package com.zxdc.utils.library.bean.json;

import java.io.Serializable;
import java.util.List;

/**
 * 下单页的优惠券json
 */
public class YhqJson implements Serializable {

    private double money;
    private List<goodList> prolist;

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public List<goodList> getProlist() {
        return prolist;
    }

    public void setProlist(List<goodList> prolist) {
        this.prolist = prolist;
    }

    public static class goodList implements Serializable{
        private int proid;
        private double promoney;

        public int getProid() {
            return proid;
        }

        public void setProid(int proid) {
            this.proid = proid;
        }

        public double getPromoney() {
            return promoney;
        }

        public void setPromoney(double promoney) {
            this.promoney = promoney;
        }
    }
}
