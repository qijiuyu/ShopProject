package com.ylkj.shopproject.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.main.fault.AddFaultActivity;
import com.ylkj.shopproject.activity.main.news.MainNewsActivity;
import com.ylkj.shopproject.activity.main.persenter.MainPersenter;
import com.ylkj.shopproject.activity.main.pjsc.PeiJianTypeActivity;
import com.ylkj.shopproject.activity.main.search.SearchActivity;
import com.ylkj.shopproject.activity.main.zzfu.ZzfuTypeActivity;
import com.ylkj.shopproject.activity.user.login.LoginActivity;
import com.ylkj.shopproject.activity.user.login.RegisterActivity;
import com.ylkj.shopproject.adapter.main.HotPartsAdapter;
import com.ylkj.shopproject.adapter.main.NewsRecommendedAdapter;
import com.ylkj.shopproject.adapter.main.SentimentRecommendedAdapter;
import com.ylkj.shopproject.util.GetLocation;
import com.ylkj.shopproject.util.MyImgLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MeasureListView;
import com.zxdc.utils.library.view.MyGridView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener,View.OnTouchListener{
    private ScrollView scrollView;
    private Banner banner,banner2;
    private MeasureListView rqListView;
    private MainPersenter mainPersenter;
    private MyGridView xpGird,rmGird;
    private List<String> imgList=new ArrayList<>();
    //新品推荐的adapter
    private NewsRecommendedAdapter newsRecommendedAdapter;
    //人气推荐的adapter
    private SentimentRecommendedAdapter sentimentRecommendedAdapter;
    //热门配件
    private HotPartsAdapter hotPartsAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPersenter=new MainPersenter(this);
        initView();
        //开始定位
        GetLocation.getInstance().setLocation(this);
        setBanner(banner,imgList);
        setBanner(banner2,imgList);
    }


    /**
     * 初始化控件
     */
    private void initView(){
        scrollView=findViewById(R.id.scrollView);
        banner=findViewById(R.id.banner);
        banner2=findViewById(R.id.banner2);
        xpGird=findViewById(R.id.gv_xptj);
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
        imgList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        imgList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        imgList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
        imgList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");

        newsRecommendedAdapter=new NewsRecommendedAdapter(this);
        xpGird.setAdapter(newsRecommendedAdapter);

        sentimentRecommendedAdapter=new SentimentRecommendedAdapter(this);
        rqListView.setAdapter(sentimentRecommendedAdapter);

        hotPartsAdapter=new HotPartsAdapter(this);
        rmGird.setAdapter(hotPartsAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //磨床
            case R.id.lin_mc:
                Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
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
                 setClass(RzzpActivity.class);
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


    private void setBanner(Banner banner,List<String> imgList){
        //设置样式，里面有很多种样式可以自己都看看效果
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置轮播的动画效果,里面有很多种特效,可以都看看效果。
        banner.setBannerAnimation(Transformer.ZoomOutSlide);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyImgLoader());
        //设置图片集合
        banner.setImages(imgList);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是true
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，居中显示
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setOnBannerListener(new OnBannerListener() {
            public void OnBannerClick(int position) {
                ToastUtil.showLong(position+"");
            }
        });
        //banner设置方法全部调用完毕时最后调用
        banner.start();
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
                    ToastUtil.showLong("111");
                }
                break;

        }
        return false;
    }
}
