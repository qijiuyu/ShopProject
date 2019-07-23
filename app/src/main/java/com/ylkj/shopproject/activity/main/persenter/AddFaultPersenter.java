package com.ylkj.shopproject.activity.main.persenter;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.CustomListener;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.selectphoto.bean.Bimp;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.OrderAddr;
import com.zxdc.utils.library.bean.UploadImg;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.BitMapUtil;
import com.zxdc.utils.library.util.DateUtils;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.time.TimePickerView;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddFaultPersenter {

    private Activity activity;
    private TimePickerView timePickerView;
    private String addressId,jxName,time,content;

    public AddFaultPersenter(Activity activity){
        this.activity=activity;
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                //获取地址列表回执
                case HandlerConstant.GET_ORDER_ADDR_SUCCESS:
                    OrderAddr orderAddr= (OrderAddr) msg.obj;
                    if(null==orderAddr){
                        break;
                    }
                    if(orderAddr.isSussess()){
                        EventBus.getDefault().post(new EventBusType(EventStatus.GET_MY_ADDRESS,orderAddr.getData()));
                    }
                    break;
                //图片上传成功
                case HandlerConstant.UPLOAD_IMG_SUCCESS:
                    final UploadImg uploadImg= (UploadImg) msg.obj;
                    if(uploadImg.isSussess()){
                        submitData(uploadImg);
                    }else{
                        ToastUtil.showLong(uploadImg.getDesc());
                    }
                    break;
                 //报修回执
                case HandlerConstant.ADD_FAULT_SUCCESS:
                      final BaseBean baseBean= (BaseBean) msg.obj;
                      if(null==baseBean){
                          break;
                      }
                      if(baseBean.isSussess()){
                          activity.finish();
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


    /**
     * 获取收货地址列表
     */
    public void getOrderAddr(){
        HttpMethod.getOrderAddr(handler);
    }

    /**
     * 上传名牌，机床图片
     */
    public  void uploadImg(File mpFile,File jcFile){
        final List<File> list=new ArrayList<>();
        list.add(mpFile);
        list.add(jcFile);
        DialogUtil.showProgress(activity,"数据提交中");
        handler.postDelayed(new Runnable() {
            public void run() {
                for (int i = 0; i< Bimp.selectBitmap.size(); i++){
                    final File file=new File(Bimp.selectBitmap.get(i).getImagePath());
                    if(!file.isFile()){
                        return;
                    }
                    final String newPath= BitMapUtil.compressBitMap(file);
                    final File file1=new File(newPath);
                    if(file1.isFile()){
                        list.add(file1);
                    }
                }
                HttpMethod.uploadImg(1,list,handler);
            }
        },100);
    }

    /**
     * 提交数据
     */
    private void submitData(UploadImg uploadImg){
        String otherImg=null;
        if(uploadImg.getData().length>2){
            StringBuffer stringBuffer=new StringBuffer();
            for (int i=2;i<uploadImg.getData().length;i++){
                 stringBuffer.append(uploadImg.getData()[i]+",");
            }
            otherImg=stringBuffer.substring(0,stringBuffer.toString().length()-1);
        }
        DialogUtil.showProgress(activity,"数据提交中");
        HttpMethod.addFault(addressId,jxName,time,content,uploadImg.getData()[0],uploadImg.getData()[1],otherImg,handler);
    }


    public void setContent(String addressId,String jxName,String tiem,String content){
        this.addressId=addressId;
        this.jxName=jxName;
        this.time=tiem;
        this.content=content;
    }
}
