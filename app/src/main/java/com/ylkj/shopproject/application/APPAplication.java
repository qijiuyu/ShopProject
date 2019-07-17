package com.ylkj.shopproject.application;

import android.text.TextUtils;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.zxdc.utils.library.base.BaseApplication;
import com.zxdc.utils.library.bean.Login;
import com.zxdc.utils.library.util.ActivitysLifecycle;
import com.zxdc.utils.library.util.SPUtil;

public class APPAplication extends BaseApplication {

    public static APPAplication application;
    public static Login.LoginBean login;
    public void onCreate() {
        super.onCreate();
        application=this;
        setContext(this);

        //获取用户对象
        final String userInfo= SPUtil.getInstance(this).getString(SPUtil.LOGIN);
        if(!TextUtils.isEmpty(userInfo)){
            login=SPUtil.gson.fromJson(userInfo,Login.LoginBean.class);
        }

        //初始化地图
        SDKInitializer.initialize(getApplicationContext());
        SDKInitializer.setCoordType(CoordType.BD09LL);

        registerActivityLifecycleCallbacks(ActivitysLifecycle.getInstance());
    }
}
