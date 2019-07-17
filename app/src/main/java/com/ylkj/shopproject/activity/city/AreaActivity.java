package com.ylkj.shopproject.activity.city;

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
import com.zxdc.utils.library.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

/**
 * 选择区
 */
public class AreaActivity extends BaseActivity {

    private ListView listView;
    private CityAdapter cityAdapter;
    private String proviceCode,proviceName,cityCode,cityName;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        initView();
        getProvice();
    }

    /**
     * 初始化控件
     */
    private void initView(){
        TextView tvTitle=findViewById(R.id.tv_title);
        tvTitle.setText("选择区");
        listView=findViewById(R.id.listView);
        findViewById(R.id.lin_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AreaActivity.this.finish();
            }
        });
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                case HandlerConstant.GET_AREA_SUCCESS:
                     final City city= (City) msg.obj;
                     if(null==city){
                         break;
                     }
                     if(city.isSussess()){
                         cityAdapter=new CityAdapter(AreaActivity.this,city.getData(),2);
                         listView.setAdapter(cityAdapter);
                         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                 City.CityBean cityBean=city.getData().get(position);
                                 if(null==cityBean){
                                     return;
                                 }
                                 StringBuffer stringBuffer=new StringBuffer(proviceCode+","+cityCode+","+cityBean.getCode()+","+proviceName+","+cityName+","+cityBean.getName());
                                 EventBus.getDefault().post(new EventBusType(EventStatus.GET_CITY,stringBuffer.toString()));
                                 finish();
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
     * 获取省份数据
     */
    private void getProvice(){
        proviceCode=getIntent().getStringExtra("proviceCode");
        proviceName=getIntent().getStringExtra("proviceName");
        cityCode=getIntent().getStringExtra("cityCode");
        cityName=getIntent().getStringExtra("cityName");
        DialogUtil.showProgress(this,"数据加载中");
        HttpMethod.getArea(cityCode,handler);
    }
}
