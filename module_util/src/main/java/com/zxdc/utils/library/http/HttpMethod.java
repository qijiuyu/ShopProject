package com.zxdc.utils.library.http;

import android.os.Handler;
import android.text.TextUtils;

import com.zxdc.utils.library.bean.About;
import com.zxdc.utils.library.bean.Address;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.Business;
import com.zxdc.utils.library.bean.BusinessMsg;
import com.zxdc.utils.library.bean.City;
import com.zxdc.utils.library.bean.Collection;
import com.zxdc.utils.library.bean.Coupon;
import com.zxdc.utils.library.bean.JCGoodDetails;
import com.zxdc.utils.library.bean.Login;
import com.zxdc.utils.library.bean.News;
import com.zxdc.utils.library.bean.PJGoodDetails;
import com.zxdc.utils.library.bean.PJGoodList;
import com.zxdc.utils.library.bean.PJType;
import com.zxdc.utils.library.bean.Shopping;
import com.zxdc.utils.library.bean.Type;
import com.zxdc.utils.library.bean.UploadImg;
import com.zxdc.utils.library.bean.UserInfo;
import com.zxdc.utils.library.bean.ZzfuType;
import com.zxdc.utils.library.bean.ZzfuTypeList;
import com.zxdc.utils.library.http.base.BaseRequst;
import com.zxdc.utils.library.http.base.Http;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.SPUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class HttpMethod extends BaseRequst {

    /**
     * 获取手机验证码
     */
    public static void getSmsCode(String ph,String smsType, final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("ph", ph);
        map.put("smsType", smsType);
        Http.getRetrofit().create(HttpApi.class).getSmsCode(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.GET_SMS_CODE_SUCCESS, response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 注册
     */
    public static void register(String mobile,String password,String smscode, final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("mobile", mobile);
        map.put("password", password);
        map.put("smscode",smscode);
        Http.getRetrofit().create(HttpApi.class).register(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.REGISTER_SUCCESS, response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 登录
     */
    public static void login(String mobile,String password,final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("mobile", mobile);
        map.put("password", password);
        Http.getRetrofit().create(HttpApi.class).login(map).enqueue(new Callback<Login>() {
            public void onResponse(Call<Login> call, Response<Login> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.LOGIN_SUCCESS, response.body());
            }
            public void onFailure(Call<Login> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 获取省份
     */
    public static void getProvice(final Handler handler) {
        Map<String, String> map = new HashMap<>();
        Http.getRetrofit().create(HttpApi.class).getProvice(map).enqueue(new Callback<City>() {
            public void onResponse(Call<City> call, Response<City> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.GET_PROVICE_SUCCESS, response.body());
            }
            public void onFailure(Call<City> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 获取市
     */
    public static void getCity(String proviceCode,final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("proviceCode",proviceCode);
        Http.getRetrofit().create(HttpApi.class).getCity(map).enqueue(new Callback<City>() {
            public void onResponse(Call<City> call, Response<City> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.GET_CITY_SUCCESS, response.body());
            }
            public void onFailure(Call<City> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 获取区数据
     */
    public static void getArea(String cityCode,final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("cityCode",cityCode);
        Http.getRetrofit().create(HttpApi.class).getArea(map).enqueue(new Callback<City>() {
            public void onResponse(Call<City> call, Response<City> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.GET_AREA_SUCCESS, response.body());
            }
            public void onFailure(Call<City> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 添加收货地址
     */
    public static void addAddress(String Name,String ProvinceCode,String CityCode,String AreaCode,String Address,String Mobile,String CompanyName,int IsDefault,final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("Name",Name);
        map.put("ProvinceCode",ProvinceCode);
        map.put("CityCode",CityCode);
        map.put("AreaCode",AreaCode);
        map.put("Address",Address);
        map.put("Mobile",Mobile);
        map.put("CompanyName",CompanyName);
        map.put("IsDefault",String.valueOf(IsDefault));
        Http.getRetrofit().create(HttpApi.class).addAddress(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.ADD_ADDRESS_SUCCESS, response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 设置增票资质
     */
    public static void add_zpzz(String companyname,String taxnum,String loginaddress,String loginphone,String bankname,String bankcode,String licenseimg,String permitimg,String name,String phone,String address, final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("companyname", companyname);
        map.put("taxnum", taxnum);
        map.put("loginaddress",loginaddress);
        map.put("loginphone",loginphone);
        map.put("bankname",bankname);
        map.put("bankcode",bankcode);
        map.put("licenseimg",licenseimg);
        map.put("permitimg",permitimg);
        map.put("name",name);
        map.put("phone",phone);
        map.put("address",address);
        Http.getRetrofit().create(HttpApi.class).add_zpzz(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.ADD_ZPZZ_SUCCESS, response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 获取收货地址列表
     */
    public static void getAddrList(final Handler handler) {
        Map<String, String> map = new HashMap<>();
        Http.getRetrofit().create(HttpApi.class).getAddrList(map).enqueue(new Callback<Address>() {
            public void onResponse(Call<Address> call, Response<Address> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.GET_ADDR_LIST_SUCCESS, response.body());
            }
            public void onFailure(Call<Address> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 修改默认收货地址
     */
    public static void setAddrDefault(int id,final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("id",String.valueOf(id));
        Http.getRetrofit().create(HttpApi.class).setAddrDefault(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.SET_ADDR_DEFAULT_SUCCESS, response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 删除收货地址
     */
    public static void delAddr(int id,final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("id",String.valueOf(id));
        Http.getRetrofit().create(HttpApi.class).delAddr(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.DEL_ADDR_SUCCESS, response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 修改收货地址
     */
    public static void updAddr(int id,String Name,String ProvinceCode,String CityCode,String AreaCode,String Address,String Mobile,String CompanyName,int IsDefault,final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("id",String.valueOf(id));
        map.put("Name",Name);
        map.put("ProvinceCode",ProvinceCode);
        map.put("CityCode",CityCode);
        map.put("AreaCode",AreaCode);
        map.put("Address",Address);
        map.put("Mobile",Mobile);
        map.put("CompanyName",CompanyName);
        map.put("IsDefault",String.valueOf(IsDefault));
        Http.getRetrofit().create(HttpApi.class).updAddr(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.ADD_ADDRESS_SUCCESS, response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 意见反馈
     */
    public static void feedBack(String content,final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("content",content);
        Http.getRetrofit().create(HttpApi.class).feedBack(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.FEED_BACK_SUCCESS, response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 设置企业信息
     */
    public static void editCompany(String companyname,String address,String name,String phone,final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("companyname",companyname);
        map.put("address",address);
        map.put("name",name);
        map.put("phone",phone);
        Http.getRetrofit().create(HttpApi.class).editCompany(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.EDIT_COMPANY_SUCCESS, response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 查询机床分类
     */
    public static void getType(String type,final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("type",type);
        Http.getRetrofit().create(HttpApi.class).getType(map).enqueue(new Callback<Type>() {
            public void onResponse(Call<Type> call, Response<Type> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.GET_TYPE_SUCCESS, response.body());
            }
            public void onFailure(Call<Type> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }

    /**
     * 查询配件商城分类
     */
    public static void getpJType(String type,final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("type",type);
        Http.getRetrofit().create(HttpApi.class).getpJType(map).enqueue(new Callback<PJType>() {
            public void onResponse(Call<PJType> call, Response<PJType> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.GET_TYPE_SUCCESS, response.body());
            }
            public void onFailure(Call<PJType> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 查询增值服务分类
     */
    public static void getZzfuType(String type,final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("type",type);
        Http.getRetrofit().create(HttpApi.class).getZzfuType(map).enqueue(new Callback<ZzfuType>() {
            public void onResponse(Call<ZzfuType> call, Response<ZzfuType> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.GET_TYPE_SUCCESS, response.body());
            }
            public void onFailure(Call<ZzfuType> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 获取配件商品列表
     */
    public static void getPJGoodList(String protype,String classid,int page,final int index,final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("protype",protype);
        map.put("classid",classid);
        map.put("index",String.valueOf(page));
        map.put("size","20");
        Http.getRetrofit().create(HttpApi.class).getPJGoodList(map).enqueue(new Callback<PJGoodList>() {
            public void onResponse(Call<PJGoodList> call, Response<PJGoodList> response) {
                BaseRequst.sendMessage(handler, index, response.body());
            }
            public void onFailure(Call<PJGoodList> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 设置用户信息
     */
    public static void editUser(String nickname,String imgurl,String introduction,final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("nickname",nickname);
        map.put("imgurl",imgurl);
        map.put("introduction",introduction);
        Http.getRetrofit().create(HttpApi.class).editUser(map).enqueue(new Callback<UserInfo>() {
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.EDIT_USER_SUCCESS, response.body());
            }
            public void onFailure(Call<UserInfo> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 获取用户信息
     */
    public static void getUser(final Handler handler) {
        Map<String, String> map = new HashMap<>();
        Http.getRetrofit().create(HttpApi.class).getUser(map).enqueue(new Callback<UserInfo>() {
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.GET_USER_SUCCESS, response.body());
            }
            public void onFailure(Call<UserInfo> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 修改密码
     */
    public static void updatePwd(String password,String smscode,final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("password",password);
        map.put("smscode",smscode);
        Http.getRetrofit().create(HttpApi.class).updatePwd(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.UPDATE_PWD_SUCCESS, response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }



    /**
     * 修改手机号
     */
    public static void updateMobile(String mobile,String password,String smscode,final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("mobile",mobile);
        map.put("password",password);
        map.put("smscode",smscode);
        Http.getRetrofit().create(HttpApi.class).updateMobile(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.UPDATE_MOBILE_SUCCESS, response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 增值服务分类下的列表
     */
    public static void getZzfuList(String type,final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("type",type);
        Http.getRetrofit().create(HttpApi.class).getZzfuList(map).enqueue(new Callback<ZzfuTypeList>() {
            public void onResponse(Call<ZzfuTypeList> call, Response<ZzfuTypeList> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.GET_ZZFU_LIST_SUCCESS, response.body());
            }
            public void onFailure(Call<ZzfuTypeList> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 我的收藏
     */
    public static void myCollection(String type,final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("type",type);
        Http.getRetrofit().create(HttpApi.class).myCollection(map).enqueue(new Callback<Collection>() {
            public void onResponse(Call<Collection> call, Response<Collection> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.MY_COLLECTION_SUCCESS, response.body());
            }
            public void onFailure(Call<Collection> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 收藏/取消收藏商品
     */
    public static void collection(int id,final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("id",String.valueOf(id));
        Http.getRetrofit().create(HttpApi.class).collection(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.COLLECTION_SUCCESS, response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 单页类型（0：关于我们，1：融资租赁）
     */
    public static void about(String type,final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("type",type);
        Http.getRetrofit().create(HttpApi.class).about(map).enqueue(new Callback<About>() {
            public void onResponse(Call<About> call, Response<About> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.GET_ABOUT_SUCCESS, response.body());
            }
            public void onFailure(Call<About> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 查询购物车
     */
    public static void getCarList(final Handler handler) {
        Map<String, String> map = new HashMap<>();
        Http.getRetrofit().create(HttpApi.class).getCarList(map).enqueue(new Callback<Shopping>() {
            public void onResponse(Call<Shopping> call, Response<Shopping> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.GET_CAR_LIST_SUCCESS, response.body());
            }
            public void onFailure(Call<Shopping> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 删除多个购物车商品接口
     */
    public static void delMoreCar(String ids,final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("ids",ids);
        Http.getRetrofit().create(HttpApi.class).delMoreCar(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.DEL_MORE_CAR_SUCCESS, response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 删除单个购物车商品接口
     */
    public static void delCar(String id,final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("id",id);
        Http.getRetrofit().create(HttpApi.class).delCar(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.DEL_MORE_CAR_SUCCESS, response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 修改购物车商品数量接口
     */
    public static void changeCount(String id,int count,final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("id",id);
        map.put("count",String.valueOf(count));
        Http.getRetrofit().create(HttpApi.class).changeCount(map).enqueue(new Callback<Shopping>() {
            public void onResponse(Call<Shopping> call, Response<Shopping> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.GET_CAR_LIST_SUCCESS, response.body());
            }
            public void onFailure(Call<Shopping> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 根据id获取增值服务详情接口
     */
    public static void getServiceInfo(String id,final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("id",id);
        Http.getRetrofit().create(HttpApi.class).getServiceInfo(map).enqueue(new Callback<About>() {
            public void onResponse(Call<About> call, Response<About> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.GET_SERVICE_INFO_SUCCESS, response.body());
            }
            public void onFailure(Call<About> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 获取配件商品详情接口
     */
    public static void getPJDetails(String spuid,final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("spuid",spuid);
        Http.getRetrofit().create(HttpApi.class).getPJDetails(map).enqueue(new Callback<PJGoodDetails>() {
            public void onResponse(Call<PJGoodDetails> call, Response<PJGoodDetails> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.GET_PJ_DETAILS_SUCCESS, response.body());
            }
            public void onFailure(Call<PJGoodDetails> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 加入购物车
     */
    public static void addCar(int skuid,int procount,final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("skuid",String.valueOf(skuid));
        map.put("procount",String.valueOf(procount));
        Http.getRetrofit().create(HttpApi.class).addCar(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.ADD_CAR_SUCCESS, response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 获取机床商品详情
     */
    public static void getJCDetails(int spuid,final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("spuid",String.valueOf(spuid));
        Http.getRetrofit().create(HttpApi.class).getJCDetails(map).enqueue(new Callback<JCGoodDetails>() {
            public void onResponse(Call<JCGoodDetails> call, Response<JCGoodDetails> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.GET_JC_DETAILS_SUCCESS, response.body());
            }
            public void onFailure(Call<JCGoodDetails> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 上传图片
     */
    public static void uploadImg(int relationtype, List<File> list, final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("relationtype",String.valueOf(relationtype));
        Http.upLoadFile(HttpConstant.UPLOAD_IMG,"images", list, map, new okhttp3.Callback() {
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                try {
                    final String str=response.body().string();
                    LogUtils.e(str+"+++++++++++++++++++");
                    sendMessage(handler, HandlerConstant.UPLOAD_IMG_SUCCESS, SPUtil.gson.fromJson(str,UploadImg.class));
                }catch (Exception e){
                    e.printStackTrace();
                    sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
                }
            }
            public void onFailure(okhttp3.Call call, IOException e) {
                LogUtils.e(e.getMessage()+"_________");
                sendMessage(handler, HandlerConstant.REQUST_ERROR, null);

            }

        });
    }


    /**
     * 发布生意圈动态
     */
    public static void addBusiness(int type,String content,String imgurl,String address,String lat,String lng,final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("type",String.valueOf(type));
        map.put("content",String.valueOf(content));
        map.put("imgurl",imgurl);
        map.put("address",address);
        map.put("lat",lat);
        map.put("lng",lng);
        Http.getRetrofit().create(HttpApi.class).addBusiness(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.ADD_BUSINESS_SUCCESS, response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 我的优惠券
     */
    public static void MyCoupon(int type, int index, final int handlerIndex, final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("type",String.valueOf(type));
        map.put("index",String.valueOf(index));
        map.put("size","20");
        Http.getRetrofit().create(HttpApi.class).MyCoupon(map).enqueue(new Callback<Coupon>() {
            public void onResponse(Call<Coupon> call, Response<Coupon> response) {
                BaseRequst.sendMessage(handler, handlerIndex, response.body());
            }
            public void onFailure(Call<Coupon> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 获取优惠券列表
     */
    public static void getCouponList(String spuid, int index, final int handlerIndex, final Handler handler) {
        Map<String, String> map = new HashMap<>();
        if(!TextUtils.isEmpty(spuid)){
            map.put("spuid",spuid);
        }
        map.put("index",String.valueOf(index));
        map.put("size","20");
        Http.getRetrofit().create(HttpApi.class).getCouponList(map).enqueue(new Callback<Coupon>() {
            public void onResponse(Call<Coupon> call, Response<Coupon> response) {
                BaseRequst.sendMessage(handler, handlerIndex, response.body());
            }
            public void onFailure(Call<Coupon> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 领取优惠券
     */
    public static void takeCoupon( int couponid,final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("couponid",String.valueOf(couponid));
        Http.getRetrofit().create(HttpApi.class).takeCoupon(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.TAKE_COUPON_SUCCESS, response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 生意圈消息提示接口
     */
    public static void businessMsg(final Handler handler) {
        Map<String, String> map = new HashMap<>();
        Http.getRetrofit().create(HttpApi.class).businessMsg(map).enqueue(new Callback<BusinessMsg>() {
            public void onResponse(Call<BusinessMsg> call, Response<BusinessMsg> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.GET_BUSINESS_MSG_SUCCESS, response.body());
            }
            public void onFailure(Call<BusinessMsg> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 获取生意圈列表接口
     */
    public static void getBusinessList(String lat,String lng,int type,int page,final int index,final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("lat",lat);
        map.put("lng",lng);
        map.put("type",String.valueOf(type));
        map.put("page",String.valueOf(page));
        map.put("size","20");
        Http.getRetrofit().create(HttpApi.class).getBusinessList(map).enqueue(new Callback<Business>() {
            public void onResponse(Call<Business> call, Response<Business> response) {
                BaseRequst.sendMessage(handler, index, response.body());
            }
            public void onFailure(Call<Business> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 举报接口
     */
    public static void report(int momentid,String type,final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("momentid",String.valueOf(momentid));
        map.put("type",type);
        Http.getRetrofit().create(HttpApi.class).report(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.REPORT_SUCCESS, response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 获取消息（消息类型（0：订单消息，1：平台消息，2：生意圈评论消息））
     */
    public static void getNews(String type,int page,final int index,final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("type",type);
        map.put("page",String.valueOf(page));
        map.put("size","20");
        Http.getRetrofit().create(HttpApi.class).getNews(map).enqueue(new Callback<News>() {
            public void onResponse(Call<News> call, Response<News> response) {
                BaseRequst.sendMessage(handler, index, response.body());
            }
            public void onFailure(Call<News> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 点赞生意圈
     */
    public static void likeBusiness(int momentid,final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("momentid",String.valueOf(momentid));
        Http.getRetrofit().create(HttpApi.class).likeBusiness(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.LIKE_BUSINESS_SUCCESS, response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }


    /**
     * 评论生意圈
     */
    public static void addComment(int momentid,String content,final Handler handler) {
        Map<String, String> map = new HashMap<>();
        map.put("momentid",String.valueOf(momentid));
        map.put("content",content);
        Http.getRetrofit().create(HttpApi.class).addComment(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.ADD_COMMENT_SUCCESS, response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
            }
        });
    }

}
