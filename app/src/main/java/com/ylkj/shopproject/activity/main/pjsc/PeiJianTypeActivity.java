package com.ylkj.shopproject.activity.main.pjsc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.adapter.main.PeiJianDataAdapter;
import com.ylkj.shopproject.adapter.main.PeiJianTypeAdapter;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.zxdc.utils.library.base.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * 配件商城分类
 */
public class PeiJianTypeActivity extends BaseActivity {

    private ListView listType,listData;
    private PeiJianTypeAdapter peiJianTypeAdapter;
    private PeiJianDataAdapter peiJianDataAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peijian_type);
        //注册EventBus
        EventBus.getDefault().register(this);
        initView();
    }

    /**
     * 初始化
     */
    private void initView(){
        listType=findViewById(R.id.list_type);
        listData=findViewById(R.id.list_data);
        peiJianTypeAdapter=new PeiJianTypeAdapter(this,null);
        listType.setAdapter(peiJianTypeAdapter);

        peiJianDataAdapter=new PeiJianDataAdapter(this,null);
        listData.setAdapter(peiJianDataAdapter);
    }


    /**
     * EventBus注解
     */
    @Subscribe
    public void onEvent(EventBusType eventBusType){
        if(eventBusType.getStatus()== EventStatus.PJSC_TYPE){
            setClass(PeiJianListActivity.class);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //关闭eventBus
        EventBus.getDefault().unregister(this);
    }
}
