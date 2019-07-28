package com.ylkj.shopproject.activity.main.persenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.main.pjsc.SelectYHQActivity;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.PJGoodDetails;
import com.zxdc.utils.library.bean.json.YhqJson;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.SPUtil;
import com.zxdc.utils.library.util.TimerUtil;
import com.zxdc.utils.library.util.ToastUtil;
import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PeiJianDetailsPersenter {

    private Activity activity;
    private List<PJGoodDetails.imgList> imgList;
    //计时器对象
    private TimerUtil timerUtil;
    //时间格式
    private SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public PeiJianDetailsPersenter(Activity activity){
        this.activity=activity;
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            BaseBean baseBean=null;
            switch (msg.what){
                //获取配件商品详情接口
                case HandlerConstant.GET_PJ_DETAILS_SUCCESS:
                     final PJGoodDetails pjGoodDetails= (PJGoodDetails) msg.obj;
                     if(null==pjGoodDetails){
                         break;
                     }
                     if(pjGoodDetails.isSussess()){
                         EventBus.getDefault().post(new EventBusType(EventStatus.GET_PJ_DETAILS,pjGoodDetails));
                     }else{
                         ToastUtil.showLong(pjGoodDetails.getDesc());
                     }
                     break;
                //收藏商品回执
                case HandlerConstant.COLLECTION_SUCCESS:
                       baseBean= (BaseBean) msg.obj;
                      if(null==baseBean){
                          break;
                      }
                      if(baseBean.isSussess()){
                          EventBus.getDefault().post(new EventBusType(EventStatus.COLLECTION_SUCCESS));
                      }
                      ToastUtil.showLong(baseBean.getDesc());
                      break;
                //添加购物车回执
                case HandlerConstant.ADD_CAR_SUCCESS:
                      baseBean= (BaseBean) msg.obj;
                      if(null==baseBean){
                          break;
                      }
                      ToastUtil.showLong(baseBean.getDesc());
                      break;
                case HandlerConstant.REQUST_ERROR:
                    ToastUtil.showLong(activity.getString(R.string.net_error));
                    break;
            }
            return false;
        }
    });


    /**
     * 设置轮播图
     * @param banner
     */
    public void setBanner(Banner banner,List<PJGoodDetails.imgList> imgList){
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
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    /**
     * 图片加载监听
     */
    public class ImgLoader extends ImageLoader {
        public void displayImage(Context context, Object path, ImageView imageView) {
            final PJGoodDetails.imgList imgList= (PJGoodDetails.imgList) path;
            Glide.with(context).load(imgList.getUrl()).into(imageView);
        }
    }

    /**
     * 获取配件商品详情接口
     */
    public void getPJDetails(int id){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getPJDetails(String.valueOf(id),handler);
    }


    /**
     * 收藏商品
     */
    public void isCollec(PJGoodDetails pjGoodDetails){
        if(null==pjGoodDetails){
            return;
        }
        final PJGoodDetails.goodBean goodBean=pjGoodDetails.getData();
        if(null==goodBean){
            return;
        }
        if(goodBean.getIscollect()==0){
            DialogUtil.showProgress(activity,"收藏中");
            HttpMethod.collection(goodBean.getSpuid(),handler);
        }else{
            ToastUtil.showLong("该商品已收藏过！");
        }
    }


    /**
     * 加入购物车
     */
    public void addCar(String skuid,int num){
        DialogUtil.showProgress(activity,"加入购物车中");
        HttpMethod.addCar(skuid,num,handler);
    }


    /**
     * 组装优惠券json
     */
    public void couponJson(PJGoodDetails.goodBean goodBean,int skuCount,double skuPrice){
        YhqJson yhqJson=new YhqJson();
        List<YhqJson.goodList> list=new ArrayList<>();
        double totalMoney=0;
        if(null!=goodBean){
            //总费用
            totalMoney=skuCount*skuPrice;
            //商品信息
            YhqJson.goodList goodList=new YhqJson.goodList();
            goodList.setProid(goodBean.getSpuid());
            goodList.setPromoney(goodBean.getPrice());
            list.add(goodList);
        }
        yhqJson.setMoney(totalMoney);
        yhqJson.setProlist(list);
        Intent intent=new Intent(activity, SelectYHQActivity.class);
        intent.putExtra("parameter",SPUtil.gson.toJson(yhqJson));
        activity.startActivity(intent);
    }


    /**
     * 判断选中的规格是否是团购
     */
    public boolean isTG(PJGoodDetails.skuBean skuBean){
        if(null==skuBean){
            return false;
        }
        if(null!=timerUtil){
            timerUtil.stop();
        }
        if(skuBean.getIstg()==1){
            return true;
        }
        return false;
    }


    /**
     * 开始倒计时
     */
    private int time;
    public void startCountdown(PJGoodDetails.skuBean skuBean){
        if(null==skuBean){
            return;
        }
        try {
            Date endDate = format.parse(skuBean.getTgdto().getEndtime());
            //日期转时间戳（毫秒）
            Long startTime=System.currentTimeMillis()/1000;
            Long endTime=endDate.getTime()/1000;
            Long along=endTime-startTime;
            time=along.intValue();
            timerUtil=new TimerUtil(0, 1000, new TimerUtil.TimerCallBack() {
                public void onFulfill() {
                    timerTask();
                }
            });
            timerUtil.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 开始计时器
     */
    private void timerTask(){
        time--;
        if(time<0){
            return;
        }
        final int day=time/86400;
        if(day>0){
            time=time-(day*86400);
        }
        final int hoursInt = time / 3600;
        final int minutesInt = (time - hoursInt * 3600) / 60;
        final int secondsInt = time - hoursInt * 3600 - minutesInt * 60;
        handler.post(new Runnable() {
            public void run() {
                if(hoursInt==0){
                    LogUtils.e(String.format("%02d", minutesInt) + ":" + String.format("%02d", secondsInt));
                } else{
                    LogUtils.e(day+"天:"+String.format("%02d", hoursInt) + ":" + String.format("%02d", minutesInt) + ":" + String.format("%02d", secondsInt));
                }
            }
        });
        if(time==0){
            timerUtil.stop();
        }

    }
}
