package com.ylkj.shopproject.activity.main.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.adapter.main.AppNewsAdapter;
import com.ylkj.shopproject.adapter.main.OrderNewsAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.view.MyRefreshLayout;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;

/**
 * 首页消息
 */
public class MainNewsActivity extends BaseActivity implements View.OnClickListener,MyRefreshLayoutListener {
    private TextView tvOrderNews,tvAppNews;
    private ImageView imgOrder,imgApp;
    private ListView listView;
    private MyRefreshLayout mRefreshLayout;
    //订单消息
    private OrderNewsAdapter orderNewsAdapter;
    //平台消息
    private AppNewsAdapter appNewsAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_news);
        initView();
    }


    /**
     * 初始化控件
     */
    private void initView(){
        tvOrderNews=findViewById(R.id.tv_order_news);
        tvAppNews=findViewById(R.id.tv_app_news);
        imgOrder=findViewById(R.id.img_order);
        imgApp=findViewById(R.id.img_app);
        mRefreshLayout=findViewById(R.id.re_list);
        listView=findViewById(R.id.listView);
        tvOrderNews.setOnClickListener(this);
        tvAppNews.setOnClickListener(this);
        //刷新加载
        mRefreshLayout.setMyRefreshLayoutListener(this);
        findViewById(R.id.lin_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //订单消息
            case R.id.tv_order_news:
                 tvOrderNews.setTextSize(18);
                 tvOrderNews.setTextColor(getResources().getColor(R.color.color_37C7B5));
                 tvAppNews.setTextSize(15);
                 tvAppNews.setTextColor(getResources().getColor(R.color.color_666666));
                 imgOrder.setVisibility(View.VISIBLE);
                 imgApp.setVisibility(View.GONE);
                 orderNewsAdapter=new OrderNewsAdapter(this,null);
                 listView.setAdapter(orderNewsAdapter);
                 break;
            //平台消息
            case R.id.tv_app_news:
                 tvOrderNews.setTextSize(15);
                 tvOrderNews.setTextColor(getResources().getColor(R.color.color_666666));
                 tvAppNews.setTextSize(18);
                 tvAppNews.setTextColor(getResources().getColor(R.color.color_37C7B5));
                 imgOrder.setVisibility(View.GONE);
                 imgApp.setVisibility(View.VISIBLE);

                 appNewsAdapter=new AppNewsAdapter(this,null);
                 listView.setAdapter(appNewsAdapter);
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
