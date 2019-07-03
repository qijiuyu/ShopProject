package com.ylkj.shopproject.activity.main.persenter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.zxdc.utils.library.util.DialogUtil;

public class PeiJianListPersenter {

    private Activity activity;
    private PopupWindow popupWindow;
    public PeiJianListPersenter(Activity activity){
        this.activity=activity;
    }


    /**
     * 展示默认选择的dialog
     */
    public void showTypeDialog(View rel){
        if(null!=popupWindow){
            return;
        }
        final View view= LayoutInflater.from(activity).inflate(R.layout.dialog_select_pj_type,null);
        final TextView tvMoRen=view.findViewById(R.id.tv_moren);
        final TextView tvXinPin=view.findViewById(R.id.tv_xinpin);
        popupWindow= DialogUtil.showBottom(view,rel);
        //选择默认
        view.findViewById(R.id.rel_moren).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tvMoRen.setTextColor(activity.getResources().getColor(R.color.color_37C7B5));
                tvXinPin.setTextColor(activity.getResources().getColor(R.color.color_666666));
                view.findViewById(R.id.img_moren).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_xinpin).setVisibility(View.GONE);
                popupWindow.dismiss();
                popupWindow=null;
            }
        });
        //选择新品
        view.findViewById(R.id.rel_xinpin).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tvMoRen.setTextColor(activity.getResources().getColor(R.color.color_666666));
                tvXinPin.setTextColor(activity.getResources().getColor(R.color.color_37C7B5));
                view.findViewById(R.id.img_moren).setVisibility(View.GONE);
                view.findViewById(R.id.img_xinpin).setVisibility(View.VISIBLE);
                popupWindow.dismiss();
                popupWindow=null;
            }
        });
        view.findViewById(R.id.lin).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popupWindow.dismiss();
                popupWindow=null;
            }
        });
    }
}
