package com.ylkj.shopproject.activity.user.tuan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.user.persenter.MyTuanPersenter;
import com.ylkj.shopproject.adapter.user.PinTuanStatusAdapter;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.MyTuanDetails;
import com.zxdc.utils.library.view.OvalImage2Views;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * 拼团状态界面，是成功还是失败
 */
public class MyTuanDetailsActivity extends BaseActivity implements View.OnClickListener{

    private OvalImage2Views imgIcon;
    private ImageView imgType;
    private TextView tvTitle,tvTime,tvMoney,tvDmMoney,tvNum,tvYfMoney,tvStatus,tvPayTime,tvPayType,tvPlay;
    private RecyclerView listView;
    private PinTuanStatusAdapter pinTuanStatusAdapter;
    //MVP实例
    private MyTuanPersenter myTuanPersenter;
    //已发起的团购信息编号
    private String ugnum;
    //拼团详情对象
    private MyTuanDetails.DataBean dataBean;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pintuan_status);
        myTuanPersenter=new MyTuanPersenter(this);
        //注册EventBus
        EventBus.getDefault().register(this);
        initView();
        //查询团购详情
        myTuanPersenter.tuanDetails(ugnum);
    }


    /**
     * 初始化
     */
    private void initView(){
        ugnum=getIntent().getStringExtra("ugnum");
        imgIcon=findViewById(R.id.img_shopping);
        tvTitle=findViewById(R.id.tv_title);
        tvTime=findViewById(R.id.tv_time);
        tvMoney=findViewById(R.id.tv_money);
        tvDmMoney=findViewById(R.id.tv_dm_money);
        tvNum=findViewById(R.id.tv_num);
        tvYfMoney=findViewById(R.id.tv_yf_money);
        imgType=findViewById(R.id.img_type);
        tvStatus=findViewById(R.id.tv_status);
        listView=findViewById(R.id.listView);
        tvPayTime=findViewById(R.id.tv_pay_time);
        tvPayType=findViewById(R.id.tv_pay_type);
        tvPlay=findViewById(R.id.tv_play);
        findViewById(R.id.lin_back).setOnClickListener(this);

        pinTuanStatusAdapter=new PinTuanStatusAdapter(this,null,new PinTuanStatusAdapter.OnItemClickListener(){
            public void onItemClick(int position) {

            }
        });
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this, 5);
        listView.setLayoutManager(gridLayoutManager);//网格布局
        listView.setAdapter(pinTuanStatusAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lin_back:
                 finish();
                 break;
        }
    }


    /**
     * 展示界面数据
     */
    private void showData(){
        Glide.with(this).load(dataBean.getProimg()).override(90,90).centerCrop().into(imgIcon);
        tvTitle.setText(dataBean.getProname());
        //计算团剩余时间
        myTuanPersenter.showMyTime(dataBean.getEndtime());
        tvMoney.setText("¥"+dataBean.getPrice());
        tvDmMoney.setText("单买价 ¥"+dataBean.getOldprice());
        tvYfMoney.setText("运费： ¥"+dataBean.getFreight());
        switch (dataBean.getStatus()){
            case 0:
                imgType.setImageDrawable(getResources().getDrawable(R.mipmap.pt_dd));
                tvPlay.setText("邀请好友参团");
                break;
            case 1:
                imgType.setImageDrawable(getResources().getDrawable(R.mipmap.pt_success));
                tvStatus.setText("恭喜您拼团成功");
                tvPlay.setText("查看订单");
                break;
            case 2:
                imgType.setImageDrawable(getResources().getDrawable(R.mipmap.pt_fail));
                tvStatus.setText("很遗憾未能按时凑齐人数，拼团失败");
                tvPlay.setText("再发一团");
                break;
        }
        tvPayTime.setText("支付时间"+dataBean.getPaytime());
        switch (dataBean.getPaytype()){
            case 6:
                tvPayType.setText("支付方式：支付宝支付");
                 break;
            case 7:
                tvPayType.setText("支付方式：微信支付");
                 break;
            case 15:
                tvPayType.setText("支付方式：对公支付");
                 break;
        }
    }


    /**
     * EventBus注解
     */
    @Subscribe
    public void onEvent(EventBusType eventBusType){
        switch (eventBusType.getStatus()){
            //查询详情数据
            case EventStatus.MY_TUAN_DETAILS:
                  dataBean= (MyTuanDetails.DataBean) eventBusType.getObject();
                  showData();
                  break;
            //计算剩余倒计时
            case EventStatus.TUAN_DETAILS_TIME:
                  tvTime.setText((String)eventBusType.getObject());
                  if(dataBean.getStatus()==0){
                      tvStatus.setText("还差"+dataBean.getLackcount()+"人拼团成功，剩余"+(String)eventBusType.getObject());
                  }
                  break;
        }
    }


    protected void onDestroy() {
        super.onDestroy();
        myTuanPersenter.finish();
        EventBus.getDefault().unregister(this);
    }
}
