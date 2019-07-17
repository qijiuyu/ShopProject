package com.ylkj.shopproject.activity.user.address;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.adapter.user.SelectAddrAdapter;
import com.ylkj.shopproject.util.GetLocation;
import com.zxdc.utils.library.base.BaseActivity;

import java.util.List;

/**
 * 添加地址中：选择地区
 */
public class SelectRegionActivity extends BaseActivity implements OnGetPoiSearchResultListener {

    private EditText etKey;
    private ListView listView;
    private PoiSearch mPoiSearch;
    private PoiNearbySearchOption poiNearbySearchOption;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_region);
        initView();
        //开始定位
//        GetLocation.getInstance().setLocation(this,handler);
    }


    /**
     * 初始化
     */
    private void initView(){
        etKey=findViewById(R.id.et_keys);
        listView=findViewById(R.id.listView);
        /**
         * 根据关键字搜索
         */
        etKey.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            public void afterTextChanged(Editable s) {
                if(s.toString().length()==0){
                    return;
                }
                poiNearbySearchOption.keyword(s.toString());
                mPoiSearch.searchNearby(poiNearbySearchOption);  // 发起附近检索请求
            }
        });

        //返回
        findViewById(R.id.lin_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SelectRegionActivity.this.finish();
            }
        });
    }


    private void initMap(LatLng latLng){
        // POI初始化搜索模块，注册搜索事件监听
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(this);
        poiNearbySearchOption = new PoiNearbySearchOption();
        poiNearbySearchOption.keyword("公司,家");
        poiNearbySearchOption.location(latLng);
        poiNearbySearchOption.radius(500);  // 检索半径，单位是米
        poiNearbySearchOption.pageCapacity(50);  // 默认每页10条
        mPoiSearch.searchNearby(poiNearbySearchOption);  // 发起附近检索请求
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            //定位成功
            if(msg.what==0x00){
                final LatLng latLng= (LatLng) msg.obj;
                initMap(latLng);
            }
            return false;
        }
    });

    @Override
    public void onGetPoiResult(PoiResult poiResult) {
        if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
            final List<PoiInfo> list=poiResult.getAllPoi();
            SelectAddrAdapter selectAddrAdapter=new SelectAddrAdapter(this,list);
            listView.setAdapter(selectAddrAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });
        }
    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

    }
}
