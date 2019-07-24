package com.ylkj.shopproject.activity.main.persenter;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.zxdc.utils.library.bean.PJGoodList;
import com.zxdc.utils.library.bean.PJType;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

public class PeiJianListPersenter {

    private Activity activity;
    private PopupWindow popupWindow;
    private TextView tvMoRen,tvXinPin;
    private ImageView img_moren,img_xinpin;
    public PeiJianListPersenter(Activity activity){
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


    /**
     * 展示默认选择的dialog
     */
    public void showTypeDialog(View rel, int type){
        if(null!=popupWindow && popupWindow.isShowing()){
            return;
        }
        final View view= LayoutInflater.from(activity).inflate(R.layout.dialog_select_pj_type,null);
        popupWindow= DialogUtil.showBottom(view,rel);
        tvMoRen=view.findViewById(R.id.tv_moren);
        tvXinPin=view.findViewById(R.id.tv_xinpin);
        img_moren=view.findViewById(R.id.img_moren);
        img_xinpin=view.findViewById(R.id.img_xinpin);
        if(type==1){
            setSelect(type);
        }else{
            setSelect(type);
        }
        //选择默认
        view.findViewById(R.id.rel_moren).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setSelect(1);
                popupWindow.dismiss();
            }
        });
        //选择新品
        view.findViewById(R.id.rel_xinpin).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setSelect(2);
                popupWindow.dismiss();
            }
        });
        view.findViewById(R.id.lin).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }


    private void setSelect(int type){
        EventBus.getDefault().post(new EventBusType(EventStatus.PJSC_SELECT_TYPE,type));
        if(type==1){
            tvMoRen.setTextColor(activity.getResources().getColor(R.color.color_37C7B5));
            tvXinPin.setTextColor(activity.getResources().getColor(R.color.color_666666));
            img_moren.setVisibility(View.VISIBLE);
            img_xinpin.setVisibility(View.GONE);
        }else{
            tvMoRen.setTextColor(activity.getResources().getColor(R.color.color_666666));
            tvXinPin.setTextColor(activity.getResources().getColor(R.color.color_37C7B5));
            img_moren.setVisibility(View.GONE);
            img_xinpin.setVisibility(View.VISIBLE);
        }
    }


    //获取配件商品列表
    public void getPJGoodList(int index,int typeId,int page,String searchkey){
        HttpMethod.getPJGoodList("2",String.valueOf(typeId),searchkey,page,index,handler);
    }
}
