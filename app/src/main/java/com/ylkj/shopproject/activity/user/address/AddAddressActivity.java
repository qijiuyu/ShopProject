package com.ylkj.shopproject.activity.user.address;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.util.ToastUtil;

/**
 * 添加收货地址
 */
public class AddAddressActivity extends BaseActivity implements View.OnClickListener{

    private EditText etName,etMobile,etAddress;
    private TextView tvRegion;
    private ImageView imgType;
    //是否设置默认，true是   false不是
    private boolean isDefalt=false;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        initView();
    }


    /**
     * 初始化
     */
    private void initView(){
        etName=findViewById(R.id.et_name);
        etMobile=findViewById(R.id.et_mobile);
        tvRegion=findViewById(R.id.tv_region);
        etAddress=findViewById(R.id.et_address);
        imgType=findViewById(R.id.img_type);
        imgType.setOnClickListener(this);
        findViewById(R.id.tv_select).setOnClickListener(this);
        findViewById(R.id.tv_save).setOnClickListener(this);
        findViewById(R.id.lin_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //选择地区
            case R.id.tv_select:
                 Intent intent=new Intent(this,SelectRegionActivity.class);
                 startActivityForResult(intent,1);
                 break;
            //选择是否默认
            case R.id.img_type:
                 if(isDefalt){
                     isDefalt=false;
                     imgType.setImageDrawable(getResources().getDrawable(R.mipmap.addr_close_type));
                 }else{
                     isDefalt=true;
                     imgType.setImageDrawable(getResources().getDrawable(R.mipmap.addr_open_type));
                 }
                 break;
            //保存
            case R.id.tv_save:
                 final String name=etName.getText().toString().trim();
                 final String mobile=etMobile.getText().toString().trim();
                 final String region=tvRegion.getText().toString().trim();
                 final String address=etAddress.getText().toString().trim();
                 if(TextUtils.isEmpty(name)){
                     ToastUtil.showLong("请输入收货人姓名！");
                     return;
                 }
                if(TextUtils.isEmpty(mobile)){
                    ToastUtil.showLong("请输入手机号码！");
                    return;
                }
                if(TextUtils.isEmpty(region)){
                    ToastUtil.showLong("请选择所在地区！");
                    return;
                }
                if(TextUtils.isEmpty(address)){
                    ToastUtil.showLong("请输入详细地址！");
                    return;
                }
                 break;
            case R.id.lin_back:
                 finish();
                 break;
        }
    }
}
