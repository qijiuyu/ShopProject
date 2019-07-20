package com.ylkj.shopproject.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.main.pjsc.PeiJianDetailsActivity;
import com.ylkj.shopproject.activity.type.JCDetailsActivity;
import com.ylkj.shopproject.adapter.main.MoreTopicAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.MainHot;
import com.zxdc.utils.library.bean.MainJX;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyRefreshLayout;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;
import java.util.ArrayList;
import java.util.List;
/**
 * 专题下的商品列表
 */
public class MoreTopicActivity extends BaseActivity  implements MyRefreshLayoutListener {

    private RecyclerView recyclerView;
    private MyRefreshLayout mRefreshLayout;
    //专题对象
    private MainJX.DataBean dataBean;
    //当前页数
    private int page=1;
    //总数据集合
    private List<MainHot.DataBean> listAll=new ArrayList<>();
    private MoreTopicAdapter moreTopicAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_topic);
        initView();
        getMoreTopic(HandlerConstant.GET_MORE_TOPIC_SUCCESS1);
    }

    /**
     * 初始化
     */
    private void initView(){
        dataBean= (MainJX.DataBean) getIntent().getSerializableExtra("dataBean");
        if(null==dataBean){
            return;
        }
        TextView tvTitle=findViewById(R.id.tv_title);
        tvTitle.setText(dataBean.getTitle());
        mRefreshLayout=findViewById(R.id.re_list);
        recyclerView=findViewById(R.id.listView);
        //刷新加载
        mRefreshLayout.setMyRefreshLayoutListener(this);
        //设置recyclie网格布局
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    private Handler handler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what) {
                //查询数据回执
                case HandlerConstant.GET_MORE_TOPIC_SUCCESS1:
                    listAll.clear();
                    mRefreshLayout.refreshComplete();
                    refresh((MainHot) msg.obj);
                    break;
                case HandlerConstant.GET_MORE_TOPIC_SUCCESS2:
                    mRefreshLayout.loadMoreComplete();
                    refresh((MainHot) msg.obj);
                    break;
                case HandlerConstant.REQUST_ERROR:
                    ToastUtil.showLong(getString(R.string.net_error));
                    break;
            }
            return false;
        }
    });

    /**
     * 刷新界面数据
     */
    private void refresh(MainHot mainHot) {
        if (null == mainHot) {
            return;
        }
        if (mainHot.isSussess()) {
            List<MainHot.DataBean> list = mainHot.getData();
            listAll.addAll(list);
            if (null == moreTopicAdapter) {
                moreTopicAdapter = new MoreTopicAdapter(this, listAll,new MoreTopicAdapter.OnItemClickListener(){
                    public void onItemClick(int position) {
                        MainHot.DataBean dataBean=listAll.get(position);
                        Intent intent=new Intent();
                        if(dataBean.getType()==1){
                            intent.setClass(MoreTopicActivity.this, JCDetailsActivity.class);
                        }else{
                            intent.setClass(MoreTopicActivity.this, PeiJianDetailsActivity.class);
                        }
                        intent.putExtra("spuid",dataBean.getSpuid());
                        startActivity(intent);
                    }
                });
                recyclerView.setAdapter(moreTopicAdapter);
            } else {
                moreTopicAdapter.notifyDataSetChanged();
            }
            if (list.size() < 20) {
                mRefreshLayout.setIsLoadingMoreEnabled(false);
            }
        } else {
            ToastUtil.showLong(mainHot.getDesc());
        }
    }

    @Override
    public void onRefresh(View view) {
        page=1;
        getMoreTopic(HandlerConstant.GET_MORE_TOPIC_SUCCESS1);
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        getMoreTopic(HandlerConstant.GET_MORE_TOPIC_SUCCESS2);
    }

    /**
     * 获取专题更多商品接口
     */
    private void getMoreTopic(int index){
        HttpMethod.getMoreTopic(dataBean.getTopicid(),page,index,handler);
    }
}
