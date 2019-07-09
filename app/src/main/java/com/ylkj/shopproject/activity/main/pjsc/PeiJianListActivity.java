package com.ylkj.shopproject.activity.main.pjsc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.main.persenter.PeiJianListPersenter;
import com.ylkj.shopproject.activity.main.search.SearchActivity;
import com.ylkj.shopproject.activity.type.JCDetailsActivity;
import com.ylkj.shopproject.adapter.main.SearchResultAdapter;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.view.MyRefreshLayout;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * 配件商城-->分类下的列表
 */
public class PeiJianListActivity extends BaseActivity implements MyRefreshLayoutListener,View.OnClickListener {

    private RecyclerView recyclerView;
    private MyRefreshLayout mRefreshLayout;
    private SearchResultAdapter searchResultAdapter;
    private PeiJianListPersenter peiJianListPersenter;
    //侧滑菜单
    public static DrawerLayout mDrawerLayout;
    /**
     * 1：默认排序，   2：新品排序
     */
    private int type=1;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peijian_list);
        //注册EventBus
        EventBus.getDefault().register(this);
        peiJianListPersenter=new PeiJianListPersenter(this);
        initView();
    }


    /**
     * 初始化控件
     */
    private void initView(){
        mDrawerLayout =findViewById(R.id.drawer_layout);
        mRefreshLayout=findViewById(R.id.re_list);
        recyclerView=findViewById(R.id.listView);
        //刷新加载
        mRefreshLayout.setMyRefreshLayoutListener(this);

        searchResultAdapter=new SearchResultAdapter(this,null,new SearchResultAdapter.OnItemClickListener(){
            public void onItemClick(int position) {
                setClass(PeiJianDetailsActivity.class);

                //这是进入拼团详情界面
//                setClass(PinTuanDetailsActivity.class);
            }
        });
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);//网格布局
        recyclerView.setAdapter(searchResultAdapter);

        findViewById(R.id.img_search).setOnClickListener(this);
        findViewById(R.id.lin_type).setOnClickListener(this);
        findViewById(R.id.lin_screening).setOnClickListener(this);
        findViewById(R.id.lin_back).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //进入搜索界面
            case R.id.img_search:
                setClass(SearchActivity.class);
                break;
             //选择默认分类
            case R.id.lin_type:
                 RelativeLayout rel=findViewById(R.id.rel);
                 peiJianListPersenter.showTypeDialog(rel,type);
                 break;
            //筛选
            case R.id.lin_screening:
                 mDrawerLayout.openDrawer(Gravity.RIGHT);
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


    /**
     * EventBus注解
     */
    @Subscribe
    public void onEvent(EventBusType eventBusType){
        if(eventBusType.getStatus()== EventStatus.PJSC_SELECT_TYPE){
            type= (int) eventBusType.getObject();
            LogUtils.e(type+"++++++++++++++++++++");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //关闭eventBus
        EventBus.getDefault().unregister(this);
    }

}
