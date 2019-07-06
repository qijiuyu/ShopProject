package com.ylkj.shopproject.activity.user.fragment.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.adapter.user.OrderAdapter;
import com.zxdc.utils.library.base.BaseFragment;

/**
 * 待发货订单
 */
public class DFHfragment extends BaseFragment {

    private ListView listView;
    private OrderAdapter orderAdapter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_order, container, false);
        listView=view.findViewById(R.id.listview);
        orderAdapter=new OrderAdapter(mActivity,null);
        listView.setAdapter(orderAdapter);
        return view;
    }
}
