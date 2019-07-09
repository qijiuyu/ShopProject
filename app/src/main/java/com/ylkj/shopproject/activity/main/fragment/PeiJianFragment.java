package com.ylkj.shopproject.activity.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.type.JCDetailsActivity;
import com.ylkj.shopproject.adapter.main.AppNewsAdapter;
import com.ylkj.shopproject.adapter.main.SearchResultAdapter;
import com.zxdc.utils.library.base.BaseFragment;
import com.zxdc.utils.library.view.MyRefreshLayout;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;

/**
 * 配件列表
 */
public class PeiJianFragment extends BaseFragment implements MyRefreshLayoutListener {

    private MyRefreshLayout mRefreshLayout;
    private RecyclerView listView;
    private SearchResultAdapter searchResultAdapter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.recycleview, container, false);
        listView=view.findViewById(R.id.listView);
        mRefreshLayout=view.findViewById(R.id.re_list);
        //刷新加载
        mRefreshLayout.setMyRefreshLayoutListener(this);

        searchResultAdapter=new SearchResultAdapter(mActivity,null,new SearchResultAdapter.OnItemClickListener(){
            public void onItemClick(int position) {
                setClass(JCDetailsActivity.class);
            }
        });
        GridLayoutManager gridLayoutManager=new GridLayoutManager(mActivity, 2);
        listView.setLayoutManager(gridLayoutManager);//网格布局
        listView.setAdapter(searchResultAdapter);
        return view;
    }

    @Override
    public void onRefresh(View view) {

    }

    @Override
    public void onLoadMore(View view) {

    }
}
