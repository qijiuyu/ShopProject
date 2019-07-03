package com.ylkj.shopproject.application;

import com.zxdc.utils.library.base.BaseApplication;
import com.zxdc.utils.library.util.ActivitysLifecycle;

public class APPAplication extends BaseApplication {

    public static APPAplication application;
    public void onCreate() {
        super.onCreate();
        application=this;
        setContext(this);

        registerActivityLifecycleCallbacks(ActivitysLifecycle.getInstance());
    }
}
