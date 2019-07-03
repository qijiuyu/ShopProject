package com.ylkj.shopproject.activity.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.view.CircleImageView;

/**
 * 我的
 */
public class UserActivity extends BaseActivity implements View.OnClickListener{

    private CircleImageView imgUser;
    private TextView tvName;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initView();
    }


    /**
     * 初始化控件
     */
    private void initView(){
        imgUser=findViewById(R.id.img_user);
        tvName=findViewById(R.id.tv_name);
        findViewById(R.id.img_customer).setOnClickListener(this);
        findViewById(R.id.tv_dfk).setOnClickListener(this);
        findViewById(R.id.tv_dfh).setOnClickListener(this);
        findViewById(R.id.tv_dsh).setOnClickListener(this);
        findViewById(R.id.tv_dpj).setOnClickListener(this);
        findViewById(R.id.tv_sh).setOnClickListener(this);
        findViewById(R.id.rel_pt).setOnClickListener(this);
        findViewById(R.id.rel_wxjl).setOnClickListener(this);
        findViewById(R.id.rel_yhq).setOnClickListener(this);
        findViewById(R.id.rel_sc).setOnClickListener(this);
        findViewById(R.id.rel_jl).setOnClickListener(this);
        findViewById(R.id.rel_jgrz).setOnClickListener(this);
        findViewById(R.id.rel_zpzz).setOnClickListener(this);
        findViewById(R.id.rel_shdz).setOnClickListener(this);
        findViewById(R.id.rel_qyxx).setOnClickListener(this);
        findViewById(R.id.rel_setting).setOnClickListener(this);
        findViewById(R.id.img_news).setOnClickListener(this);
        findViewById(R.id.tv_all_order).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //全部订单
            case R.id.tv_all_order:
                 break;
             //待付款
            case R.id.tv_dfk:
                 break;
            //待发货
            case R.id.tv_dfh:
                 break;
            //待收货
            case R.id.tv_dsh:
                 break;
            //待评价
            case R.id.tv_dpj:
                 break;
            //售后
            case R.id.tv_sh:
                 break;
           //我的拼团
            case R.id.rel_pt:
                 break;
            //我的维修记录
            case R.id.rel_wxjl:
                 break;
            //我的优惠券
            case R.id.rel_yhq:
                 break;
            //我的收藏
            case R.id.rel_sc:
                 break;
            //我的交流
            case R.id.rel_jl:
                 break;
            //机构认证
            case R.id.rel_jgrz:
                 break;
            //增票资质
            case R.id.rel_zpzz:
                 break;
            //收货地址
            case R.id.rel_shdz:
                 break;
            //企业信息
            case R.id.rel_qyxx:
                 break;
            //设置
            case R.id.rel_setting:
                 break;
            //客服
            case R.id.img_customer:
                 break;
            //消息
            case R.id.img_news:
                 break;
        }
    }
}
