package com.ylkj.shopproject.activity.business;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.adapter.business.BusinessNewsAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.News;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyRefreshLayout;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;
import java.util.ArrayList;
import java.util.List;
/**
 * 生意圈消息
 */
public class BusinessNewsActivity extends BaseActivity  implements MyRefreshLayoutListener {

    private ListView listView;
    private MyRefreshLayout mRefreshLayout;
    //当前页数
    private int page=1;
    private BusinessNewsAdapter businessNewsAdapter;
    private List<News.DataBean> listAll=new ArrayList<>();
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_news);
        initView();
        getNews(HandlerConstant.GET_NEWS_SUCCESS1);
    }

    /**
     * 初始化
     */
    private void initView(){
        listView=findViewById(R.id.listView);
        mRefreshLayout=findViewById(R.id.re_list);
        //刷新加载
        mRefreshLayout.setMyRefreshLayoutListener(this);
    }

    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                //查询数据回执
                case HandlerConstant.GET_NEWS_SUCCESS1:
                    listAll.clear();
                    mRefreshLayout.refreshComplete();
                    refresh((News) msg.obj);
                    break;
                case HandlerConstant.GET_NEWS_SUCCESS2:
                    mRefreshLayout.loadMoreComplete();
                    refresh((News) msg.obj);
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
    private void refresh(News news){
        if(null==news){
            return;
        }
        if(news.isSussess()){
            List<News.DataBean> list=news.getData();
            listAll.addAll(list);
            if(null==businessNewsAdapter){
                businessNewsAdapter=new BusinessNewsAdapter(this,listAll);
                listView.setAdapter(businessNewsAdapter);
            }else{
                businessNewsAdapter.notifyDataSetChanged();
            }
            if(list.size()<20){
                mRefreshLayout.setIsLoadingMoreEnabled(false);
            }
        }else{
            ToastUtil.showLong(news.getDesc());
        }
    }


    @Override
    public void onRefresh(View view) {
        page=1;
        getNews(HandlerConstant.GET_NEWS_SUCCESS1);
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        getNews(HandlerConstant.GET_NEWS_SUCCESS2);
    }

    /**
     * 获取消息数据
     */
    private  void getNews(int index){
        HttpMethod.getNews("2",page,index,handler);
    }

    @Override
    protected void onDestroy() {
        removeHandler(handler);
        super.onDestroy();
    }
}
