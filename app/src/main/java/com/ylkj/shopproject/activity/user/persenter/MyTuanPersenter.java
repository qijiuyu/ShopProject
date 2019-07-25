package com.ylkj.shopproject.activity.user.persenter;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.zxdc.utils.library.bean.MyTuanDetails;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.TimerUtil;
import com.zxdc.utils.library.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyTuanPersenter {

    private Activity activity;
    public MyTuanPersenter(Activity activity){
        this.activity=activity;
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                //查询详情回执
                case HandlerConstant.MY_TUAN_DETAILS_SUCCESS:
                      final MyTuanDetails myTuanDetails= (MyTuanDetails) msg.obj;
                      if(null==myTuanDetails){
                          break;
                      }
                      if(myTuanDetails.isSussess()){
                          EventBus.getDefault().post(new EventBusType(EventStatus.MY_TUAN_DETAILS,myTuanDetails.getData()));
                      }else{
                          ToastUtil.showLong(myTuanDetails.getDesc());
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
     * 计算团剩余时间
     */
    private int time;
    private TimerUtil timerUtil;
    public void showMyTime(String strTime){
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = format.parse(strTime);
            Long nowTime=System.currentTimeMillis()/1000;
            Long endTime=date.getTime()/1000;
            time=((Long)(endTime-nowTime)).intValue();

            //开始倒计时
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
        final int day=time/86400;
        if(day>0){
            time=time-(day*86400);
        }
        final int hoursInt = time / 3600;
        final int minutesInt = (time - hoursInt * 3600) / 60;
        final int secondsInt = time - hoursInt * 3600 - minutesInt * 60;
        handler.post(new Runnable() {
            public void run() {
                String message=null;
                if(hoursInt==0){
                    message=String.format("%02d", minutesInt) + ":" + String.format("%02d", secondsInt);
                } else{
                    message=day+"天:"+String.format("%02d", hoursInt) + ":" + String.format("%02d", minutesInt) + ":" + String.format("%02d", secondsInt);
                }
                EventBus.getDefault().post(new EventBusType(EventStatus.TUAN_DETAILS_TIME,message));
            }
        });
        if(time==0){
            timerUtil.stop();
        }

    }


    /**
     * 团购详情
     */
    public void tuanDetails(String ugnum){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.tuanDetails(ugnum,handler);
    }


    public void finish(){
        if(null!=timerUtil){
            timerUtil.stop();
        }
        handler.removeCallbacksAndMessages(null);
    }
}
