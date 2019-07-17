package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 配件商城的分类
 */
public class PJType extends BaseBean {

    private List<dataBean> data;

    public List<dataBean> getData() {
        return data;
    }

    public void setData(List<dataBean> data) {
        this.data = data;
    }

    public static class dataBean implements Serializable{
        private List<Children> children;
        private int id;
        private String img;
        private String name;

        public List<Children> getChildren() {
            return children;
        }

        public void setChildren(List<Children> children) {
            this.children = children;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Children implements Serializable{

        private List<PJType.TypeBean> children;
        private int id;
        private int pid;
        private String img;
        private String name;

        public List<PJType.TypeBean> getChildren() {
            return children;
        }

        public void setChildren(List<PJType.TypeBean> children) {
            this.children = children;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    public static class TypeBean implements Serializable{
        private int id;
        private String img;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
