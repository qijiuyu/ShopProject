package com.ylkj.shopproject.activity.user.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.TextViewCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.util.ToastUtil;

/**
 * 密码重置
 */
public class UpdatePwdActivity extends BaseActivity implements View.OnClickListener{

    private TextView tvMobile,tvSend;
    private EditText etCode,etPwd;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pwd);
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
        tvSend.setOnClickListener(this);
        findViewById(R.id.tv_confirm).setOnClickListener(this);
        findViewById(R.id.lin_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //获取验证码
            case R.id.tv_send:
                 break;
            //确定
            case R.id.tv_confirm:
                 final String code=etCode.getText().toString().trim();
                 final String pwd=etPwd.getText().toString().trim();
                 if(TextUtils.isEmpty(code)){
                     ToastUtil.showLong("请输入验证码!");
                     return;
                 }
                if(TextUtils.isEmpty(pwd)){
                    ToastUtil.showLong("请输入新密码!");
                    return;
                }
                 break;
            case R.id.lin_back:
                finish();
                break;
        }
    }
}
