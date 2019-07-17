package com.ylkj.shopproject.activity.business.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.baidu.mapapi.model.LatLng;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.business.BusinessActivity;
import com.ylkj.shopproject.activity.business.BusinessDetailsActivity;
import com.ylkj.shopproject.adapter.business.BusinessAdapter;
import com.zxdc.utils.library.base.BaseFragment;
import com.zxdc.utils.library.bean.Business;
import com.zxdc.utils.library.bean.ZzfuType;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.SPUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyRefreshLayout;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;
import java.util.ArrayList;
import java.util.List;
public class BusinessFragment extends BaseFragment implements MyRefreshLayoutListener {

    private View  view;
    private ListView listView;
    private MyRefreshLayout mRefreshLayout;
    private BusinessAdapter businessAdapter;
    //fragment是否可见
    private boolean isVisibleToUser=false;
    //当前页数
    private int page=1;
    //数据集合
    private List<Business.DataBean> listAll=new ArrayList<>();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.listview, container, false);
        listView=view.findViewById(R.id.listView);
        mRefreshLayout=view.findViewById(R.id.re_list);
        //刷新加载
        mRefreshLayout.setMyRefreshLayoutListener(this);
        //获取生意圈列表接口
        getBusinessList(HandlerConstant.GET_BUSINESS_LIST_SUCCESS1);
        return view;
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
            if(null==businessAdapter){
                businessAdapter=new BusinessAdapter(mActivity,listAll);
                listView.setAdapter(businessAdapter);
            }else{
                businessAdapter.notifyDataSetChanged();
            }
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Business.DataBean dataBean=listAll.get(position);
                    Intent intent=new Intent(mActivity, BusinessDetailsActivity.class);
                    intent.putExtra("dataBean",dataBean);
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
    private void getBusinessList(int index){
        if(null!=view && isVisibleToUser && listAll.size()==0){
           getBusiness(index);
        }
    }

    private void getBusiness(int index){
        final LatLng latLng= (LatLng) SPUtil.getInstance(mActivity).getObject(SPUtil.LATLNG,LatLng.class);
        final ZzfuType.dataBean dataBean= BusinessActivity.typeList.get(BusinessActivity.index);
        HttpMethod.getBusinessList(String.valueOf(latLng.latitude),String.valueOf(latLng.longitude),dataBean.getId(),page,index ,handler);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser=isVisibleToUser;
        //获取生意圈列表接口
        getBusinessList(HandlerConstant.GET_BUSINESS_LIST_SUCCESS1);
    }

    @Override
    public void onDestroy() {
        removeHandler(handler);
        super.onDestroy();
    }
}
