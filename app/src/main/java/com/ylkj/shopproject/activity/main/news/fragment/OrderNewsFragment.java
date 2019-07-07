package com.ylkj.shopproject.activity.main.news.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.main.pjsc.PinTuanStatusActivity;
import com.ylkj.shopproject.adapter.main.OrderNewsAdapter;
import com.ylkj.shopproject.adapter.user.MyTuanAdapter;
import com.zxdc.utils.library.base.BaseFragment;
import com.zxdc.utils.library.view.MyRefreshLayout;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;

/**
 * 订单消息
 */
public class OrderNewsFragment extends BaseFragment implements MyRefreshLayoutListener {

    private MyRefreshLayout mRefreshLayout;
    private ListView listView;
    private OrderNewsAdapter orderNewsAdapter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.listview, container, false);
        listView=view.findViewById(R.id.listView);
        mRefreshLayout=view.findViewById(R.id.re_list);
        //刷新加载
        mRefreshLayout.setMyRefreshLayoutListener(this);

        orderNewsAdapter=new OrderNewsAdapter(mActivity,null);
        listView.setAdapter(orderNewsAdapter);
        return view;
    }

    @Override
    public void onRefresh(View view) {

    }

    @Override
    public void onLoadMore(View view) {

    }
}
