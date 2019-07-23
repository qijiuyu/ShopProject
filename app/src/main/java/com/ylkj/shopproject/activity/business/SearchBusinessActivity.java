package com.ylkj.shopproject.activity.business;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import com.baidu.mapapi.model.LatLng;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.adapter.business.SearchBusinessAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Business;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.SPUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyRefreshLayout;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;
import java.util.ArrayList;
import java.util.List;
/**
 * 搜索生意圈
 */
public class SearchBusinessActivity extends BaseActivity implements View.OnClickListener,MyRefreshLayoutListener {

    private EditText etKey;
    private ListView listView;
    private MyRefreshLayout mRefreshLayout;
    private SearchBusinessAdapter searchBusinessAdapter;
    //当前页数
    private int page=1;
    //数据集合
    private List<Business.DataBean> listAll=new ArrayList<>();
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_business);
        initView();
    }


    /**
     * 初始化
     */
    private void initView(){
        etKey=findViewById(R.id.et_keys);
        listView=findViewById(R.id.listView);
        mRefreshLayout=findViewById(R.id.re_list);
        //刷新加载
        mRefreshLayout.setMyRefreshLayoutListener(this);
        findViewById(R.id.lin_back).setOnClickListener(this);
        findViewById(R.id.tv_search).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //搜索
            case R.id.tv_search:
                 final String key=etKey.getText().toString().trim();
                 if(TextUtils.isEmpty(key)){
                     ToastUtil.showLong("请输入关键字搜索");
                     return;
                 }
                 //获取生意圈列表接口
                 DialogUtil.showProgress(this,"数据查询中");
                 getBusiness(HandlerConstant.GET_BUSINESS_LIST_SUCCESS1);
                 break;
            case R.id.lin_back:
                  finish();
                  break;
        }
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                //查询数据回执
                case HandlerConstant.GET_BUSINESS_LIST_SUCCESS1:
                    listAll.clear();
                    mRefreshLayout.refreshComplete();
                    refresh((Business) msg.obj);
                    break;
                case HandlerConstant.GET_BUSINESS_LIST_SUCCESS2:
                    mRefreshLayout.loadMoreComplete();
                    refresh((Business) msg.obj);
                    break;
                case HandlerConstant.REQUST_ERROR:
                    ToastUtil.showLong(getString(R.string.net_error));
                    break;
            }
            return false;
        }
    });


    /**
     * 刷新界面数据
     */
    private void refresh(Business business){
        if(null==business){
            return;
        }
        if(business.isSussess()){
            List<Business.DataBean> list=business.getData();
            listAll.addAll(list);
            if(null==searchBusinessAdapter){
                searchBusinessAdapter=new SearchBusinessAdapter(this,listAll);
                listView.setAdapter(searchBusinessAdapter);
            }else{
                searchBusinessAdapter.notifyDataSetChanged();
            }
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Business.DataBean dataBean=listAll.get(position);
                    Intent intent=new Intent(SearchBusinessActivity.this, BusinessDetailsActivity.class);
                    intent.putExtra("id",dataBean.getId());
                    startActivity(intent);
                }
            });
            if(list.size()<20){
                mRefreshLayout.setIsLoadingMoreEnabled(false);
            }
        }else{
            ToastUtil.showLong(business.getDesc());
        }
    }

    @Override
    public void onRefresh(View view) {
        page=1;
        getBusiness(HandlerConstant.GET_BUSINESS_LIST_SUCCESS1);
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        getBusiness(HandlerConstant.GET_BUSINESS_LIST_SUCCESS2);
    }


    /**
     * 获取生意圈列表接口
     */
    private void getBusiness(int index){
        final LatLng latLng= (LatLng) SPUtil.getInstance(this).getObject(SPUtil.LATLNG,LatLng.class);
        HttpMethod.getBusinessList(etKey.getText().toString().trim(),String.valueOf(latLng.latitude),String.valueOf(latLng.longitude),0,page,index ,handler);
    }
}
