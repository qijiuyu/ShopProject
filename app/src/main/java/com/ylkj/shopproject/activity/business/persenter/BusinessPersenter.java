package com.ylkj.shopproject.activity.business.persenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.business.AddBusinessActivity;
import com.ylkj.shopproject.activity.business.BusinessActivity;
import com.ylkj.shopproject.activity.user.company.EditCompanyActivity;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.zxdc.utils.library.bean.BusinessMsg;
import com.zxdc.utils.library.bean.Company;
import com.zxdc.utils.library.bean.ZzfuType;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;

public class BusinessPersenter {

    private Activity activity;

    public BusinessPersenter(Activity activity){
        this.activity=activity;
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                //查询分类回执
                case HandlerConstant.GET_TYPE_SUCCESS:
                    final ZzfuType zzfuType= (ZzfuType) msg.obj;
                    if(null==zzfuType){
                        break;
                    }
                    if(zzfuType.isSussess()){
                        EventBus.getDefault().post(new EventBusType(EventStatus.BUSINESS_TYPE,zzfuType.getData()));
                    }else{
                        ToastUtil.showLong(zzfuType.getDesc());
                    }
                    break;
                //生意圈消息提示
                case HandlerConstant.GET_BUSINESS_MSG_SUCCESS:
                     BusinessMsg businessMsg= (BusinessMsg) msg.obj;
                    if(null==businessMsg){
                        break;
                    }
                    if(businessMsg.isSussess() && null!=businessMsg.getData()){
                        EventBus.getDefault().post(new EventBusType(EventStatus.BUSINESS_NEW,businessMsg.getData()));
                    }
                    break;
                //查询企业信息回执
                case HandlerConstant.GET_COMPANY_INFO_SUCCESS:
                     final Company company= (Company) msg.obj;
                     if(null==company) {
                         break;
                     }
                     Intent intent=new Intent();
                     if(company.isSussess() && null!=company.getData()){
                         intent.setClass(activity,AddBusinessActivity.class);
                         intent.putExtra("typeList",(Serializable)BusinessActivity.typeList);
                     }else{
                         intent.setClass(activity,EditCompanyActivity.class);
                     }
                     activity.startActivity(intent);
                     break;
                case HandlerConstant.REQUST_ERROR:
                    ToastUtil.showLong(activity.getString(R.string.net_error));
                    break;
            }
            return false;
        }
    });


    /**
     * 获取分类
     */
    public void getType(){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getZzfuType("4",handler);
    }


    /**
     * 生意圈消息提示
     */
    public void getBusinessMsg(){
        HttpMethod.businessMsg(handler);
    }


    /**
     * 查询企业信息
     */
    public void getCompany(){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getCompany(handler);
    }
}
