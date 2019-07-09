package com.ylkj.shopproject.activity.main.pjsc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.main.search.SearchActivity;
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
public class PeiJianTypeActivity extends BaseActivity implements View.OnClickListener{

    private ListView listType,listData;
    //左边分类的adapter
    private PeiJianTypeAdapter peiJianTypeAdapter;
    //右边分类下的数据的adapter
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
        findViewById(R.id.lin_back).setOnClickListener(this);
        findViewById(R.id.lin_search).setOnClickListener(this);
        peiJianTypeAdapter=new PeiJianTypeAdapter(this,null);
        listType.setAdapter(peiJianTypeAdapter);

        peiJianDataAdapter=new PeiJianDataAdapter(this,null);
        listData.setAdapter(peiJianDataAdapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //进入搜索界面
            case R.id.lin_search:
                setClass(SearchActivity.class);
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
