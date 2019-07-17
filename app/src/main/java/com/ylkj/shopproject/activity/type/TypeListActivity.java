package com.ylkj.shopproject.activity.type;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.main.pjsc.PeiJianDetailsActivity;
import com.ylkj.shopproject.activity.type.persenter.TypeListPersenter;
import com.ylkj.shopproject.adapter.main.SearchResultAdapter;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.PJGoodList;
import com.zxdc.utils.library.bean.Type;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyRefreshLayout;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类列表
 */
public class TypeListActivity extends BaseActivity implements MyRefreshLayoutListener {

    private MyRefreshLayout mRefreshLayout;
    private RecyclerView recyclerView;
    private SearchResultAdapter searchResultAdapter;
    //分类对象
    private Type.TypeBean typeBean;
    //MVP对象
    private TypeListPersenter typeListPersenter;
    //当前页数
    private int page=1;
    //总数据集合
    private List<PJGoodList.GoodList> listAll=new ArrayList<>();
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_list);
        //注册EventBus
        EventBus.getDefault().register(this);
        //实例化MVP
        typeListPersenter=new TypeListPersenter(this);
        initView();
        typeListPersenter.getGoodList(HandlerConstant.GET_PJ_GOODS_LIST_SUCCESS1,typeBean.getId(),page);
    }


    /**
     * 初始化
     */
    private void initView(){
        typeBean= (Type.TypeBean) getIntent().getSerializableExtra("typeBean");
        TextView tvTitle=findViewById(R.id.tv_title);
        tvTitle.setText(typeBean.getName());
        recyclerView=findViewById(R.id.listView);
        mRefreshLayout=findViewById(R.id.re_list);
        //刷新加载
        mRefreshLayout.setMyRefreshLayoutListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));//网格布局
        //返回
        findViewById(R.id.lin_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TypeListActivity.this.finish();
            }
        });
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
            searchResultAdapter=new SearchResultAdapter(this,listAll,new SearchResultAdapter.OnItemClickListener(){
                public void onItemClick(int position) {
                    final PJGoodList.GoodList goodList=listAll.get(position);
                    Intent intent=new Intent(TypeListActivity.this,JCDetailsActivity.class);
                    intent.putExtra("spuid",goodList.getSpuid());
                    startActivity(intent);

                    //这是进入拼团详情界面
//                setClass(PinTuanDetailsActivity.class);
                }
            });
            recyclerView.setAdapter(searchResultAdapter);
            if(list.size()<20){
                mRefreshLayout.setIsLoadingMoreEnabled(false);
            }
        }else{
            ToastUtil.showLong(pjGoodList.getDesc());
        }
    }


    /**
     * EventBus注解
     */
    @Subscribe
    public void onEvent(EventBusType eventBusType){
        switch (eventBusType.getStatus()){
            //获取机床商品列表
            case EventStatus.GET_PJ_LIST1:
                listAll.clear();
                mRefreshLayout.refreshComplete();
                refrech((PJGoodList) eventBusType.getObject());
                break;
            //获取机床商品列表--上拉加载
            case EventStatus.GET_PJ_LIST2:
                mRefreshLayout.loadMoreComplete();
                refrech((PJGoodList) eventBusType.getObject());
                break;
        }
    }

    @Override
    public void onRefresh(View view) {
        page=1;
        typeListPersenter.getGoodList(HandlerConstant.GET_PJ_GOODS_LIST_SUCCESS1,typeBean.getId(),page);
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        typeListPersenter.getGoodList(HandlerConstant.GET_PJ_GOODS_LIST_SUCCESS2,typeBean.getId(),page);
    }

    protected void onDestroy() {
        super.onDestroy();
        //关闭eventBus
        EventBus.getDefault().unregister(this);
    }
}
