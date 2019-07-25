package com.ylkj.shopproject.activity.main.pjsc;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.main.persenter.PeiJianDetailsPersenter;
import com.ylkj.shopproject.adapter.main.PeiJianDetailsTypeAdapter;
import com.youth.banner.Banner;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.TimerUtil;
import com.zxdc.utils.library.view.MeasureListView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 拼团商品详情
 */
public class PinTuanDetailsActivity extends BaseActivity implements View.OnClickListener{

    private Banner banner;
    private TextView tvName,tvDes,tvNewMoney,tvYfMoney,tvNum,tvDay,tvHour,tvMinutes,tvSecounds;
    private MeasureListView listView;
    private PeiJianDetailsTypeAdapter peiJianDetailsTypeAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pintuan_details);
        initView();
        showTime();
    }

    /**
     * 初始化
     */
    private void initView(){
        banner=findViewById(R.id.banner);
        tvName=findViewById(R.id.tv_name);
        tvDes=findViewById(R.id.tv_des);
        tvNewMoney=findViewById(R.id.tv_new_money);
        tvYfMoney=findViewById(R.id.tv_yf_money);
        listView=findViewById(R.id.list_type);
        tvNum=findViewById(R.id.tv_num);
        tvDay=findViewById(R.id.tv_day);
        tvHour=findViewById(R.id.tv_hour);
        tvMinutes=findViewById(R.id.tv_minutes);
        tvSecounds=findViewById(R.id.tv_secounds);
        findViewById(R.id.tv_yhq).setOnClickListener(this);
        findViewById(R.id.img_remove).setOnClickListener(this);
        findViewById(R.id.img_add).setOnClickListener(this);
        findViewById(R.id.img_kf).setOnClickListener(this);
        findViewById(R.id.img_sc).setOnClickListener(this);
        findViewById(R.id.img_shopping).setOnClickListener(this);
        findViewById(R.id.tv_buy).setOnClickListener(this);
        findViewById(R.id.tv_add_shopping).setOnClickListener(this);


        peiJianDetailsTypeAdapter=new PeiJianDetailsTypeAdapter(this,null);
        listView.setAdapter(peiJianDetailsTypeAdapter);
    }


    private Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            return false;
        }
    });


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //领取优惠券
            case R.id.tv_yhq:
                 break;
            //减数量
            case R.id.img_remove:
                 break;
            //加数量
            case R.id.img_add:
                 break;
            //客服
            case R.id.img_kf:
                 break;
            //收藏
            case R.id.img_sc:
                 break;
            //加入购物车
            case R.id.tv_add_shopping:
            case R.id.img_shopping:
                 break;
            //立即购买
            case R.id.tv_buy:
                 break;
        }
    }


    private int time;
    private TimerUtil timerUtil;
    private void showTime(){
        try {
            String startTime="2018-09-09 16:39:00";
            String endTime="2018-09-29 16:39:00";
            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = format.parse(startTime);
            Date date2 = format.parse(endTime);
            //日期转时间戳（毫秒）
            Long time1=date.getTime()/1000;
            Long time2=date2.getTime()/1000;
            Long along=time2-time1;
            time=along.intValue();
            LogUtils.e(time1+"____________"+time2+"________"+time);

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
                if(hoursInt==0){
                    LogUtils.e(String.format("%02d", minutesInt) + ":" + String.format("%02d", secondsInt));
                } else{
                    LogUtils.e(day+"天:"+String.format("%02d", hoursInt) + ":" + String.format("%02d", minutesInt) + ":" + String.format("%02d", secondsInt));
                }
            }
        });

    }

}
