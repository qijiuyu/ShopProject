package com.ylkj.shopproject.activity.main.persenter;

import android.app.Activity;

import com.ylkj.shopproject.util.MyImgLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class JCDetailsPersenter {

    private Activity activity;
    public List<String> imgList=new ArrayList<>();

    public JCDetailsPersenter(Activity activity){
        this.activity=activity;
    }


    public void setBanner(Banner banner){
        imgList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        imgList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        imgList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
        imgList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");
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
}
