package com.ylkj.shopproject.activity.user.setting;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.util.DataCleanManager;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.util.Util;

/**
 * 设置界面
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener{

    private TextView tvMobile,tvVersion,tvCache;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }

    /**
     * 初始化
     */
    private void initView(){
        tvMobile=findViewById(R.id.tv_mobile);
        tvVersion=findViewById(R.id.tv_version);
        tvCache=findViewById(R.id.tv_cache);
        tvMobile.setOnClickListener(this);
        findViewById(R.id.rel_clear_cache).setOnClickListener(this);
        findViewById(R.id.rel_update_pwd).setOnClickListener(this);
        findViewById(R.id.rel_feedback).setOnClickListener(this);
        findViewById(R.id.rel_about).setOnClickListener(this);
        findViewById(R.id.tv_log_out).setOnClickListener(this);
        findViewById(R.id.lin_back).setOnClickListener(this);

        //显示版本号
        tvVersion.setText(Util.getVersionName(this));

        //显示缓存
        try {
            long cacheSize = DataCleanManager.getFolderSize(getCacheDir());
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                cacheSize += DataCleanManager.getFolderSize(getExternalCacheDir());
            }
            tvCache.setText(DataCleanManager.getFormatSize(cacheSize).toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //更改手机号
            case R.id.tv_mobile:
                 setClass(UpdateMobileActivity.class);
                 break;
            //清除缓存
            case R.id.rel_clear_cache:
                 DataCleanManager.cleanApplicationData(this,null);
                 ToastUtil.showLong("清除缓存成功！");
                 tvCache.setText("0.0M");
                 break;
            //重置密码
            case R.id.rel_update_pwd:
                 setClass(UpdatePwdActivity.class);
                 break;
            //意见反馈
            case R.id.rel_feedback:
                 setClass(FeedBackActivity.class);
                 break;
            //关于我们
            case R.id.rel_about:
                 setClass(AboutActivity.class);
                 break;
            //退出登录
            case R.id.tv_log_out:
                 break;
            case R.id.lin_back:
                 finish();
                 break;
        }
    }


}
