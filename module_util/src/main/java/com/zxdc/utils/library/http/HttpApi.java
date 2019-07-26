package com.zxdc.utils.library.http;

import com.zxdc.utils.library.base.MainXP;
import com.zxdc.utils.library.bean.Abvert;
import com.zxdc.utils.library.bean.AddOrder;
import com.zxdc.utils.library.bean.After;
import com.zxdc.utils.library.bean.AfterAddress;
import com.zxdc.utils.library.bean.AfterDetails;
import com.zxdc.utils.library.bean.Certification;
import com.zxdc.utils.library.bean.CommOrder;
import com.zxdc.utils.library.bean.Company;
import com.zxdc.utils.library.bean.IsNews;
import com.zxdc.utils.library.bean.JCName;
import com.zxdc.utils.library.bean.MainHot;
import com.zxdc.utils.library.bean.MainJX;
import com.zxdc.utils.library.bean.MainRQ;
import com.zxdc.utils.library.bean.MoChuang;
import com.zxdc.utils.library.bean.MyTuan;
import com.zxdc.utils.library.bean.MyTuanDetails;
import com.zxdc.utils.library.bean.OrderAddr;
import com.zxdc.utils.library.bean.OrderComm;
import com.zxdc.utils.library.bean.OrderDetails;
import com.zxdc.utils.library.bean.OrderNum;
import com.zxdc.utils.library.bean.Secreening;
import com.zxdc.utils.library.bean.Zpzz;
import com.zxdc.utils.library.bean.Address;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.Business;
import com.zxdc.utils.library.bean.BusinessDetails;
import com.zxdc.utils.library.bean.BusinessMsg;
import com.zxdc.utils.library.bean.City;
import com.zxdc.utils.library.bean.Collection;
import com.zxdc.utils.library.bean.Coupon;
import com.zxdc.utils.library.bean.JCGoodDetails;
import com.zxdc.utils.library.bean.Login;
import com.zxdc.utils.library.bean.MyOrder;
import com.zxdc.utils.library.bean.News;
import com.zxdc.utils.library.bean.PJGoodDetails;
import com.zxdc.utils.library.bean.PJGoodList;
import com.zxdc.utils.library.bean.PJType;
import com.zxdc.utils.library.bean.SearchGood;
import com.zxdc.utils.library.bean.Shopping;
import com.zxdc.utils.library.bean.Type;
import com.zxdc.utils.library.bean.UserInfo;
import com.zxdc.utils.library.bean.ZzfuType;
import com.zxdc.utils.library.bean.ZzfuTypeList;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface HttpApi {

    @FormUrlEncoded
    @POST(HttpConstant.GET_SMS_CODE)
    Call<BaseBean> getSmsCode(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.REGISTER)
    Call<BaseBean> register(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.LOGIN)
    Call<Login> login(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.GET_PROVICE)
    Call<City> getProvice(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.GET_CITY)
    Call<City> getCity(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.GET_AREA)
    Call<City> getArea(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.ADD_ZPZZ)
    Call<BaseBean> add_zpzz(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.ADD_ADDRESS)
    Call<BaseBean> addAddress(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.GET_ADDR_LIST)
    Call<Address> getAddrList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.SET_ADDR_DEFAULT)
    Call<BaseBean> setAddrDefault(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.DEL_ADDR)
    Call<BaseBean> delAddr(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.UPD_ADDR)
    Call<BaseBean> updAddr(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.FEED_BACK)
    Call<BaseBean> feedBack(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.EDIT_COMPANY)
    Call<BaseBean> editCompany(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.GET_TYPE)
    Call<Type> getType(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.GET_TYPE)
    Call<PJType> getpJType(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.GET_TYPE)
    Call<ZzfuType> getZzfuType(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.GET_PJ_LIST)
    Call<PJGoodList> getPJGoodList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.EDIT_USER)
    Call<UserInfo> editUser(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.GET_USER)
    Call<UserInfo> getUser(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.UPDATE_PWD)
    Call<BaseBean> updatePwd(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.UPDATE_MOBILE)
    Call<BaseBean> updateMobile(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.GET_ZZFU_LIST)
    Call<ZzfuTypeList> getZzfuList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.COLLECTION_LIST)
    Call<Collection> myCollection(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.COLLECTION)
    Call<BaseBean> collection(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.CAR_LIST)
    Call<Shopping> getCarList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.DEL_MORE_CAR)
    Call<Shopping> delMoreCar(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.DEL_CAR)
    Call<Shopping> delCar(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.CHANGE_COUNT)
    Call<Shopping> changeCount(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.GET_PJ_GOOD_DETAILS)
    Call<PJGoodDetails> getPJDetails(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.ADD_CAR)
    Call<BaseBean> addCar(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.GET_JC_DETAILS)
    Call<JCGoodDetails> getJCDetails(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.ADD_BUSINESS)
    Call<BaseBean> addBusiness(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.GET_COUPON)
    Call<Coupon> MyCoupon(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.GET_COUPON_LIST)
    Call<Coupon> getCouponList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.TAKE_COUPON)
    Call<BaseBean> takeCoupon(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.GET_BUSINESS_MSG)
    Call<BusinessMsg> businessMsg(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.GET_BUSINESS_LIST)
    Call<Business> getBusinessList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.REPORT)
    Call<BaseBean> report(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.GET_MESSAGE)
    Call<News> getNews(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.DZ_BUSINESS)
    Call<BaseBean> likeBusiness(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.ADD_COMMENT)
    Call<BaseBean> addComment(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.MY_BUSINESS)
    Call<Business> myBusiness(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.SET_INST)
    Call<BaseBean> setInst(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.BUSINESS_DETAILS)
    Call<BusinessDetails> businessDetails(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.MY_ORDER_LIST)
    Call<MyOrder> getMyOrder(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.SEARCH_GOODS)
    Call<SearchGood> searchGoods(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.FORGET_PWD)
    Call<BaseBean> forgetPwd(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.SELECT_CAR)
    Call<Shopping> selectCar(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.SELECT_CAR_LIST)
    Call<Shopping> selectCarList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.GET_ZPZZ)
    Call<Zpzz> getZpzz(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.GET_CERTIFICATION)
    Call<Certification> getCertifiCation(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.CANCLE_ORDER)
    Call<BaseBean> cancleOrder(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.DELETE_ORDER)
    Call<BaseBean> delOrder(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.CONFIRM_GOODS)
    Call<BaseBean> confirmGoods(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.GET_COMMENT_ORDER)
    Call<CommOrder> commOrder(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.GET_AFTER_ORDER)
    Call<After> getAfterOrder(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.GET_AFTER_APPLY)
    Call<After> getAfterApply(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.APPLYAFTER)
    Call<BaseBean> applyAfter(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.APPLYAFTER)
    Call<AfterDetails> afterDetails(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.GET_ADDR_INFO)
    Call<AfterAddress> getAfterAddr(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.ORDER_DETAILS)
    Call<OrderDetails> orderDetails(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.COMM_ORDER)
    Call<BaseBean> evalOrder(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.GET_ORDER_YHQ)
    Call<Coupon> getOrderYhq(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.GET_MAIN_JX)
    Call<MainJX> mainJX(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.GET_ABVERT)
    Call<Abvert> getAbvert(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.GET_MAIN_XP)
    Call<MainXP> mainXP(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.GET_MAIN_RQ)
    Call<MainRQ> mainRQ(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.GET_MAIN_HOT)
    Call<MainHot> mainHot(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.UPDATE_ZPZZ)
    Call<BaseBean> updateZpzz(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.UPDATE_INST)
    Call<BaseBean> updateInst(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.GET_COMPANY_INFO)
    Call<Company> getCompany(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.IS_NEWS)
    Call<IsNews> isNews(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.GET_MORE_TOP)
    Call<MainHot> getMoreTopic(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.GET_ORDER_ADDR)
    Call<OrderAddr> getOrderAddr(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.ADD_ORDER)
    Call<AddOrder> addOrder(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.GET_MAIN_MC)
    Call<MoChuang> MoChuang(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.GET_JC_NAME)
    Call<JCName> getJcName(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.ADD_FAULT)
    Call<BaseBean> addFault(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.GET_ORDER_COMM)
    Call<OrderComm> getOrderComm(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.ORDER_NUM)
    Call<OrderNum> getOrderNum(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.PAY_DG)
    Call<BaseBean> dgPay(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.SCREENING)
    Call<Secreening> Secreening(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.MY_TUAN)
    Call<MyTuan> myTuan(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.MY_TUAN_DETAILS)
    Call<MyTuanDetails> tuanDetails(@FieldMap Map<String, String> map);
}
