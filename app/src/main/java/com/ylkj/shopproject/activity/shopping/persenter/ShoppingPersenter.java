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
    public void delete(Shopping shopping){
        int num = 0;
        StringBuffer ids=new StringBuffer();
        for (int i=0;i<shopping.getData().getCartInfos().size();i++){
              if(shopping.getData().getCartInfos().get(i).getIsselect()==1){
                  num++;
                  ids.append(shopping.getData().getCartInfos().get(i).getCartid()+",");
              }
        }
        if(num==0){
            ToastUtil.showLong("请选择要删除的商品！");
            return;
        }
        DialogUtil.showProgress(activity,"删除商品中");
        if(num==1){
            HttpMethod.delCar(ids.substring(0,ids.length()-1).toString(),handler);
        }else{
            HttpMethod.delMoreCar(ids.substring(0,ids.length()-1),handler);
        }
    }

    /**
     * 修改商品数量
     */
    public void changeCount(Shopping.goodList goodList){
        HttpMethod.changeCount(String.valueOf(goodList.getCartid()),goodList.getProcount(),handler);
    }


    /**
     * 修改单个商品选择状态接口
     */
    public void selectCar(int carId,Shopping shopping){
        String sel="1";
        for (int i=0;i<shopping.getData().getCartInfos().size();i++){
              if(carId==shopping.getData().getCartInfos().get(i).getCartid()){
                  if(shopping.getData().getCartInfos().get(i).getIsselect()==1){
                      sel="0";
                  }
                  break;
              }
        }
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.selectCar(carId,sel,handler);
    }

    /**
     * 修改多个商品选择状态接口
     */
    public void selectCarList(Shopping shopping){
        StringBuffer stringBuffer=new StringBuffer();
        for (int i=0;i<shopping.getData().getCartInfos().size();i++){
              stringBuffer.append(shopping.getData().getCartInfos().get(i).getCartid()+",");
        }
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.selectCarList(stringBuffer.substring(0,stringBuffer.toString().length()-1),"1",handler);
    }
}
