package com.ylkj.shopproject.activity.user.fragment.order;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.adapter.user.OrderAdapter;
import com.zxdc.utils.library.base.BaseFragment;
import com.zxdc.utils.library.bean.MyOrder;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyRefreshLayout;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;
import java.util.ArrayList;
import java.util.List;
/**
 * 待付款订单
 */
public class DFKfragment extends BaseFragment implements MyRefreshLayoutListener {

    private View  view;
    private ListView listView;
    private MyRefreshLayout mRefreshLayout;
    private OrderAdapter orderAdapter;
    //fragment是否可见
    private boolean isVisibleToUser=false;
    //当前页数
    private int page=1;
    private List<MyOrder.DataBean> listAll=new ArrayList<>();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_order, container, false);
        listView=view.findViewById(R.id.listView);
        mRefreshLayout=view.findViewById(R.id.re_list);
        //刷新加载
        mRefreshLayout.setMyRefreshLayoutListener(this);
        //获取我的订单
        getMyOrder(HandlerConstant.GET_MY_ORDER_SUCCESS1);
        return view;
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                //查询数据回执
                case HandlerConstant.GET_MY_ORDER_SUCCESS1:
                    listAll.clear();
                    mRefreshLayout.refreshComplete();
                    refresh((MyOrder) msg.obj);
                    break;
                case HandlerConstant.GET_MY_ORDER_SUCCESS2:
                    mRefreshLayout.loadMoreComplete();
                    refresh((MyOrder) msg.obj);
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
    private void refresh(MyOrder myOrder){
        if(null==myOrder){
            return;
        }
        if(myOrder.isSussess()){
            List<MyOrder.DataBean> list=myOrder.getData();
            listAll.addAll(list);
            if(null==orderAdapter){
                orderAdapter=new OrderAdapter(mActivity,listAll);
                listView.setAdapter(orderAdapter);
            }else{
                orderAdapter.notifyDataSetChanged();
            }
            if(list.size()<20){
                mRefreshLayout.setIsLoadingMoreEnabled(false);
            }
        }else{
            ToastUtil.showLong(myOrder.getDesc());
        }
    }

    @Override
    public void onRefresh(View view) {
        page=1;
        HttpMethod.getMyOrder(0,page,HandlerConstant.GET_MY_ORDER_SUCCESS1,handler);
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        HttpMethod.getMyOrder(0,page,HandlerConstant.GET_MY_ORDER_SUCCESS2,handler);
    }


    /**
     * 我的订单
     */
    private void getMyOrder(int index){
        if(null!=view && isVisibleToUser && listAll.size()==0){
            HttpMethod.getMyOrder(0,page,index,handler);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser=isVisibleToUser;
        //获取我的订单
        getMyOrder(HandlerConstant.GET_MY_ORDER_SUCCESS1);
    }

    @Override
    public void onDestroy() {
        removeHandler(handler);
        super.onDestroy();
    }
}
