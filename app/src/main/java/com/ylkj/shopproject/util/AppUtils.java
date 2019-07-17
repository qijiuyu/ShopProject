package com.ylkj.shopproject.util;

import android.text.TextUtils;

import com.ylkj.shopproject.application.APPAplication;
import com.zxdc.utils.library.util.SPUtil;

public class AppUtils {

    /**
     * 判断是否登录
     */
    public static boolean isLogin(){
        String token= SPUtil.getInstance(APPAplication.application).getString(SPUtil.TOKEN);
        if(TextUtils.isEmpty(token)){
            return false;
        }else{
            return true;
        }
    }
}
