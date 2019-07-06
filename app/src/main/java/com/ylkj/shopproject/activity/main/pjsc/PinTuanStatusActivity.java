package com.ylkj.shopproject.activity.main.pjsc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.adapter.user.PinTuanStatusAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.view.OvalImage2Views;

/**
 * 拼团状态界面，是成功还是失败
 */
public class PinTuanStatusActivity extends BaseActivity implements View.OnClickListener{

    private OvalImage2Views imgIcon;
    private ImageView imgType;
    private TextView tvTitle,tvTime,tvMoney,tvDmMoney,tvNum,tvYfMoney,tvStatus,tvPayTime,tvPayType;
    private RecyclerView listView;
    private PinTuanStatusAdapter pinTuanStatusAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pintuan_status);
        initView();
    }


    /**
     * 初始化
     */
    private void initView(){
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
}
