package com.zxdc.utils.library.bean;

import java.io.Serializable;

/**
 * 单页类型（0：关于我们，1：融资租赁）
 */
public class About extends BaseBean {

    private AboutBean data;

    public AboutBean getData() {
        return data;
    }

    public void setData(AboutBean data) {
        this.data = data;
    }

    public static class AboutBean implements Serializable{
        private String content;
        private String title;
        private String imgurl;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }
    }
}
