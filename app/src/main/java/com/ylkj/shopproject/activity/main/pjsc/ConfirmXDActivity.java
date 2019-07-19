package com.ylkj.shopproject.activity.main.pjsc;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.main.persenter.ConfirmPTPersenter;
import com.ylkj.shopproject.activity.shopping.PayOrderActivity;
import com.ylkj.shopproject.activity.user.address.AddressListActivity;
import com.ylkj.shopproject.adapter.main.ConfirmOrderAdapter;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Address;
import com.zxdc.utils.library.bean.PJGoodDetails;
import com.zxdc.utils.library.bean.Shopping;
import com.zxdc.utils.library.util.Util;
import com.zxdc.utils.library.view.MeasureListView;
import com.zxdc.utils.library.view.OvalImage2Views;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * 确认拼团下单
 */
public class ConfirmXDActivity extends BaseActivity implements View.OnClickListener{

    private OvalImage2Views imgIcon;
    private TextView tvName,tvMobile,tvAddress,tvTitle,tvTime,tvMoney,tvOldMoney,tvNum,tvYfMoney,tvFP,tvYHQ,tvMoney2,tvYfMoney2,tvYhMoney,tvTotalMoney;
    private EditText etDes;
    private MeasureListView goodList;
    private ConfirmPTPersenter confirmPTPersenter;
    //收货地址集合
    private List<Address.AddressBean> addrList=new ArrayList<>();
    //商品详情页对象
    private PJGoodDetails.goodBean goodBean;
    //购物车对象
    private Shopping shopping;
    //0：商品详情页进入，  1：购物车进入
    private int type;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        //注册EventBus
        EventBus.getDefault().register(this);
        confirmPTPersenter=new ConfirmPTPersenter(this);
        initView();
        showGood();
    }

    /**
     * 初始化
     */
    private void initView(){
        imgIcon=findViewById(R.id.img_shopping);
        tvName=findViewById(R.id.tv_userName);
        tvMobile=findViewById(R.id.tv_mobile);
        tvAddress=findViewById(R.id.tv_address);
        tvTitle=findViewById(R.id.tv_title);
        tvTime=findViewById(R.id.tv_time);
        tvMoney=findViewById(R.id.tv_money);
        tvOldMoney=findViewById(R.id.tv_old_money);
        tvNum=findViewById(R.id.tv_num);
        tvYfMoney=findViewById(R.id.tv_yf_money);
        etDes=findViewById(R.id.et_des);
        tvFP=findViewById(R.id.tv_fp);
        tvYHQ=findViewById(R.id.tv_yhq);
        tvMoney2=findViewById(R.id.tv_money2);
        tvYfMoney2=findViewById(R.id.tv_yf_money2);
        tvYhMoney=findViewById(R.id.tv_yh_money);
        tvTotalMoney=findViewById(R.id.tv_total_money);
        goodList=findViewById(R.id.good_list);
        tvYHQ.setOnClickListener(this);
        findViewById(R.id.rel_select_addr).setOnClickListener(this);
        findViewById(R.id.lin_fp).setOnClickListener(this);
        findViewById(R.id.tv_submit).setOnClickListener(this);
        findViewById(R.id.lin_back).setOnClickListener(this);
    }


    /**
     * 展示商品数据信息
     */
    private void showGood(){
        type=getIntent().getIntExtra("type",0);
        goodBean= (PJGoodDetails.goodBean) getIntent().getSerializableExtra("goodBean");
        shopping= (Shopping) getIntent().getSerializableExtra("shopping");
        //展示商品详情页的
        if(type==0){
            tvTitle.setText(goodBean.getName());
            tvMoney.setText("¥"+goodBean.getPrice());
            tvOldMoney.setText("¥"+goodBean.getOldprice());
            tvOldMoney.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
            tvNum.setText("x"+goodBean.getCount());
            tvYfMoney.setText("+¥"+goodBean.getFreigth());
            Glide.with(this).load(goodBean.getImg()).centerCrop().error(R.mipmap.default_icon).into(imgIcon);
        }

        //购物车进入
        if(type==1){
            findViewById(R.id.rel_good).setVisibility(View.GONE);
            //展示商品列表
            ConfirmOrderAdapter confirmOrderAdapter=new ConfirmOrderAdapter(this,shopping.getData().getCartInfos());
            goodList.setAdapter(confirmOrderAdapter);
            //购物车计算总的运费
            double totalYFmoney = 0;
            for (int i=0;i<shopping.getData().getCartInfos().size();i++){
                  totalYFmoney= Util.sum(totalYFmoney,shopping.getData().getCartInfos().get(i).getFreigth());
            }
            tvYfMoney.setText("+¥"+totalYFmoney);
        }
    }


    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()){
            //选择地址
            case R.id.rel_select_addr:
                 setClass(AddressListActivity.class);
                 break;
            //选择发票
            case R.id.lin_fp:
                 confirmPTPersenter.showFPDialog();
                 break;
             //选择优惠券
            case R.id.tv_yhq:
                 setClass(SelectYHQActivity.class);
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


    /**
     * EventBus注解
     */
    @Subscribe
    public void onEvent(EventBusType eventBusType){
        switch (eventBusType.getStatus()){
            //查询收货地址
            case EventStatus.GET_MY_ADDRESS:
                  addrList= (List<Address.AddressBean>) eventBusType.getObject();
                  //展示收货地址信息
                  showAddress();
                  break;
        }
    }


    /**
     * 展示收货地址信息
     */
    private void showAddress(){
        if(addrList.size()>0){
            findViewById(R.id.rel_select_addr).setVisibility(View.GONE);
            findViewById(R.id.rel_addr).setVisibility(View.VISIBLE);
            Address.AddressBean addressBean=addrList.get(0);
            tvName.setText(addressBean.getName());
            tvMobile.setText(addressBean.getMobile());
            tvAddress.setText(addressBean.getAddress());
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        //获取我的地址列表
        confirmPTPersenter.getAddrList();
    }

    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
