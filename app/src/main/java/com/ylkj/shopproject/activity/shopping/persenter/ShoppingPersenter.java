package com.ylkj.shopproject.activity.shopping.persenter;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.Shopping;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import org.greenrobot.eventbus.EventBus;
import java.util.Map;

public class ShoppingPersenter {

    private Activity activity;

    //要删除的商品id
    private String ids;

    public ShoppingPersenter(Activity activity){
        this.activity=activity;
    }

    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                //查询购物车数据回执
                case HandlerConstant.GET_CAR_LIST_SUCCESS:
                      final Shopping shopping= (Shopping) msg.obj;
                      if(null==shopping){
                          break;
                      }
                      if(shopping.isSussess()){
                          EventBus.getDefault().post(new EventBusType(EventStatus.GET_CAR_LIST,shopping));
                      }else{
                          ToastUtil.showLong(shopping.getDesc());
                      }
                      break;
                //删除商品回执
                case HandlerConstant.DEL_MORE_CAR_SUCCESS:
                      final BaseBean baseBean= (BaseBean) msg.obj;
                      if(null==baseBean){
                          break;
                      }
                      if(baseBean.isSussess()){
                          EventBus.getDefault().post(new EventBusType(EventStatus.DEL_CAR,ids));
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
     * 查询购物车数据
     */
    public void getCarList(){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getCarList(handler);
    }

    /**
     * 删除购物车
     */
    public void delete(Map<Integer,Integer> map){
        StringBuffer stringBuffer=new StringBuffer();
        for (Integer key : map.keySet()) {
             stringBuffer.append(key+",");
        }
        ids=stringBuffer.subSequence(0,stringBuffer.length()-1).toString();
        DialogUtil.showProgress(activity,"删除商品中");
        if(map.size()==1){
            HttpMethod.delCar(ids,handler);
        }else{
            HttpMethod.delMoreCar(ids,handler);
        }
    }

    /**
     * 修改商品数量
     */
    public void changeCount(Shopping.goodList goodList){
        HttpMethod.changeCount(String.valueOf(goodList.getCartid()),goodList.getProcount(),handler);
    }
}
