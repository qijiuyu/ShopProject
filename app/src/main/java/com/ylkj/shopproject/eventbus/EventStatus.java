package com.ylkj.shopproject.eventbus;

public class EventStatus {
    //配件商城分类下的列表，选择默认等
    public static final int PJSC_SELECT_TYPE=101;

    //微信支付成功
    public static final int WX_RECHARGE=102;

    //选择城市
    public static final int GET_CITY=103;

    //收货地址设置为默认
    public static final int SET_ADDR_DEFAULT=104;

    //删除收货地址
    public static final int DEL_ADDR=105;

    //修改地址
    public static final int UPD_ADDR=106;

    //获取配件商品列表
    public static final int GET_PJ_LIST1=107;
    public static final int GET_PJ_LIST2=108;

    //取消商品收藏
    public static final int CANCLE_COLLECTION=109;

    //查询购物车数据
    public static final int GET_CAR_LIST=110;

    //修改购物车数量
    public static final int CHANGE_CAR_COUNT=112;

    //获取配件商品详情
    public static final int GET_PJ_DETAILS=113;

    //商品收藏成功
    public static final int COLLECTION_SUCCESS=114;

    //获取机床商品详情
    public static final int GET_JC_DETAILS=115;

    //选择对应的颜色
    public static final int SELECT_JC_COLOR=116;

    //领取优惠券
    public static final int TAKE_COUPON=117;

    //购物车选中了几个
    public static final int SELECT_CAR_GOODS=118;
}
