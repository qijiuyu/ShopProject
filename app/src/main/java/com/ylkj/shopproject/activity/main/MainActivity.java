package com.ylkj.shopproject.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ScrollView;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.main.fault.AddFaultActivity;
import com.ylkj.shopproject.activity.main.news.MainNewsActivity;
import com.ylkj.shopproject.activity.main.persenter.MainPersenter;
import com.ylkj.shopproject.activity.main.pjsc.PeiJianDetailsActivity;
import com.ylkj.shopproject.activity.main.pjsc.PeiJianTypeActivity;
import com.ylkj.shopproject.activity.main.search.SearchActivity;
import com.ylkj.shopproject.activity.main.zzfu.ZzfuTypeActivity;
import com.ylkj.shopproject.activity.type.JCDetailsActivity;
import com.ylkj.shopproject.activity.user.login.LoginActivity;
import com.ylkj.shopproject.activity.webview.WebViewActivity;
import com.ylkj.shopproject.adapter.main.HotPartsAdapter;
import com.ylkj.shopproject.adapter.main.NewsRecommendedAdapter;
import com.ylkj.shopproject.adapter.main.SentimentRecommendedAdapter;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.ylkj.shopproject.util.GetLocation;
import com.ylkj.shopproject.view.HorizontalListView;
import com.youth.banner.Banner;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.base.MainXP;
import com.zxdc.utils.library.bean.MainHot;
import com.zxdc.utils.library.bean.MainRQ;
import com.zxdc.utils.library.view.MeasureListView;
import com.zxdc.utils.library.view.MyGridView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener,View.OnTouchListener{
    private ScrollView scrollView;
    private Banner banner,banner2;
    private MeasureListView rqListView;
    private MainPersenter mainPersenter;
    private HorizontalListView listXP;
    private MyGridView rmGird;
    //新品推荐的adapter
    private NewsRecommendedAdapter newsRecommendedAdapter;
    //人气推荐的adapter
    private SentimentRecommendedAdapter sentimentRecommendedAdapter;
    //热门配件
    private HotPartsAdapter hotPartsAdapter;
    //新品数据的集合
    private List<MainXP.DataBean> xpList=new ArrayList<>();
    //人气推荐数据集合
    private List<MainRQ.DataBean> rqList=new ArrayList<>();
    //热门推荐数据集合
    private List<MainHot.DataBean> hotList=new ArrayList<>();
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPersenter=new MainPersenter(this);
        //注册eventBus
        EventBus.getDefault().register(this);
        initView();
        //开始定位
        GetLocation.getInstance().setLocation(this);
        //查询顶部广告轮播图
        mainPersenter.getAbvert(banner);
        //获取精选专题接口
        mainPersenter.mainJX(banner2);
        //获取新品推荐数据
        mainPersenter.mainXP();
        //获取首页人气推荐
        mainPersenter.mainRQ();
        //获取首页热门
        mainPersenter.mainHot();
        //消息列表入口红点接口
        mainPersenter.isNews();
    }


    /**
     * 初始化控件
     */
    private void initView(){
        scrollView=findViewById(R.id.scrollView);
        banner=findViewById(R.id.banner);
        banner2=findViewById(R.id.banner2);
        listXP=findViewById(R.id.list_xptj);
        rmGird=findViewById(R.id.gv_rmpj);
        rqListView=findViewById(R.id.rq_list);
        scrollView.setOnTouchListener(this);
        findViewById(R.id.lin_mc).setOnClickListener(this);
        findViewById(R.id.lin_pj).setOnClickListener(this);
        findViewById(R.id.lin_bx).setOnClickListener(this);
        findViewById(R.id.lin_zz).setOnClickListener(this);
        findViewById(R.id.lin_zl).setOnClickListener(this);
        findViewById(R.id.lin_search).setOnClickListener(this);
        findViewById(R.id.rel_news).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //磨床
            case R.id.lin_mc:
                 setClass(MoChuangActivity.class);
                 break;
            //配件商城
            case R.id.lin_pj:
                 setClass(PeiJianTypeActivity.class);
                 break;
            //在线报修
            case R.id.lin_bx:
                 setClass(AddFaultActivity.class);
                 break;
            //增值服务
            case R.id.lin_zz:
                 setClass(ZzfuTypeActivity.class);
                break;
            //融资租赁
            case R.id.lin_zl:
                Intent intent=new Intent(this, WebViewActivity.class);
                intent.putExtra("type",4);
                startActivity(intent);
                 break;
            //搜索
            case R.id.lin_search:
                 setClass(SearchActivity.class);
                 break;
            //消息
            case R.id.rel_news:
                 setClass(MainNewsActivity.class);
                 break;

        }
    }


    /**
     * EventBus注解
     */
    @Subscribe
    public void onEvent(EventBusType eventBusType){
        switch (eventBusType.getStatus()){
            //获取到新品数据
            case EventStatus.MAIN_XP_SUCCESS:
                  xpList= (List<MainXP.DataBean>) eventBusType.getObject();
                  newsRecommendedAdapter=new NewsRecommendedAdapter(this,xpList);
                  listXP.setAdapter(newsRecommendedAdapter);
                  listXP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        MainXP.DataBean dataBean=xpList.get(position);
                        Intent intent=new Intent();
                        if(dataBean.getType()==1){
                            intent.setClass(MainActivity.this, JCDetailsActivity.class);
                        }else{
                            intent.setClass(MainActivity.this, PeiJianDetailsActivity.class);
                        }
                        intent.putExtra("spuid",dataBean.getSpuid());
                        startActivity(intent);
                    }
                });
                  break;
            //获取人气推荐
            case EventStatus.MAIN_RQ_SUCCESS:
                  rqList= (List<MainRQ.DataBean>) eventBusType.getObject();
                  sentimentRecommendedAdapter=new SentimentRecommendedAdapter(this,rqList);
                  rqListView.setAdapter(sentimentRecommendedAdapter);
                  break;
            //获取热门
            case EventStatus.MAIN_HOT_SUCCESS:
                  List<MainHot.DataBean> list= (List<MainHot.DataBean>) eventBusType.getObject();
                  hotList.addAll(list);
                  hotPartsAdapter=new HotPartsAdapter(this,hotList);
                  rmGird.setAdapter(hotPartsAdapter);
                  rmGird.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                          MainHot.DataBean dataBean=hotList.get(position);
                          Intent intent=new Intent();
                          if(dataBean.getType()==1){
                              intent.setClass(MainActivity.this, JCDetailsActivity.class);
                          }else{
                              intent.setClass(MainActivity.this, PeiJianDetailsActivity.class);
                          }
                          intent.putExtra("spuid",dataBean.getSpuid());
                          startActivity(intent);
                      }
                  });
                  break;
            //首页有未读的消息
            case EventStatus.MAIN_NEWS_SHOW:
                  findViewById(R.id.img_news).setVisibility(View.VISIBLE);
                  break;
        }
    }


    /**
     * 监听scrollview下滑底部
     */
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                int scrollY = v.getScrollY();
                int height = v.getHeight();
                int scrollViewMeasuredHeight = scrollView.getChildAt(0).getMeasuredHeight();
                if ((scrollY + height) == scrollViewMeasuredHeight) {
                    //获取首页热门
                    mainPersenter.mainHot();
                }
                break;

        }
        return false;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
