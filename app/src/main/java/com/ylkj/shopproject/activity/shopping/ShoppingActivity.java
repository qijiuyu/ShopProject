package com.ylkj.shopproject.activity.shopping;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.main.pjsc.ConfirmPTActivity;
import com.ylkj.shopproject.adapter.shopping.ShoppingAdapter;
import com.zxdc.utils.library.base.BaseActivity;

/**
 * 购物车
 */
public class ShoppingActivity extends BaseActivity implements View.OnClickListener{

    private TextView tvUpdate,tvMoney,tvPlay;
    private ImageView imgSelect;
    private ListView listView;
    private ShoppingAdapter shoppingAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
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

        shoppingAdapter=new ShoppingAdapter(this,null);
        listView.setAdapter(shoppingAdapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //编辑
            case R.id.tv_update:
                 shoppingAdapter.setIsShow(true);
                 shoppingAdapter.notifyDataSetChanged();
                 break;
            //全选
            case R.id.img_select:
                 break;
            //结算或删除
            case R.id.tv_play:
                 setClass(ConfirmPTActivity.class);
                 break;

        }
    }
}
