package com.ylkj.shopproject.activity.user.zpzz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.zxdc.utils.library.base.BaseActivity;

/**
 * 审核通过的增票资质
 */
public class ZpzzActivity extends BaseActivity {

    private TextView tvUnitName,tvCode,tvAddress,tvMobile,tvBack,tvBackCode;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zpzz);
        initView();
    }

    /**
     * 初始化
     */
    private void initView(){
        tvUnitName=findViewById(R.id.tv_unit_name);
        tvCode=findViewById(R.id.tv_code);
        tvAddress=findViewById(R.id.tv_address);
        tvMobile=findViewById(R.id.tv_mobile);
        tvBack=findViewById(R.id.tv_back);
        tvBackCode=findViewById(R.id.tv_back_code);
    }
}
