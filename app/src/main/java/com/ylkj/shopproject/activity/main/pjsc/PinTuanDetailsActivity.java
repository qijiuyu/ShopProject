package com.ylkj.shopproject.activity.main.pjsc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.main.persenter.PeiJianDetailsPersenter;
import com.ylkj.shopproject.adapter.main.PeiJianDetailsTypeAdapter;
import com.youth.banner.Banner;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.view.MeasureListView;

/**
 * 拼团商品详情
 */
public class PinTuanDetailsActivity extends BaseActivity implements View.OnClickListener{

    private Banner banner;
    private TextView tvName,tvDes,tvNewMoney,tvYfMoney,tvNum,tvDay,tvHour,tvMinutes,tvSecounds;
    private MeasureListView listView;
    private PeiJianDetailsTypeAdapter peiJianDetailsTypeAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pintuan_details);
        initView();
    }

    /**
     * 初始化
     */
    private void initView(){
        banner=findViewById(R.id.banner);
        tvName=findViewById(R.id.tv_name);
        tvDes=findViewById(R.id.tv_des);
        tvNewMoney=findViewById(R.id.tv_new_money);
        tvYfMoney=findViewById(R.id.tv_yf_money);
        listView=findViewById(R.id.list_type);
        tvNum=findViewById(R.id.tv_num);
        tvDay=findViewById(R.id.tv_day);
        tvHour=findViewById(R.id.tv_hour);
        tvMinutes=findViewById(R.id.tv_minutes);
        tvSecounds=findViewById(R.id.tv_secounds);
        findViewById(R.id.tv_yhq).setOnClickListener(this);
        findViewById(R.id.img_remove).setOnClickListener(this);
        findViewById(R.id.img_add).setOnClickListener(this);
        findViewById(R.id.img_kf).setOnClickListener(this);
        findViewById(R.id.img_sc).setOnClickListener(this);
        findViewById(R.id.img_shopping).setOnClickListener(this);
        findViewById(R.id.tv_buy).setOnClickListener(this);
        findViewById(R.id.tv_add_shopping).setOnClickListener(this);


        peiJianDetailsTypeAdapter=new PeiJianDetailsTypeAdapter(this,null);
        listView.setAdapter(peiJianDetailsTypeAdapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //领取优惠券
            case R.id.tv_yhq:
                 break;
            //减数量
            case R.id.img_remove:
                 break;
            //加数量
            case R.id.img_add:
                 break;
            //客服
            case R.id.img_kf:
                 break;
            //收藏
            case R.id.img_sc:
                 break;
            //加入购物车
            case R.id.tv_add_shopping:
            case R.id.img_shopping:
                 break;
            //立即购买
            case R.id.tv_buy:
                 break;
        }
    }
}
