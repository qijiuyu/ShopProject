package com.ylkj.shopproject.activity.main.persenter;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.CustomListener;
import com.ylkj.shopproject.R;
import com.zxdc.utils.library.util.DateUtils;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.view.time.TimePickerView;
import java.util.Calendar;
import java.util.Date;

public class AddFaultPersenter {

    private Activity activity;
    private TimePickerView timePickerView;

    public AddFaultPersenter(Activity activity){
        this.activity=activity;
    }

    /**
     * 初始化时间选择
     */
    public void initCustomTimePicker(final TextView tvTime) {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2000, 1, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(3000, 12, 31);
        //时间选择器 ，自定义布局
        timePickerView = new TimePickerView.Builder(activity, new TimePickerView.OnTimeSelectListener() {
            public void onTimeSelect(Date date, View v) {//选中事件回调
                tvTime.setText(DateUtils.getDay(date.getTime()));
            }
        }).setDate(selectedDate).setRangDate(startDate, endDate)
           .setLayoutRes(R.layout.pickerview_sign_time, new CustomListener() {
            public void customLayout(View v) {
                final TextView tvSubmit =v.findViewById(R.id.tv_finish);
                TextView ivCancel = v.findViewById(R.id.tv_cancel);
                tvSubmit.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        timePickerView.returnData();
                        timePickerView.dismiss();
                    }
                });
                ivCancel.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        timePickerView.dismiss();
                    }
                });
            }
        })
        .setType(new boolean[]{true, true, true, false, false, false})
        .setLabel("年", "月", "日", "时", "分", "")
        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
        .setDividerColor(activity.getResources().getColor(R.color.color_dbdbdb))
        .setTextColorCenter(activity.getResources().getColor(R.color.color_333333))
        .gravity(Gravity.CENTER)
        .build();
    }

    /**
     * 显示时间弹框
     * @param tvTime
     */
    public void showTime(TextView tvTime){
        timePickerView.show(tvTime);
    }
}
