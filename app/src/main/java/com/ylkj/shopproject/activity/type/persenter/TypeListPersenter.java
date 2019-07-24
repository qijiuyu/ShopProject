package com.ylkj.shopproject.activity.type.persenter;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.zxdc.utils.library.bean.PJGoodList;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

public class TypeListPersenter {

    private Activity activity;

    public TypeListPersenter(Activity activity){
        this.activity=activity;
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            PJGoodList pjGoodList;
            switch (msg.what){
                //查询数据回执
                case HandlerConstant.GET_PJ_GOODS_LIST_SUCCESS1:
                    pjGoodList= (PJGoodList) msg.obj;
                    EventBus.getDefault().post(new EventBusType(EventStatus.GET_PJ_LIST1,pjGoodList));
                    break;
                //上拉加载更多
                case HandlerConstant.GET_PJ_GOODS_LIST_SUCCESS2:
                    pjGoodList= (PJGoodList) msg.obj;
                    EventBus.getDefault().post(new EventBusType(EventStatus.GET_PJ_LIST2,pjGoodList));
                    break;
                case HandlerConstant.REQUST_ERROR:
                    ToastUtil.showLong(activity.getString(R.string.net_error));
                    break;
            }
            return false;
        }
    });


    //获取机床商品列表
    public void getGoodList(int index,int typeId,int page){
        HttpMethod.getPJGoodList("1",String.valueOf(typeId),null,page,index,handler);
    }
}
