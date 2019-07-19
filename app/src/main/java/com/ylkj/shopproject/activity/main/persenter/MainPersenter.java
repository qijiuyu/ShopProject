package com.ylkj.shopproject.activity.main.persenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.type.JCDetailsActivity;
import com.ylkj.shopproject.activity.webview.WebViewActivity;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import com.zxdc.utils.library.base.MainXP;
import com.zxdc.utils.library.bean.Abvert;
import com.zxdc.utils.library.bean.IsNews;
import com.zxdc.utils.library.bean.MainHot;
import com.zxdc.utils.library.bean.MainJX;
import com.zxdc.utils.library.bean.MainRQ;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.ToastUtil;
import org.greenrobot.eventbus.EventBus;
import java.util.ArrayList;
import java.util.List;
public class MainPersenter {

    private Activity activity;
    private Banner abBanner,jxBanner;
    //顶部轮播图
    private List<Abvert.DataBean> abList=new ArrayList<>();
    //精选列表集合
    private List<MainJX.DataBean> jxList=new ArrayList<>();
    //页数
    private int page;

    public MainPersenter(Activity activity){
        this.activity=activity;
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                //获取顶部轮播图
                case HandlerConstant.GET_ABVERT_SUCCESS:
                      final Abvert abvert= (Abvert) msg.obj;
                      if(null==abvert){
                          break;
                      }
                      if(abvert.isSussess()){
                          abList=abvert.getData();
                          //设置顶部的banner轮播图
                          setBanner(abBanner,0);
                      }
                      break;
                //获取精选专题回执
                case HandlerConstant.GET_MAIN_JX_SUCCESS:
                      final MainJX mainJX= (MainJX) msg.obj;
                      if(null==mainJX){
                          break;
                      }
                      if(mainJX.isSussess()){
                          jxList=mainJX.getData();
                          //设置精选专题的banner轮播图
                          setBanner(jxBanner,1);
                      }else{
                          ToastUtil.showLong(mainJX.getDesc());
                      }
                      break;
                //获得新品专题回执
                case HandlerConstant.GET_MAIN_XP_SUCCESS:
                      final MainXP mainXP= (MainXP) msg.obj;
                      if(null==mainXP){
                          break;
                      }
                      if(mainXP.isSussess()){
                          EventBus.getDefault().post(new EventBusType(EventStatus.MAIN_XP_SUCCESS,mainXP.getData()));
                      }
                      break;
                //获取首页人气推荐
                case HandlerConstant.GET_MAIN_RQ_SUCCESS:
                      final MainRQ mainRQ= (MainRQ) msg.obj;
                      if(null==mainRQ){
                          break;
                      }
                      if(mainRQ.isSussess()){
                          EventBus.getDefault().post(new EventBusType(EventStatus.MAIN_RQ_SUCCESS,mainRQ.getData()));
                      }
                      break;
                //获取首页热门
                case HandlerConstant.GET_MAIN_HOT_SUCCESS:
                      final MainHot mainHot= (MainHot) msg.obj;
                      if(null==mainHot){
                          break;
                      }
                      if(mainHot.isSussess()){
                          if(mainHot.getData().size()==0){
                              --page;
                          }
                          EventBus.getDefault().post(new EventBusType(EventStatus.MAIN_HOT_SUCCESS,mainHot.getData()));
                      }
                      break;
                //消息列表入口红点
                case HandlerConstant.IS_NEWS_SUCCESS:
                      final IsNews isNews= (IsNews) msg.obj;
                      if(null==isNews){
                          break;
                      }
                      if(isNews.isSussess() && isNews.getData()==1){
                          EventBus.getDefault().post(new EventBusType(EventStatus.MAIN_NEWS_SHOW));
                      }
                      break;
                case HandlerConstant.REQUST_ERROR:
                    ToastUtil.showLong(activity.getString(R.string.net_error));
                    break;
            }
            return false;
        }
    });


    /**
     * 设置顶部广告/精选专题的banner轮播图
     */
    private void setBanner(Banner banner,int type){
        //设置样式，里面有很多种样式可以自己都看看效果
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置轮播的动画效果,里面有很多种特效,可以都看看效果。
        banner.setBannerAnimation(Transformer.ZoomOutSlide);
        //设置图片加载器，图片加载器在下方
        if(type==0){
            banner.setImageLoader(new ABImageLoader());
            //设置图片集合
            banner.setImages(abList);
        }else{
            banner.setImageLoader(new JXImageLoader());
            //设置图片集合
            banner.setImages(jxList);
        }
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是true
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，居中显示
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }


    public class ABImageLoader extends ImageLoader {
        public void displayImage(Context context, Object path, ImageView imageView) {
            Abvert.DataBean dataBean= (Abvert.DataBean) path;
            Glide.with(context).load(dataBean.getImgurl()).into(imageView);
            //图片点击跳转
            imageView.setTag(dataBean);
            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Abvert.DataBean dataBean= (Abvert.DataBean) v.getTag();
                    Intent intent=new Intent();
                    switch (dataBean.getJumptype()){
                        //跳转外链
                        case 1:
                             intent.setClass(activity, WebViewActivity.class);
                             intent.putExtra("url",dataBean.getUrl());
                             break;
                        //跳转机床商品
                        case 2:
                             intent.setClass(activity,JCDetailsActivity.class);
                             intent.putExtra("spuid",dataBean.getSpuid());
                             break;
                    }
                    activity.startActivity(intent);

                }
            });
        }
    }

    public class JXImageLoader extends ImageLoader {
        public void displayImage(Context context, Object path, ImageView imageView) {
            MainJX.DataBean dataBean= (MainJX.DataBean) path;
            Glide.with(context).load(dataBean.getImgurl()).into(imageView);
        }
    }

    /**
     * 查询顶部广告轮播图
     */
    public void getAbvert(Banner abBanner){
        this.abBanner=abBanner;
        HttpMethod.getAbvert("1",handler);
    }

    /**
     * 获取精选专题接口
     */
    public void mainJX(Banner jxBanner){
        this.jxBanner=jxBanner;
        HttpMethod.mainJX(handler);
    }


    /**
     * 获取新品首发专题接口
     */
    public void mainXP(){
        HttpMethod.mainXP(handler);
    }

    /**
     * 获取首页人气推荐
     */
    public void mainRQ(){
        HttpMethod.mainRQ(handler);
    }

    /**
     * 获取首页热门
     */
    public void mainHot(){
        HttpMethod.mainHot(++page,handler);
    }

    /**
     * 消息列表入口红点接口
     */
    public void isNews(){
        HttpMethod.isNews("1",handler);
    }
}
