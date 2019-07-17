package com.ylkj.shopproject.activity.type.persenter;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.ylkj.shopproject.util.MyImgLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.zxdc.utils.library.bean.JCGoodDetails;
import com.zxdc.utils.library.bean.PJGoodDetails;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.http.base.Http;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.SPUtil;
import com.zxdc.utils.library.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class JCDetailsPersenter {

    private Activity activity;
    public List<JCGoodDetails.imgList> imgList=new ArrayList<>();

    public JCDetailsPersenter(Activity activity){
        this.activity=activity;
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                //获取机床商品详情回执
                case HandlerConstant.GET_JC_DETAILS_SUCCESS:
                      final String message= (String) msg.obj;
                      if(TextUtils.isEmpty(message)){
                          break;
                      }
                      final JCGoodDetails jcGoodDetails= SPUtil.gson.fromJson(message,JCGoodDetails.class);
                      if(null==jcGoodDetails){
                          break;
                      }
                      if(jcGoodDetails.isSussess()){
                          EventBus.getDefault().post(new EventBusType(EventStatus.GET_JC_DETAILS,jcGoodDetails));
                      }else{
                          ToastUtil.showLong(jcGoodDetails.getDesc());
                      }
                      break;
                case HandlerConstant.REQUST_ERROR:
                    ToastUtil.showLong(activity.getString(R.string.net_error));
                    break;
            }
            return false;
        }
    });


    public void setBanner(Banner banner,List<JCGoodDetails.imgList> imgList){
        this.imgList=imgList;
        //设置样式，里面有很多种样式可以自己都看看效果
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置轮播的动画效果,里面有很多种特效,可以都看看效果。
        banner.setBannerAnimation(Transformer.ZoomOutSlide);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new ImgLoader());
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


    /**
     * 图片加载监听
     */
    public class ImgLoader extends ImageLoader {
        public void displayImage(Context context, Object path, ImageView imageView) {
            JCGoodDetails.imgList imgList= (JCGoodDetails.imgList) path;
            Glide.with(context).load(imgList.getUrl()).into(imageView);
        }
    }


    /**
     * 查询机床商品详情
     */
    public void getJCDetails(int spuid){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getJCDetails(spuid,handler);
    }


    /**
     * 收藏商品
     */
    public void isCollec(JCGoodDetails jcGoodDetails){
        if(null==jcGoodDetails){
            return;
        }
        final JCGoodDetails.DataBean dataBean=jcGoodDetails.getData();
        if(null==dataBean){
            return;
        }
        if(dataBean.getIscollect()==0){
            DialogUtil.showProgress(activity,"收藏中");
            HttpMethod.collection(dataBean.getSpuid(),handler);
        }else{
            ToastUtil.showLong("该商品已收藏过！");
        }
    }
}
