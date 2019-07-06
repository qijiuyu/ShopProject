package com.ylkj.shopproject.activity.user.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.adapter.user.OrderDetailsAdapter;
import com.zxdc.utils.library.base.BaseActivity;

/**
 * 订单详情
 */
public class OrderDetailsActivity extends BaseActivity implements View.OnClickListener{

    private ImageView imgBg,imgStatus;
    private TextView tvStatus,tvName,tvMobile,tvAddress,tvLeave,tvOrderCode,tvOrderTime,tvPayType,tvFpType,tvCompany,tvFpContent,tvFpCode,tvShopMoney,tvYfMoney,tvYhMoney,tvTotalMoney;
    private ListView listView;
    private OrderDetailsAdapter orderDetailsAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        initView();
    }

    /**
     * 初始化
     */
    private void initView(){
        imgBg=findViewById(R.id.img_bg);
        imgStatus=findViewById(R.id.img_status);
        tvStatus=findViewById(R.id.tv_status);
        tvName=findViewById(R.id.tv_name);
        tvMobile=findViewById(R.id.tv_mobile);
        tvAddress=findViewById(R.id.tv_address);
        listView=findViewById(R.id.listview);
        tvLeave=findViewById(R.id.tv_leave);
        tvOrderCode=findViewById(R.id.tv_order_code);
        tvOrderTime=findViewById(R.id.tv_order_time);
        tvPayType=findViewById(R.id.tv_pay_type);
        tvFpType=findViewById(R.id.tv_fp_type);
        tvCompany=findViewById(R.id.tv_company);
        tvFpContent=findViewById(R.id.tv_fp_content);
        tvFpCode=findViewById(R.id.tv_fp_code);
        tvShopMoney=findViewById(R.id.tv_shop_money);
        tvYfMoney=findViewById(R.id.tv_yf_money);
        tvYhMoney=findViewById(R.id.tv_yh_money);
        tvTotalMoney=findViewById(R.id.tv_total_money);
        findViewById(R.id.lin_back).setOnClickListener(this);

        orderDetailsAdapter=new OrderDetailsAdapter(this,null);
        listView.setAdapter(orderDetailsAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lin_back:
                 finish();
                 break;
        }
    }
}
