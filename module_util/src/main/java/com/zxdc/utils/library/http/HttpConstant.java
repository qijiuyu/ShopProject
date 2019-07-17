package com.zxdc.utils.library.http;

/**
 * 主分支
 */
public class HttpConstant {
    //¥

    //测试IP
     public static final String IP = "http://htm.yl-mall.cn/";

    //获取短信验证码
    public static final String GET_SMS_CODE = "app/api/sms/send";

    //注册接口
    public static final String REGISTER="api/app/user/register";

    //登录
    public static final String LOGIN="api/app/user/login";

    //获取省份
    public static final String GET_PROVICE="api/app/addr/selectAllProvice";

    //获取市
    public static final String GET_CITY="api/app/addr/selectAllCity";

    //获取区数据
    public static final String GET_AREA="api/app/addr/selectAllArea";

    //添加收货地址
    public static final String ADD_ADDRESS="api/app/receiveaddress/add";

    //获取收货地址列表
    public static final String GET_ADDR_LIST="api/app/receiveaddress/list";

    //修改默认收货地址
    public static final String SET_ADDR_DEFAULT="api/app/receiveaddress/upisdef";

    //删除收货地址
    public static final String DEL_ADDR="api/app/receiveaddress/del";

    //修改收货地址
    public static final String UPD_ADDR="api/app/receiveaddress/upd";

    //设置增票资质
    public static final String ADD_ZPZZ="api/app/invoicevat/add";

    //意见反馈
    public static final String FEED_BACK="api/app/user/feedback";

    //设置企业信息
    public static final String EDIT_COMPANY="api/app/company/add";

    //分类类型(1:机床分类 2:配件分类 3:增值服务分类 4:生意圈分类 5:机床商品分类)
    public static final String GET_TYPE="api/app/category/getcategory";

    //获取配件商品列表
    public static final String GET_PJ_LIST="api/app/product/getproductlist";

    //设置用户信息
    public static final String EDIT_USER="api/app/user/updateuser";

    //获取用户信息
    public static final String GET_USER="api/app/user/getuser";

    //修改密码
    public static final String UPDATE_PWD="api/app/user/updatepassword";

    //修改手机号
    public static final String UPDATE_MOBILE="api/app/user/updatemobile";

    //获取增值服务分类下的列表
    public static final String GET_ZZFU_LIST="api/wap/services/getList";

    //我的收藏
    public static final String COLLECTION_LIST="api/app/collect/list";

    //收藏/取消收藏商品
    public static final String COLLECTION="api/app/collect/collect";

    //单页类型（0：关于我们，1：融资租赁）
    public static final String ABOUT="api/app/user/aboutus";

    //查询购物车
    public static final String CAR_LIST="api/app/cart/getcartlist";

    //删除多个购物车商品接口
    public static final String DEL_MORE_CAR="api/app/cart/delecartspulist";

    //删除单个购物车商品接口
    public static  final String DEL_CAR="api/app/cart/delecartspu";

    //修改购物车商品数量接口
    public static final String CHANGE_COUNT="api/app/cart/changecount";

    //根据id获取增值服务详情接口
    public static final String ZZFW_INFO="api/wap/services/getInfo";

    //获取配件商品详情接口
    public static final String GET_PJ_GOOD_DETAILS="api/app/product/getprofittingdetail";

    //加入购物车
    public static final String ADD_CAR="api/app/cart/addcart";

    //获取机床商品详情接口
    public static final String GET_JC_DETAILS="api/app/product/getpromachinedetail";

    //发布生意圈动态
    public static final String ADD_BUSINESS="api/app/moment/addmoment";

    //上传图片
    public static final String UPLOAD_IMG="api/app/img/apiupload";

    //获取我的优惠券列表接口
    public static final String GET_COUPON="api/app/coupon/showcoupon";

    //获取优惠券列表
    public static final String GET_COUPON_LIST="api/app/coupon/getlist";

    //领取优惠券
    public static final String TAKE_COUPON="api/app/coupon/takecoupon";

    //生意圈消息提示接口
    public static final String GET_BUSINESS_MSG="api/app/message/getnum";

    //获取生意圈列表接口
    public static final String GET_BUSINESS_LIST="api/app/moment/getomentbypage";

    //举报接口
    public static final String REPORT="api/app/report/addreport";

    //获取消息列表接口
    public static final String GET_MESSAGE="api/app/message/getMomentByPage";

    //生意圈点赞
    public static final String DZ_BUSINESS="api/app/praise/dianzan";

    //添加评论
    public static final String ADD_COMMENT="api/app/moment/addcomment";

}
