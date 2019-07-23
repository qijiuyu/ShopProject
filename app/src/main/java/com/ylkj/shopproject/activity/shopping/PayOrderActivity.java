package com.ylkj.shopproject.activity.shopping;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.zxdc.utils.library.base.BaseActivity;

/**
 * 支付界面
 */
public class PayOrderActivity extends BaseActivity implements View.OnClickListener{

    private ImageView imgWX,imgZFB,imgDG;
    //1：微信，   2：支付宝
    private int payType=1;
    private double money;
    private String orderCode;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_order);
        initView();
    }



    /**
     * 初始化
     */
    private void initView(){
        //获取费用和订单号
        money=getIntent().getDoubleExtra("money",0);
        orderCode=getIntent().getStringExtra("orderCode");
        TextView tvMoney=findViewById(R.id.tv_money);
        TextView tvOrder=findViewById(R.id.tv_order);
        tvMoney.setText("¥"+money);
        tvOrder.setText(orderCode);
        imgWX=findViewById(R.id.img_wx);
        imgZFB=findViewById(R.id.img_zfb);
        imgDG=findViewById(R.id.img_dg);
        findViewById(R.id.lin_wx).setOnClickListener(this);
        findViewById(R.id.lin_zfb).setOnClickListener(this);
        findViewById(R.id.lin_dg).setOnClickListener(this);
        findViewById(R.id.tv_pay).setOnClickListener(this);
        findViewById(R.id.lin_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //选择微信
            case R.id.lin_wx:
                 payType=1;
                 imgWX.setImageDrawable(getResources().getDrawable(R.mipmap.pay_select));
                 imgZFB.setImageDrawable(getResources().getDrawable(R.mipmap.select_btn));
                 imgDG.setImageDrawable(getResources().getDrawable(R.mipmap.select_btn));
                 break;
            //选择支付宝
            case R.id.lin_zfb:
                 payType=2;
                 imgWX.setImageDrawable(getResources().getDrawable(R.mipmap.select_btn));
                 imgZFB.setImageDrawable(getResources().getDrawable(R.mipmap.pay_select));
                 imgDG.setImageDrawable(getResources().getDrawable(R.mipmap.select_btn));
                break;
            //选择对公支付
            case R.id.lin_dg:
                imgWX.setImageDrawable(getResources().getDrawable(R.mipmap.select_btn));
                imgZFB.setImageDrawable(getResources().getDrawable(R.mipmap.select_btn));
                imgDG.setImageDrawable(getResources().getDrawable(R.mipmap.pay_select));
                  break;
            case R.id.tv_pay:
                 break;
            case R.id.lin_back:
                 finish();
                 break;
        }
    }
}
