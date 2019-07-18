package com.ylkj.shopproject.activity.user.after;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.adapter.user.ApplyForDetailsAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.AfterDetails;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyGridView;
import com.zxdc.utils.library.view.OvalImage2Views;
/**
 * 申请记录详情
 */
public class ApplyForDetailsActivity extends BaseActivity {

    private TextView tvStatus,tvType,tvTitle,tvMoney,tvNum,tvDes,tvAddress,tvCompany,tvPhone;
    private OvalImage2Views imgIcon;
    private MyGridView gridView;
    //订单id
    private int orderId;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_for_details);
        initView();
        //售后订单详情接口
        afterDetails();
    }


    /**
     * 初始化
     */
    private void initView(){
        orderId=getIntent().getIntExtra("orderId",0);
        tvStatus=findViewById(R.id.tv_status);
        tvType=findViewById(R.id.tv_type);
        imgIcon=findViewById(R.id.img_icon);
        tvTitle=findViewById(R.id.tv_title);
        tvMoney=findViewById(R.id.tv_money);
        tvNum=findViewById(R.id.tv_num);
        tvDes=findViewById(R.id.tv_des);
        gridView=findViewById(R.id.gridview);
        tvAddress=findViewById(R.id.tv_address);
        tvCompany=findViewById(R.id.tv_company);
        tvPhone=findViewById(R.id.tv_phone);
        findViewById(R.id.lin_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ApplyForDetailsActivity.this.finish();
            }
        });
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                //售后订单详情回执
                case HandlerConstant.AFTER_DETAILS_SUCCESS:
                     final AfterDetails afterDetails= (AfterDetails) msg.obj;
                     if(null==afterDetails){
                         break;
                     }
                     if(afterDetails.isSussess()){
                         //展示售后数据
                         showData(afterDetails);
                     }else{
                         ToastUtil.showLong(afterDetails.getDesc());
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
     * 展示售后数据
     */
    private void showData(AfterDetails afterDetails){
        switch (afterDetails.getAfstatus()){
            case -1:
                tvStatus.setText("审核不通过");
                break;
            case 0:
                tvStatus.setText("待审核");
                break;
            case 1:
                tvStatus.setText("审核通过");
                break;
            case 2:
                tvStatus.setText("商家待收货");
                break;
            case 3:
                tvStatus.setText("商家待发货");
                break;
            case 4:
                tvStatus.setText("买家待收货");
                break;
            case 5:
                tvStatus.setText("售后完成");
                break;
        }
        if(afterDetails.getType()==4){
            tvType.setText("退货");
        }else{
            tvType.setText("换货");
        }
        Glide.with(this).load(afterDetails.getProimg()).override(90,90).centerCrop().into(imgIcon);
        tvTitle.setText(afterDetails.getProname());
        tvMoney.setText("¥"+afterDetails.getProprice());
        tvNum.setText("x"+afterDetails.getProcount());
        tvDes.setText(afterDetails.getReason());
        if(!TextUtils.isEmpty(afterDetails.getImgs())){
            String[] imgs=afterDetails.getImgs().split(",");
            ApplyForDetailsAdapter applyForDetailsAdapter=new ApplyForDetailsAdapter(this,imgs);
            gridView.setAdapter(applyForDetailsAdapter);
        }
        tvCompany.setText("门店名称  "+afterDetails.getShopname());
        tvAddress.setText("寄回地址  "+afterDetails.getAddress());
        tvPhone.setText("寄回电话  "+afterDetails.getPhone());
    }


    /**
     * 售后订单详情接口
     */
    private void afterDetails(){
        DialogUtil.showProgress(this,"数据加载中");
        HttpMethod.afterDetails(orderId,handler);
    }
}
