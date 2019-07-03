package com.ylkj.shopproject.application;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.zxdc.utils.library.base.BaseApplication;
import com.zxdc.utils.library.util.ActivitysLifecycle;

public class APPAplication extends BaseApplication {

    public static APPAplication application;
    public void onCreate() {
        super.onCreate();
        application=this;
        setContext(this);

        //初始化地图
        SDKInitializer.initialize(getApplicationContext());
        SDKInitializer.setCoordType(CoordType.BD09LL);

        registerActivityLifecycleCallbacks(ActivitysLifecycle.getInstance());
    }
}
