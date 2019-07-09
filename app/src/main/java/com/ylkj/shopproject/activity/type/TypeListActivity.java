package com.ylkj.shopproject.activity.type;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.adapter.main.SearchResultAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.view.MyRefreshLayout;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;

/**
 * 分类列表
 */
public class TypeListActivity extends BaseActivity implements MyRefreshLayoutListener {

    private MyRefreshLayout mRefreshLayout;
    private RecyclerView recyclerView;
    private SearchResultAdapter searchResultAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_list);
        initView();
    }


    /**
     * 初始化
     */
    private void initView(){
        TextView tvTitle=findViewById(R.id.tv_title);
        recyclerView=findViewById(R.id.listView);
        mRefreshLayout=findViewById(R.id.re_list);
        //刷新加载
        mRefreshLayout.setMyRefreshLayoutListener(this);
        searchResultAdapter=new SearchResultAdapter(this,null,new SearchResultAdapter.OnItemClickListener(){
            public void onItemClick(int position) {
                setClass(JCDetailsActivity.class);
            }
        });

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));//网格布局
        recyclerView.setAdapter(searchResultAdapter);
        //返回
        findViewById(R.id.lin_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TypeListActivity.this.finish();
            }
        });
    }

    @Override
    public void onRefresh(View view) {

    }

    @Override
    public void onLoadMore(View view) {

    }
}
