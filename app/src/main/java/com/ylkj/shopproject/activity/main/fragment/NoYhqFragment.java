package com.ylkj.shopproject.activity.main.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.main.pjsc.SelectYHQActivity;
import com.ylkj.shopproject.adapter.main.SelectYhqAdapter;
import com.zxdc.utils.library.base.BaseFragment;
import com.zxdc.utils.library.bean.Coupon;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 不可以使用的优惠券
 */
public class NoYhqFragment extends BaseFragment{

    private ListView listView;
    //数据集合
    private List<Coupon.DataBean> list = new ArrayList<>();
    private SelectYhqAdapter selectYhqAdapter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        listView = new ListView(mActivity);
        listView.setDivider(null);
        //获取订单结算也优惠券列表接口
        getOrderYhq();
        return listView;
    }

    private Handler handler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what) {
                //查询数据回执
                case HandlerConstant.GET_ORDER_YHQ_SUCCESS:
                      final Coupon coupon= (Coupon) msg.obj;
                      if(null==coupon){
                          break;
                      }
                      if(coupon.isSussess()){
                          list=coupon.getData();
                          selectYhqAdapter=new SelectYhqAdapter(mActivity,list,2);
                          listView.setAdapter(selectYhqAdapter);
                      }else{
                          ToastUtil.showLong(coupon.getDesc());
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
     * 获取订单结算也优惠券列表接口
     */
    private void getOrderYhq() {
       HttpMethod.getOrderYhq(2, SelectYHQActivity.parameter,handler);
    }

    @Override
    public void onDestroy() {
        removeHandler(handler);
        super.onDestroy();
    }
}

