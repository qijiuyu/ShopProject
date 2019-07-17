package com.ylkj.shopproject.activity.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.main.pjsc.PeiJianDetailsActivity;
import com.ylkj.shopproject.activity.main.search.SearchResultActivity;
import com.ylkj.shopproject.activity.type.JCDetailsActivity;
import com.ylkj.shopproject.adapter.main.AppNewsAdapter;
import com.ylkj.shopproject.adapter.main.SearchResultAdapter;
import com.zxdc.utils.library.base.BaseFragment;
import com.zxdc.utils.library.bean.SearchGood;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyRefreshLayout;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 配件列表
 */
public class PeiJianFragment extends BaseFragment  implements MyRefreshLayoutListener {

    private View view;
    private MyRefreshLayout mRefreshLayout;
    private RecyclerView listView;
    private SearchResultAdapter searchResultAdapter;
    //fragment是否可见
    private boolean isVisibleToUser = false;
    //当前页数
    private int page = 1;
    private List<SearchGood.DataBean> listAll=new ArrayList<>();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recycleview, container, false);
        listView=view.findViewById(R.id.listView);
        mRefreshLayout=view.findViewById(R.id.re_list);
        //刷新加载
        mRefreshLayout.setMyRefreshLayoutListener(this);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(mActivity, 2);
        listView.setLayoutManager(gridLayoutManager);//网格布局

        //关键字搜索分页查询接口
        searchGoods();
        return view;
    }


    private Handler handler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what) {
                //查询数据回执
                case HandlerConstant.SEARCH_GOODS_SUCCESS1:
                    listAll.clear();
                    mRefreshLayout.refreshComplete();
                    refresh((SearchGood) msg.obj);
                    break;
                case HandlerConstant.SEARCH_GOODS_SUCCESS2:
                    mRefreshLayout.loadMoreComplete();
                    refresh((SearchGood) msg.obj);
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
    private void refresh(SearchGood searchGood) {
        if (null == searchGood) {
            return;
        }
        if (searchGood.isSussess()) {
            List<SearchGood.DataBean> list = searchGood.getData();
            listAll.addAll(list);
            if(null==searchResultAdapter){
                searchResultAdapter=new SearchResultAdapter(mActivity,listAll,new SearchResultAdapter.OnItemClickListener(){
                    public void onItemClick(int position) {
                        SearchGood.DataBean dataBean=listAll.get(position);
                        Intent intent=new Intent(mActivity,PeiJianDetailsActivity.class);
                        intent.putExtra("spuid",dataBean.getId());
                        startActivity(intent);
                    }
                });
                listView.setAdapter(searchResultAdapter);
            }else{
                searchResultAdapter.notifyDataSetChanged();
            }
            if (list.size() < 20) {
                mRefreshLayout.setIsLoadingMoreEnabled(false);
            }
        } else {
            ToastUtil.showLong(searchGood.getDesc());
        }
    }

    @Override
    public void onRefresh(View view) {
        page=1;
        HttpMethod.searchGoods(2, SearchResultActivity.keys,page,HandlerConstant.SEARCH_GOODS_SUCCESS1,handler);
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        HttpMethod.searchGoods(2, SearchResultActivity.keys,page,HandlerConstant.SEARCH_GOODS_SUCCESS2,handler);
    }

    /**
     * 关键字搜索分页查询接口
     */
    private void searchGoods(){
        if (null != view && isVisibleToUser && listAll.size() == 0) {
            HttpMethod.searchGoods(2, SearchResultActivity.keys,page,HandlerConstant.SEARCH_GOODS_SUCCESS1,handler);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        //关键字搜索分页查询接口
        searchGoods();
    }

    @Override
    public void onDestroy() {
        removeHandler(handler);
        super.onDestroy();
    }
}
