package com.ylkj.shopproject.activity.user.business;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.business.BusinessDetailsActivity;
import com.ylkj.shopproject.adapter.user.MyBusinessAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Business;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyRefreshLayout;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;
import java.util.ArrayList;
import java.util.List;
/**
 * 我的交流
 */
public class MyBusinessActivity extends BaseActivity  implements MyRefreshLayoutListener {

    private ListView listView;
    private MyRefreshLayout mRefreshLayout;
    //当前页数
    private int page=1;
    private MyBusinessAdapter myBusinessAdapter;
    private List<Business.DataBean> listAll=new ArrayList<>();
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_business);
        initView();
        myBusiness(HandlerConstant.MY_BUSINESS_SUCCESS1);
    }


    /**
     * 初始化控件
     */
    private void initView(){
        listView=findViewById(R.id.listView);
        mRefreshLayout=findViewById(R.id.re_list);
        //刷新加载
        mRefreshLayout.setMyRefreshLayoutListener(this);
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                //查询数据回执
                case HandlerConstant.MY_BUSINESS_SUCCESS1:
                    listAll.clear();
                    mRefreshLayout.refreshComplete();
                    refresh((Business) msg.obj);
                    break;
                case HandlerConstant.MY_BUSINESS_SUCCESS2:
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
            if(null==myBusinessAdapter){
                myBusinessAdapter=new MyBusinessAdapter(this,listAll);
                listView.setAdapter(myBusinessAdapter);
            }else{
                myBusinessAdapter.notifyDataSetChanged();
            }
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Business.DataBean dataBean=listAll.get(position);
                    Intent intent=new Intent(MyBusinessActivity.this, BusinessDetailsActivity.class);
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
        myBusiness(HandlerConstant.MY_BUSINESS_SUCCESS1);
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        myBusiness(HandlerConstant.MY_BUSINESS_SUCCESS2);
    }

    /**
     * 获取我的交流
     */
    private void myBusiness(int index){
        HttpMethod.myBusiness(page,index,handler);
    }
}
