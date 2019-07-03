package com.ylkj.shopproject.activity.type;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.showimg.ShowImgActivity;
import com.ylkj.shopproject.adapter.type.JCDetailsTypeAdapter;
import com.ylkj.shopproject.adapter.type.SelectColorAdapter;
import com.ylkj.shopproject.util.MyImgLoader;
import com.ylkj.shopproject.view.HorizontalListView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.SPUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MeasureListView;
import com.zxdc.utils.library.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * 机床详情页
 */
public class JCDetailsActivity extends BaseActivity implements View.OnClickListener{

    private Banner banner;
    private TextView tvDes,tvApp;
    private HorizontalListView listColor;
    private MyGridView gridType;
    private MeasureListView listName;
    private List<String> imgList=new ArrayList<>();
    //选择颜色的adapter
    private SelectColorAdapter selectColorAdapter;
    //类型描述的adapter
    private JCDetailsTypeAdapter jcDetailsTypeAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jc_details);
        initView();
        setBanner();
    }

    /**
     * 初始化
     */
    @SuppressLint("WrongViewCast")
    private void initView(){
        banner=findViewById(R.id.banner);
        tvDes=findViewById(R.id.tv_des);
        tvApp=findViewById(R.id.tv_app);
        listColor=findViewById(R.id.list_color);
        gridType=findViewById(R.id.gv_type);
        listName=findViewById(R.id.list_name);
        tvDes.setOnClickListener(this);
        tvApp.setOnClickListener(this);
        imgList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        imgList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        imgList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
        imgList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");

        selectColorAdapter=new SelectColorAdapter(this,null);
        listColor.setAdapter(selectColorAdapter);

        jcDetailsTypeAdapter=new JCDetailsTypeAdapter(this,null);
        gridType.setAdapter(jcDetailsTypeAdapter);
    }


    private void setBanner(){
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
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.setOnBannerListener(new OnBannerListener() {
            public void OnBannerClick(int position) {
                ToastUtil.showLong(position+"");
            }
        });
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //功能介绍
            case R.id.tv_des:
                 break;
            //平台消息
            case R.id.tv_app:
                 Intent intent=new Intent(this, ShowImgActivity.class);
                 intent.putExtra("imgs", SPUtil.gson.toJson(imgList));
                 startActivity(intent);
                 break;
        }
    }
}
