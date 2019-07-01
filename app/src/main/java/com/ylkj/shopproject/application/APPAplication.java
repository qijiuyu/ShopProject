package com.ylkj.shopproject.application;

import com.zxdc.utils.library.base.BaseApplication;
import com.zxdc.utils.library.util.ActivitysLifecycle;

public class APPAplication extends BaseApplication {

    public void onCreate() {
        super.onCreate();
        setContext(this);

        registerActivityLifecycleCallbacks(ActivitysLifecycle.getInstance());
    }
}
