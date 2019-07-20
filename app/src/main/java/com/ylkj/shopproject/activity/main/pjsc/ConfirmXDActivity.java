package com.ylkj.shopproject.activity.main.pjsc;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.main.persenter.ConfirmPTPersenter;
import com.ylkj.shopproject.activity.user.address.AddressListActivity;
import com.ylkj.shopproject.adapter.main.ConfirmOrderAdapter;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.AddrBean;
import com.zxdc.utils.library.bean.Coupon;
import com.zxdc.utils.library.bean.PJGoodDetails;
import com.zxdc.utils.library.bean.Shopping;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.util.Util;
import com.zxdc.utils.library.view.MeasureListView;
import com.zxdc.utils.library.view.OvalImage2Views;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
/**
 * 确认拼团下单
 */
public class ConfirmXDActivity extends BaseActivity implements View.OnClickListener{

    private OvalImage2Views imgIcon;
    private TextView tvName,tvMobile,tvAddress,tvTitle,tvTime,tvMoney,tvOldMoney,tvNum,tvYfMoney,tvFP,tvYHQ,tvMoney2,tvYfMoney2,tvYhMoney,tvTotalMoney;
    private EditText etDes;
    private MeasureListView goodList;
    private ConfirmPTPersenter confirmPTPersenter;
    //商品详情页对象
    private PJGoodDetails.goodBean goodBean;
    //购物车对象
    private Shopping shopping;
    //收货地址对象
    private AddrBean addrBean;
    //选中优惠券的对象
    private Coupon.DataBean coupon;
    //计算总费用
    private double totalAllMoney=0;
    //计算总的运费
    private double totalYfMoney=0;
    //计算总的商品数量
    private int totalNum=0;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        //注册EventBus
        EventBus.getDefault().register(this);
        confirmPTPersenter=new ConfirmPTPersenter(this);
        initView();
        //展示商品数据信息
        showGood();
        //获取我的地址列表
        confirmPTPersenter.getOrderAddr();
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
        findViewById(R.id.rel_addr).setOnClickListener(this);
        findViewById(R.id.lin_fp).setOnClickListener(this);
        findViewById(R.id.tv_submit).setOnClickListener(this);
        findViewById(R.id.lin_back).setOnClickListener(this);
    }


    /**
     * 展示商品数据信息
     */
    private void showGood(){
        goodBean= (PJGoodDetails.goodBean) getIntent().getSerializableExtra("goodBean");
        shopping= (Shopping) getIntent().getSerializableExtra("shopping");
        //展示商品详情页的
        if(null!=goodBean){
            //商品基本信息
            tvTitle.setText(goodBean.getName());
            tvMoney.setText("¥"+goodBean.getPrice());
            tvOldMoney.setText("¥"+goodBean.getOldprice());
            tvOldMoney.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
            tvNum.setText("x"+goodBean.getCount());
            tvYfMoney.setText("+¥"+goodBean.getFreigth());
            Glide.with(this).load(goodBean.getImg()).centerCrop().error(R.mipmap.default_icon).into(imgIcon);
            //商品金额
            tvMoney2.setText(String.valueOf(goodBean.getCount()*goodBean.getPrice()));
            //运费
            tvYfMoney2.setText("+¥"+goodBean.getFreigth());
            //计算总费用
            showTotalMoney();
        }

        //购物车进入
        if(null!=shopping){
            findViewById(R.id.rel_good).setVisibility(View.GONE);
            //展示商品列表
            ConfirmOrderAdapter confirmOrderAdapter=new ConfirmOrderAdapter(this,shopping.getData().getCartInfos());
            goodList.setAdapter(confirmOrderAdapter);
            //商品金额
            tvMoney2.setText(String.valueOf(shopping.getData().getAllmoney()));
            //计算总费用
            showTotalMoney();
        }
    }


    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()){
            //选择地址
            case R.id.rel_addr:
            case R.id.rel_select_addr:
                 intent.setClass(this,AddressListActivity.class);
                 intent.putExtra("type",1);
                 startActivityForResult(intent,100);
                 break;
            //选择发票
            case R.id.lin_fp:
                 confirmPTPersenter.showFPDialog();
                 break;
             //选择优惠券
            case R.id.tv_yhq:
                 confirmPTPersenter.couponJson(goodBean,shopping);
                 break;
            //下单
            case R.id.tv_submit:
//                 setClass(PayOrderActivity.class);
                 if(null==addrBean){
                     ToastUtil.showLong("请选择收货地址！");
                     return;
                 }
                confirmPTPersenter.orderJson(goodBean,shopping,coupon,totalAllMoney,etDes);
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
                  addrBean= (AddrBean) eventBusType.getObject();
                  //展示收货地址信息
                  showAddress();
                  break;
            //下单页选中的优惠券
            case EventStatus.SELECT_ORDER_COUPON:
                  coupon= (Coupon.DataBean) eventBusType.getObject();
                  if(null!=coupon){
                      tvYHQ.setText("满"+coupon.getFullreductionvalue()+"减"+coupon.getFacevalue());
                      //满减优惠
                      tvYhMoney.setText("-¥"+coupon.getFacevalue());
                      //计算并显示最后总的费用和总的数量
                      showTotalMoney();
                  }
                  break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            //展示收货地址信息
            case 100:
                 addrBean= (AddrBean) data.getSerializableExtra("addrBean");
                 showAddress();
                 break;
        }
    }

    /**
     * 展示收货地址信息
     */
    private void showAddress(){
        if(null==addrBean){
            return;
        }
        findViewById(R.id.rel_select_addr).setVisibility(View.GONE);
        findViewById(R.id.rel_addr).setVisibility(View.VISIBLE);
        tvName.setText(addrBean.getName());
        tvMobile.setText(addrBean.getMobile());
        tvAddress.setText(addrBean.getAddress());
    }

    /**
     * 计算并显示最后总的费用和总的数量
     */
    private void showTotalMoney(){
        totalAllMoney=0;
        if(null!=goodBean){
            //加运费
            totalAllMoney=Util.sum(totalAllMoney,goodBean.getFreigth());
            //加商品数量费用
            totalAllMoney=Util.sum(totalAllMoney,goodBean.getCount()*goodBean.getPrice());
            //商品数量
            totalNum=goodBean.getCount();
        }

        if(null!=shopping){
            for (int i=0;i<shopping.getData().getCartInfos().size();i++){
                  //计算总运费
                  totalYfMoney= Util.sum(totalYfMoney,shopping.getData().getCartInfos().get(i).getFreigth());
                  //计算商品总数量
                  totalNum+=shopping.getData().getCartInfos().get(i).getProcount();
            }
            //加运费
            totalAllMoney+=totalYfMoney;
            //加商品数量费用
            totalAllMoney+=shopping.getData().getAllmoney();
            //运费
            tvYfMoney2.setText("+¥"+totalYfMoney);
        }
        //减去优惠费用
        if(null!=coupon){
            totalAllMoney=Util.sub(totalAllMoney,coupon.getFacevalue());
        }
        tvTotalMoney.setText("共"+totalNum+"件   总金额"+totalAllMoney);
    }


    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
