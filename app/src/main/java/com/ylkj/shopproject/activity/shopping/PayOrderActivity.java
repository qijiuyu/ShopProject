package com.ylkj.shopproject.activity.shopping;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

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
                payType=3;
                imgWX.setImageDrawable(getResources().getDrawable(R.mipmap.select_btn));
                imgZFB.setImageDrawable(getResources().getDrawable(R.mipmap.select_btn));
                imgDG.setImageDrawable(getResources().getDrawable(R.mipmap.pay_select));
                  break;
            case R.id.tv_pay:
                 switch (payType){
                     //对公支付
                     case 3:
                          dgPay();
                          break;
                 }
                 break;
            case R.id.lin_back:
                 finish();
                 break;
        }
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                //对公支付回执
                case HandlerConstant.DG_PAY_SUCCESS:
                      final BaseBean baseBean= (BaseBean) msg.obj;
                      if(null==baseBean){
                          break;
                      }
                      if(baseBean.isSussess()){

                      }else{
                          ToastUtil.showLong(baseBean.getDesc());
                      }
                      break;
                case HandlerConstant.REQUST_ERROR:
                    ToastUtil.showLong(getString(R.string.net_error));
                    break;
            }
            return false;
        }
    });

    /**
     * 对公支付
     */
    private void dgPay(){
        DialogUtil.showProgress(this,"支付中");
        HttpMethod.dgPay(handler);
    }
}
