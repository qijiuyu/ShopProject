package com.ylkj.shopproject.activity.city;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.adapter.city.CityAdapter;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.City;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * 选择省份
 */
public class ProvinceActivity extends BaseActivity {

    private ListView listView;
    private CityAdapter cityAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        EventBus.getDefault().register(this);
        initView();
        getProvice();
    }

    /**
     * 初始化控件
     */
    private void initView(){
        listView=findViewById(R.id.listView);
        findViewById(R.id.lin_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ProvinceActivity.this.finish();
            }
        });
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                case HandlerConstant.GET_PROVICE_SUCCESS:
                      final City city= (City) msg.obj;
                     if(null==city){
                         break;
                     }
                     if(city.isSussess()){
                         cityAdapter=new CityAdapter(ProvinceActivity.this,city.getData(),0);
                         listView.setAdapter(cityAdapter);
                         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                 City.CityBean cityBean=city.getData().get(position);
                                 if(null==cityBean){
                                     return;
                                 }
                                 Intent intent=new Intent(ProvinceActivity.this,CityActivity.class);
                                 intent.putExtra("proviceCode",cityBean.getCode());
                                 intent.putExtra("proviceName",cityBean.getName());
                                 startActivity(intent);
                             }
                         });
                     }else{
                         ToastUtil.showLong(city.getDesc());
                     }
                     break;
                case HandlerConstant.REQUST_ERROR:
                     ToastUtil.showLong(getString(R.string.net_error));
                     break;
            }
            return false;
        }
    });


    /**
     * EventBus注解
     */
    @Subscribe
    public void onEvent(EventBusType eventBusType){
        if(eventBusType.getStatus()== EventStatus.GET_CITY){
            finish();
        }
    }

    /**
     * 获取省份数据
     */
    private void getProvice(){
        DialogUtil.showProgress(this,"数据加载中");
        HttpMethod.getProvice(handler);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
