package com.ylkj.shopproject.activity.main.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.type.JCDetailsActivity;
import com.ylkj.shopproject.adapter.main.SearchResultAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.view.MyGridView;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;

/**
 * 搜索结果页， 机床，配件
 */
public class SearchResultActivity extends BaseActivity implements View.OnClickListener,MyRefreshLayoutListener {

    private TextView tvJC,tvPJ;
    private ImageView imgJC,imgPJ;
    private RecyclerView recyclerView;
    private SearchResultAdapter searchResultAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        initView();
    }


    /**
     * 初始化控件
     */
    private void initView(){
        tvJC=findViewById(R.id.tv_jc);
        tvPJ=findViewById(R.id.tv_pj);
        imgJC=findViewById(R.id.img_jc);
        imgPJ=findViewById(R.id.img_pj);
        recyclerView=findViewById(R.id.listView);
        tvJC.setOnClickListener(this);
        tvPJ.setOnClickListener(this);
        findViewById(R.id.lin_search).setOnClickListener(this);
        findViewById(R.id.lin_back).setOnClickListener(this);
        searchResultAdapter=new SearchResultAdapter(this,null,new SearchResultAdapter.OnItemClickListener(){
            public void onItemClick(int position) {
                setClass(JCDetailsActivity.class);
            }
        });

         recyclerView.setLayoutManager(new GridLayoutManager(this, 2));//网格布局
        recyclerView.setAdapter(searchResultAdapter);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //机床
            case R.id.tv_jc:
                tvJC.setTextSize(18);
                tvJC.setTextColor(getResources().getColor(R.color.color_37C7B5));
                tvPJ.setTextSize(15);
                tvPJ.setTextColor(getResources().getColor(R.color.color_666666));
                imgJC.setVisibility(View.VISIBLE);
                imgPJ.setVisibility(View.GONE);
                break;
            //配件
            case R.id.tv_pj:
                tvJC.setTextSize(15);
                tvJC.setTextColor(getResources().getColor(R.color.color_666666));
                tvPJ.setTextSize(18);
                tvPJ.setTextColor(getResources().getColor(R.color.color_37C7B5));
                imgJC.setVisibility(View.GONE);
                imgPJ.setVisibility(View.VISIBLE);

                break;
            case R.id.lin_search:
                 setClass(SearchActivity.class);
                 break;
            case R.id.lin_back:
                 finish();
                 break;
        }
    }

    @Override
    public void onRefresh(View view) {

    }

    @Override
    public void onLoadMore(View view) {

    }
}
