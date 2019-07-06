package com.ylkj.shopproject.activity.user.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.util.ToastUtil;

/**
 * 密码手机号
 */
public class UpdateMobileActivity extends BaseActivity implements View.OnClickListener{

    private TextView tvMobile,tvSend;
    private EditText etCode,etPwd,etMobile;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_mobile);
        initView();
    }

    /**
     * 初始化
     */
    private void initView(){
        tvMobile=findViewById(R.id.tv_mobile);
        etCode=findViewById(R.id.et_code);
        tvSend=findViewById(R.id.tv_send);
        etPwd=findViewById(R.id.et_pwd);
        etMobile=findViewById(R.id.et_mobile);
        tvSend.setOnClickListener(this);
        findViewById(R.id.tv_confirm).setOnClickListener(this);
        findViewById(R.id.lin_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final String mobile=etMobile.getText().toString().trim();
        final String code=etCode.getText().toString().trim();
        final String pwd=etPwd.getText().toString().trim();
        switch (v.getId()){
            //获取验证码
            case R.id.tv_send:
                 if(TextUtils.isEmpty(mobile)){
                    ToastUtil.showLong("请输入手机号!");
                    return;
                 }
                 break;
            //确定
            case R.id.tv_confirm:
                 if(TextUtils.isEmpty(code)){
                     ToastUtil.showLong("请输入验证码!");
                     return;
                 }
                if(TextUtils.isEmpty(pwd)){
                    ToastUtil.showLong("请输入新密码!");
                    return;
                }
                if(TextUtils.isEmpty(mobile)){
                    ToastUtil.showLong("请输入手机号!");
                    return;
                }
                 break;
            case R.id.lin_back:
                finish();
                break;
        }
    }
}
