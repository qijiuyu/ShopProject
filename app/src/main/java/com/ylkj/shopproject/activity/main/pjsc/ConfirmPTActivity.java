package com.ylkj.shopproject.activity.main.pjsc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.main.persenter.ConfirmPTPersenter;
import com.ylkj.shopproject.activity.shopping.PayOrderActivity;
import com.ylkj.shopproject.activity.user.address.AddressListActivity;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.view.OvalImage2Views;

/**
 * 确认拼团下单
 */
public class ConfirmPTActivity extends BaseActivity implements View.OnClickListener{

    private OvalImage2Views imgIcon;
    private TextView tvTitle,tvTime,tvMoney,tvDmMoney,tvNum,tvYfMoney,tvFP,tvYHQ,tvMoney2,tvYfMoney2,tvYhMoney,tvTotalMoney;
    private EditText etDes;
    private ConfirmPTPersenter confirmPTPersenter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        confirmPTPersenter=new ConfirmPTPersenter(this);
        initView();
    }

    /**
     * 初始化
     */
    private void initView(){
        imgIcon=findViewById(R.id.img_shopping);
        tvTitle=findViewById(R.id.tv_title);
        tvTime=findViewById(R.id.tv_time);
        tvMoney=findViewById(R.id.tv_money);
        tvDmMoney=findViewById(R.id.tv_dm_money);
        tvNum=findViewById(R.id.tv_num);
        tvYfMoney=findViewById(R.id.tv_yf_money);
        etDes=findViewById(R.id.et_des);
        tvFP=findViewById(R.id.tv_fp);
        tvYHQ=findViewById(R.id.tv_yhq);
        tvMoney2=findViewById(R.id.tv_money2);
        tvYfMoney2=findViewById(R.id.tv_yf_money2);
        tvYhMoney=findViewById(R.id.tv_yh_money);
        tvTotalMoney=findViewById(R.id.tv_total_money);
        tvYHQ.setOnClickListener(this);
        findViewById(R.id.rel_select_addr).setOnClickListener(this);
        findViewById(R.id.lin_fp).setOnClickListener(this);
        findViewById(R.id.tv_submit).setOnClickListener(this);
        findViewById(R.id.lin_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()){
            //选择地址
            case R.id.rel_select_addr:
                 intent.setClass(this, AddressListActivity.class);
                 startActivityForResult(intent,1);
                 break;
            //选择发票
            case R.id.lin_fp:
                 confirmPTPersenter.showFPDialog();
                 break;
             //选择优惠券
            case R.id.tv_yhq:
                 break;
            //下单
            case R.id.tv_submit:
                 setClass(PayOrderActivity.class);
                 break;
            case R.id.lin_back:
                 finish();
                 break;
        }
    }
}
