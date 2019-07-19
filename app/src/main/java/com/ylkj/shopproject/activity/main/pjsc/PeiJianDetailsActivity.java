package com.ylkj.shopproject.activity.main.pjsc;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.main.persenter.PeiJianDetailsPersenter;
import com.ylkj.shopproject.adapter.main.PeiJianDetailsTypeAdapter;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.youth.banner.Banner;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.PJGoodDetails;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.util.Util;
import com.zxdc.utils.library.view.MeasureListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * 配件商品详情
 */
public class PeiJianDetailsActivity extends BaseActivity implements View.OnClickListener{

    private Banner banner;
    private TextView tvName,tvDes,tvNewMoney,tvOldMoney,tvYfMoney,tvNum;
    private MeasureListView listView;
    private PeiJianDetailsTypeAdapter peiJianDetailsTypeAdapter;
    //MVP实例
    private PeiJianDetailsPersenter peiJianDetailsPersenter;
    //商品详情对象
    private PJGoodDetails pjGoodDetails;
    //商品数量
    private int num=1;
    //商品id
    private int spuid;
    //商品分类中选中的规格skuid
    public static int skuid;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peijian_details);
        //注册eventbus
        EventBus.getDefault().register(this);
        //实例化MVP
        peiJianDetailsPersenter=new PeiJianDetailsPersenter(this);
        initView();
        peiJianDetailsPersenter.getPJDetails(spuid);
    }

    /**
     * 初始化
     */
    private void initView(){
        skuid=0;
        spuid=getIntent().getIntExtra("spuid",0);
        banner=findViewById(R.id.banner);
        tvName=findViewById(R.id.tv_name);
        tvDes=findViewById(R.id.tv_des);
        tvNewMoney=findViewById(R.id.tv_new_money);
        tvOldMoney=findViewById(R.id.tv_old_money);
        tvYfMoney=findViewById(R.id.tv_yf_money);
        listView=findViewById(R.id.list_type);
        tvNum=findViewById(R.id.tv_num);
        findViewById(R.id.tv_yhq).setOnClickListener(this);
        findViewById(R.id.img_remove).setOnClickListener(this);
        findViewById(R.id.img_add).setOnClickListener(this);
        findViewById(R.id.img_kf).setOnClickListener(this);
        findViewById(R.id.img_sc).setOnClickListener(this);
        findViewById(R.id.img_shopping).setOnClickListener(this);
        findViewById(R.id.tv_buy).setOnClickListener(this);
        findViewById(R.id.tv_add_shopping).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //领取优惠券
            case R.id.tv_yhq:
                 break;
            //减数量
            case R.id.img_remove:
                 if(num==1){
                     return;
                 }
                 --num;
                 tvNum.setText(String.valueOf(num));
                 break;
            //加数量
            case R.id.img_add:
                 num++;
                 tvNum.setText(String.valueOf(num));
                 break;
            //客服
            case R.id.img_kf:
                 break;
            //收藏
            case R.id.img_sc:
                 peiJianDetailsPersenter.isCollec(pjGoodDetails);
                 break;
            //加入购物车
            case R.id.tv_add_shopping:
            case R.id.img_shopping:
                 if(skuid==0){
                     ToastUtil.showLong("请选择商品类型！");
                     return;
                 }
                 peiJianDetailsPersenter.addCar(skuid,num);
                 break;
            //立即购买
            case R.id.tv_buy:
                 pjGoodDetails.getData().setCount(num);
                 Intent intent=new Intent(this,ConfirmPTActivity.class);
                 intent.putExtra("goodBean",pjGoodDetails.getData());
                 intent.putExtra("type",0);
                 startActivity(intent);
                 break;
        }
    }


    /**
     * 展示数据
     */
    private void showUIData(){
        final PJGoodDetails.goodBean goodBean=pjGoodDetails.getData();
        if(null==goodBean){
            return;
        }
        tvName.setText(goodBean.getName());
        tvDes.setText(Html.fromHtml(goodBean.getDiscription()));
        tvNewMoney.setText("平台售价："+ Util.setDouble(goodBean.getPrice(),2));
        tvOldMoney.setText(Util.setDouble(goodBean.getOldprice(),2));
        tvOldMoney.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tvYfMoney.setText("¥"+goodBean.getFreigth());

        //展示banner轮播图
        peiJianDetailsPersenter.setBanner(banner,goodBean.getSpuImgList());

        //展示商品类型
        peiJianDetailsTypeAdapter=new PeiJianDetailsTypeAdapter(this,goodBean.getProSpecsList());
        listView.setAdapter(peiJianDetailsTypeAdapter);

    }


    /**
     * EventBus注解
     */
    @Subscribe
    public void onEvent(EventBusType eventBusType){
        switch (eventBusType.getStatus()){
            //获取配件商品详情
            case EventStatus.GET_PJ_DETAILS:
                  pjGoodDetails= (PJGoodDetails) eventBusType.getObject();
                  showUIData();
                  break;
           //商品收藏成功
            case EventStatus.COLLECTION_SUCCESS:
                  pjGoodDetails.getData().setIscollect(1);
                  break;
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
