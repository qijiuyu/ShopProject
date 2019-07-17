package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 分类
 * 分类类型(1:机床分类 2:配件分类 3:增值服务分类 4:生意圈分类 5:机床商品分类)
 */
public class Type extends BaseBean {

    private List<Children> data;

    public List<Children> getData() {
        return data;
    }

    public void setData(List<Children> data) {
        this.data = data;
    }

    public static class Children implements Serializable{

        private List<TypeBean> children;
        private int id;
        private String img;
        private String name;

        public List<TypeBean> getChildren() {
            return children;
        }

        public void setChildren(List<TypeBean> children) {
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
