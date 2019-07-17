package com.ylkj.shopproject.activity.city;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

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
 * 选择市
 */
public class CityActivity extends BaseActivity {

    private ListView listView;
    private CityAdapter cityAdapter;
    private String proviceCode,proviceName;
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
        TextView tvTitle=findViewById(R.id.tv_title);
        tvTitle.setText("选择市");
        listView=findViewById(R.id.listView);
        findViewById(R.id.lin_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CityActivity.this.finish();
            }
        });
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                case HandlerConstant.GET_CITY_SUCCESS:
                      final City city= (City) msg.obj;
                      if(null==city){
                         break;
                      }
                     if(city.isSussess()){
                         cityAdapter=new CityAdapter(CityActivity.this,city.getData(),1);
                         listView.setAdapter(cityAdapter);
                         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                 City.CityBean cityBean=city.getData().get(position);
                                 if(null==cityBean){
                                     return;
                                 }
                                 Intent intent=new Intent(CityActivity.this,AreaActivity.class);
                                 intent.putExtra("proviceCode",proviceCode);
                                 intent.putExtra("proviceName",proviceName);
                                 intent.putExtra("cityCode",cityBean.getCode());
                                 intent.putExtra("cityName",cityBean.getName());
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
     * 获取市份数据
     */
    private void getProvice(){
        proviceCode=getIntent().getStringExtra("proviceCode");
        proviceName=getIntent().getStringExtra("proviceName");
        DialogUtil.showProgress(this,"数据加载中");
        HttpMethod.getCity(proviceCode,handler);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
