package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 生意圈详情
 */
public class BusinessDetails extends BaseBean {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        private int id;
        //位置信息
        private String address;
        //评论数
        private int commentcount;
        //评论集合
        private List<BusinessDetails.Common> commentlist=new ArrayList<>();
        //公司名称
        private String companyname;
        //生意圈内容
        private String content;
        //创建时间
        private String createtime;
        //距离（km）
        private String distance;
        //图片列表
        private String[] imglist;
        //是否点赞
        private int ispraise;
        //是否置顶
        private int istop;
        //用户昵称
        private String nickname;
        //公司电话
        private String phone;
        //点赞昵称列表
        private String[] praiselist;
        //分类名称
        private String typename;
        //用户头像
        private String userimg;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getCommentcount() {
            return commentcount;
        }

        public void setCommentcount(int commentcount) {
            this.commentcount = commentcount;
        }

        public List<Common> getCommentlist() {
            return commentlist;
        }

        public void setCommentlist(List<Common> commentlist) {
            this.commentlist = commentlist;
        }

        public String getCompanyname() {
            return companyname;
        }

        public void setCompanyname(String companyname) {
            this.companyname = companyname;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String[] getImglist() {
            return imglist;
        }

        public void setImglist(String[] imglist) {
            this.imglist = imglist;
        }

        public int getIspraise() {
            return ispraise;
        }

        public void setIspraise(int ispraise) {
            this.ispraise = ispraise;
        }

        public int getIstop() {
            return istop;
        }

        public void setIstop(int istop) {
            this.istop = istop;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String[] getPraiselist() {
            return praiselist;
        }

        public void setPraiselist(String[] praiselist) {
            this.praiselist = praiselist;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public String getUserimg() {
            return userimg;
        }

        public void setUserimg(String userimg) {
            this.userimg = userimg;
        }
    }

    public static class Common implements Serializable{
        //评论的内容
        private String content;
        //评论者
        private String nickname;
        private String createtime;
        private String imgurl;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
