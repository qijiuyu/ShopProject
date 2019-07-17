package com.ylkj.shopproject.activity.user.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.user.yhq.MyYhqActivity;
import com.ylkj.shopproject.adapter.user.MyYhqAdapter;
import com.zxdc.utils.library.base.BaseFragment;
import com.zxdc.utils.library.bean.Coupon;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyRefreshLayout;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;
import java.util.ArrayList;
import java.util.List;
public class MyYhqFragment extends BaseFragment implements MyRefreshLayoutListener {

    private View  view;
    private MyRefreshLayout mRefreshLayout;
    private ListView listView;
    private MyYhqAdapter myYhqAdapter;
    //当前页数
    private int page=1;
    //fragment是否可见
    private boolean isVisibleToUser=false;
    //优惠券数据集合
    private List<Coupon.DataBean> listAll=new ArrayList<>();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.listview, container, false);
        mRefreshLayout=view.findViewById(R.id.re_list);
        listView=view.findViewById(R.id.listView);
        //刷新加载
        mRefreshLayout.setMyRefreshLayoutListener(this);

        //获取我的优惠券
        MyCoupon(HandlerConstant.MY_COUPON_SUCCESS1);
        return view;
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                //查询数据回执
                case HandlerConstant.MY_COUPON_SUCCESS1:
                     listAll.clear();
                     mRefreshLayout.refreshComplete();
                     refresh((Coupon) msg.obj);
                    break;
                case HandlerConstant.MY_COUPON_SUCCESS2:
                     mRefreshLayout.loadMoreComplete();
                     refresh((Coupon) msg.obj);
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
    private void refresh(Coupon coupon){
        if(null==coupon){
            return;
        }
        if(coupon.isSussess()){
            List<Coupon.DataBean> list=coupon.getData();
            listAll.addAll(list);
            if(null==myYhqAdapter){
                myYhqAdapter=new MyYhqAdapter(mActivity,listAll,MyYhqActivity.index++);
                listView.setAdapter(myYhqAdapter);
            }else{
                myYhqAdapter.notifyDataSetChanged();
            }
            if(list.size()<20){
                mRefreshLayout.setIsLoadingMoreEnabled(false);
            }
        }else{
            ToastUtil.showLong(coupon.getDesc());
        }
    }


    @Override
    public void onRefresh(View view) {
        page=1;
        HttpMethod.MyCoupon(++MyYhqActivity.index,page,HandlerConstant.MY_COUPON_SUCCESS1,handler);
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        HttpMethod.MyCoupon(++MyYhqActivity.index,page,HandlerConstant.MY_COUPON_SUCCESS2,handler);
    }


    /**
     * 获取我的优惠券
     */
    private void MyCoupon(int index){
        if(null!=view && isVisibleToUser && listAll.size()==0){
            HttpMethod.MyCoupon(++MyYhqActivity.index,page,index,handler);
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser=isVisibleToUser;
        //获取我的优惠券
        MyCoupon(HandlerConstant.MY_COUPON_SUCCESS1);
    }

    @Override
    public void onDestroy() {
        removeHandler(handler);
        super.onDestroy();
    }

}
