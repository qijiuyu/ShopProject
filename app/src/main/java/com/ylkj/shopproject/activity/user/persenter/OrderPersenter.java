package com.ylkj.shopproject.activity.user.persenter;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.MyOrder;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

public class OrderPersenter {

    private Activity activity;
    private MyOrder.DataBean dataBean;

    public OrderPersenter(Activity activity){
        this.activity=activity;
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                //删除订单回执
                case HandlerConstant.DELETE_ORDER_SUCCESS:
                //取消订单回执
                case HandlerConstant.CANCLE_ORDER_SUCCESS:
                 //确认收货回执
                case HandlerConstant.CONFIRM_GOODS_SUCCESS:
                    final BaseBean baseBean= (BaseBean) msg.obj;
                    if(null==baseBean){
                        break;
                    }
                    if(baseBean.isSussess()){
                        switch (msg.what){
                            //删除订单回执
                            case HandlerConstant.DELETE_ORDER_SUCCESS:
                                EventBus.getDefault().post(new EventBusType(EventStatus.CANCLE_ORDER_SUCCESS,dataBean));
                                break;
                            //取消订单回执
                            case HandlerConstant.CANCLE_ORDER_SUCCESS:
                                EventBus.getDefault().post(new EventBusType(EventStatus.DELETE_ORDER_SUCCESS,dataBean));
                                break;
                            //确认收货回执
                            case HandlerConstant.CONFIRM_GOODS_SUCCESS:
                                EventBus.getDefault().post(new EventBusType(EventStatus.CONFIRM_GOOD_SUCCESS,dataBean));
                                break;
                        }

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
     * 取消订单
     */
    public void cancleOrder(MyOrder.DataBean dataBean){
        this.dataBean=dataBean;
        DialogUtil.showProgress(activity,"订单取消中");
        HttpMethod.cancleOrder(dataBean.getOid(),handler);
    }

    /**
     * 删除订单
     */
    public void delOrder(MyOrder.DataBean dataBean){
        this.dataBean=dataBean;
        DialogUtil.showProgress(activity,"订单删除中");
        HttpMethod.delOrder(dataBean.getOid(),handler);
    }


    /**
     * 确认收货
     */
    public void confirmGood(MyOrder.DataBean dataBean){
        this.dataBean=dataBean;
        DialogUtil.showProgress(activity,"确认收货中");
        HttpMethod.confirmGoods(dataBean.getOid(),handler);
    }
}
