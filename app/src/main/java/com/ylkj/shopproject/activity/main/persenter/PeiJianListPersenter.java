package com.ylkj.shopproject.activity.main.persenter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.zxdc.utils.library.util.DialogUtil;

import org.greenrobot.eventbus.EventBus;

public class PeiJianListPersenter {

    private Activity activity;
    private PopupWindow popupWindow;
    private TextView tvMoRen,tvXinPin;
    private ImageView img_moren,img_xinpin;
    public PeiJianListPersenter(Activity activity){
        this.activity=activity;
    }


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
}
