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
import com.ylkj.shopproject.adapter.main.PeiJianListAdapter;
import com.ylkj.shopproject.adapter.main.SearchResultAdapter;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.PJGoodList;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyRefreshLayout;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.util.ArrayList;
import java.util.List;
/**
 * 配件商城-->分类下的列表
 */
public class PeiJianListActivity extends BaseActivity implements MyRefreshLayoutListener,View.OnClickListener {

    private RecyclerView recyclerView;
    private MyRefreshLayout mRefreshLayout;
    private PeiJianListAdapter peiJianListAdapter;
    private PeiJianListPersenter peiJianListPersenter;
    //侧滑菜单
    public static DrawerLayout mDrawerLayout;
    //分类ID
    private int typeId;
    /**
     * 1：默认排序，   2：新品排序
     */
    private int type=1;
    //当前页数
    private int page=1;
    //总数据集合
    private List<PJGoodList.GoodList> listAll=new ArrayList<>();
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peijian_list);
        //注册EventBus
        EventBus.getDefault().register(this);
        peiJianListPersenter=new PeiJianListPersenter(this);
        initView();
        //获取配件商品列表
        DialogUtil.showProgress(this,"数据加载中");
        peiJianListPersenter.getPJGoodList(HandlerConstant.GET_PJ_GOODS_LIST_SUCCESS1,typeId,page);
    }


    /**
     * 初始化控件
     */
    private void initView(){
        typeId=getIntent().getIntExtra("typeId",0);
        mDrawerLayout =findViewById(R.id.drawer_layout);
        mRefreshLayout=findViewById(R.id.re_list);
        recyclerView=findViewById(R.id.listView);
        //刷新加载
        mRefreshLayout.setMyRefreshLayoutListener(this);
        //设置recyclie网格布局
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

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





    /**
     * 刷新界面数据
     */
    private void refrech(PJGoodList pjGoodList){
        if(null==pjGoodList){
            return;
        }
        if(pjGoodList.isSussess()){
            List<PJGoodList.GoodList> list=pjGoodList.getData();
            listAll.addAll(list);
            peiJianListAdapter=new PeiJianListAdapter(this,listAll,new PeiJianListAdapter.OnItemClickListener(){
                public void onItemClick(int position) {
                    setClass(PeiJianDetailsActivity.class);

                    //这是进入拼团详情界面
//                setClass(PinTuanDetailsActivity.class);
                }
            });
            recyclerView.setAdapter(peiJianListAdapter);
            if(list.size()<20){
                mRefreshLayout.setIsLoadingMoreEnabled(false);
            }
        }else{
            ToastUtil.showLong(pjGoodList.getDesc());
        }
    }


    @Override
    public void onRefresh(View view) {
        page=1;
        peiJianListPersenter.getPJGoodList(HandlerConstant.GET_PJ_GOODS_LIST_SUCCESS1,typeId,page);
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        peiJianListPersenter.getPJGoodList(HandlerConstant.GET_PJ_GOODS_LIST_SUCCESS1,typeId,page);
    }


    /**
     * EventBus注解
     */
    @Subscribe
    public void onEvent(EventBusType eventBusType){
        switch (eventBusType.getStatus()){
            //获取配件商品列表
            case EventStatus.GET_PJ_LIST1:
                  listAll.clear();
                  mRefreshLayout.refreshComplete();
                  refrech((PJGoodList) eventBusType.getObject());
                  break;
            //获取配件商品列表--上拉加载
            case EventStatus.GET_PJ_LIST2:
                 mRefreshLayout.loadMoreComplete();
                 refrech((PJGoodList) eventBusType.getObject());
                break;
            //选择默认/新品查询
            case EventStatus.PJSC_SELECT_TYPE:
                 break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //关闭eventBus
        EventBus.getDefault().unregister(this);
    }

}
