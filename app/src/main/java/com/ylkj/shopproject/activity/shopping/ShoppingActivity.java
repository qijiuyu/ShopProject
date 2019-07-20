package com.ylkj.shopproject.activity.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.main.pjsc.ConfirmXDActivity;
import com.ylkj.shopproject.activity.shopping.persenter.ShoppingPersenter;
import com.ylkj.shopproject.adapter.shopping.ShoppingAdapter;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Shopping;
import com.zxdc.utils.library.util.Util;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * 购物车
 */
public class ShoppingActivity extends BaseActivity implements View.OnClickListener{

    private TextView tvUpdate,tvMoney,tvPlay;
    private ImageView imgSelect;
    private ListView listView;
    private ShoppingAdapter shoppingAdapter;
    //MVP对象
    private ShoppingPersenter shoppingPersenter;
    //购物车数据对象
    private Shopping shopping;
    //总价格
    private String totalMoney;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        //注册eventBus
        EventBus.getDefault().register(this);
        //实例化MVP
        shoppingPersenter=new ShoppingPersenter(this);
        initView();
    }


    /**
     * 初始化
     */
    private void initView(){
        tvUpdate=findViewById(R.id.tv_update);
        imgSelect=findViewById(R.id.img_select);
        tvMoney=findViewById(R.id.tv_total_money);
        tvPlay=findViewById(R.id.tv_play);
        listView=findViewById(R.id.listView);
        tvPlay.setOnClickListener(this);
        tvUpdate.setOnClickListener(this);
        imgSelect.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //编辑
            case R.id.tv_update:
                  final String status=tvUpdate.getText().toString().trim();
                  if(status.equals("编辑")){
                      tvUpdate.setText("完成");
                      tvPlay.setText("删除");
                      tvMoney.setVisibility(View.GONE);
                  }else{
                      tvUpdate.setText("编辑");
                      tvPlay.setText("结算");
                      tvMoney.setVisibility(View.VISIBLE);
                  }
                 break;
            //全选
            case R.id.img_select:
                 shoppingPersenter.selectCarList(shopping);
                 break;
            //结算或删除
            case R.id.tv_play:
                  final String play=tvPlay.getText().toString().trim();
                  if(play.equals("结算")){
                      Intent intent=new Intent(this,ConfirmXDActivity.class);
                      intent.putExtra("shopping",shopping);
                      startActivity(intent);
                  }else{
                      shoppingPersenter.delete(shopping);
                  }
                 break;

        }
    }

    /**
     * EventBus注解
     */
    @Subscribe
    public void onEvent(EventBusType eventBusType){
        switch (eventBusType.getStatus()){
            //获取购物车数据
            case EventStatus.GET_CAR_LIST:
                 shopping= (Shopping) eventBusType.getObject();
                 totalMoney=Util.setDouble(shopping.getData().getAllmoney(),2);
                 tvMoney.setText(Html.fromHtml("总计：<font color='#36C7B5'>¥"+ totalMoney+"</font>"));
                 shoppingAdapter=new ShoppingAdapter(this,shopping.getData().getCartInfos());
                 listView.setAdapter(shoppingAdapter);
                 break;
            //修改商品数量
            case EventStatus.CHANGE_CAR_COUNT:
                 final Shopping.goodList goodList= (Shopping.goodList) eventBusType.getObject();
                 shoppingPersenter.changeCount(goodList);
                 break;
            //购物车选中了哪几个
            case EventStatus.SELECT_CAR_GOODS:
                  final int carId= (int) eventBusType.getObject();
                  shoppingPersenter.selectCar(carId,shopping);
                  break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(null!=shopping && shopping.getData().getCartInfos().size()>0){
            return;
        }
        //查询购物车数据
        shoppingPersenter.getCarList();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
