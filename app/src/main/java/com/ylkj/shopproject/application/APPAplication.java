package com.ylkj.shopproject.application;

import android.text.TextUtils;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
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

        //初始化友盟分享
        initShare();

        registerActivityLifecycleCallbacks(ActivitysLifecycle.getInstance());
    }


    /**
     * 初始化友盟分享
     */
    private void initShare(){
        UMConfigure.setLogEnabled(true);
        UMConfigure.init(this,"561cae6ae0f55abd990035bf","umeng",UMConfigure.DEVICE_TYPE_PHONE,"");
        //微信
        PlatformConfig.setWeixin("wx1a322841c0378eab", "3b0df5e49906d4d129721920aef465a1");
        //新浪微博
        PlatformConfig.setSinaWeibo("620570357", "d7bed770e0574a2d92f083883179edc8", "http://sns.whalecloud.com");
        //QQ
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }
}
