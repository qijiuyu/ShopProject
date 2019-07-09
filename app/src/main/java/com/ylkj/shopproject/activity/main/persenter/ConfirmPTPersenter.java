package com.ylkj.shopproject.activity.main.persenter;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.zxdc.utils.library.util.DialogUtil;

import java.util.ArrayList;
import java.util.List;

public class ConfirmPTPersenter implements View.OnClickListener{

    private Activity activity;
    public List<String> imgList = new ArrayList<>();
    private RelativeLayout relPT,relZZ;
    private TextView tvPT,tvZZ,tvGR,tvDW,tvGoodDetails,tvGoodType,tvCompany,tvCode,tvAddress,tvMobile,tvBank,tvBankCode;
    private EditText etTT;
    private PopupWindow popupWindow;
    public ConfirmPTPersenter(Activity activity) {
        this.activity = activity;
    }

    /**
     * 选择发票
     */
    public void showFPDialog(){
        View view= LayoutInflater.from(activity).inflate(R.layout.dialog_fp,null);
        popupWindow=DialogUtil.showPopWindow(activity,view);
        relPT=view.findViewById(R.id.rel_pt);
        relZZ=view.findViewById(R.id.rel_zz);
        tvPT=view.findViewById(R.id.tv_pt_fp);
        tvZZ=view.findViewById(R.id.tv_zz_fp);
        tvGR=view.findViewById(R.id.tv_gr);
        tvDW=view.findViewById(R.id.tv_dw);
        etTT=view.findViewById(R.id.et_tt);
        tvGoodDetails=view.findViewById(R.id.tv_goods_details);
        tvGoodType=view.findViewById(R.id.tv_goods_type);
        tvCompany=view.findViewById(R.id.tv_company);
        tvCode=view.findViewById(R.id.tv_code);
        tvAddress=view.findViewById(R.id.tv_address);
        tvMobile=view.findViewById(R.id.tv_mobile);
        tvBank=view.findViewById(R.id.tv_bank);
        tvBankCode=view.findViewById(R.id.tv_bank_code);
        tvGR.setOnClickListener(this);
        tvDW.setOnClickListener(this);
        tvGoodDetails.setOnClickListener(this);
        tvGoodType.setOnClickListener(this);
        tvPT.setOnClickListener(this);
        tvZZ.setOnClickListener(this);
        view.findViewById(R.id.img_close).setOnClickListener(this);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //展示普通发票
            case R.id.tv_pt_fp:
                 relPT.setVisibility(View.VISIBLE);
                 relZZ.setVisibility(View.GONE);
                 tvPT.setBackground(activity.getResources().getDrawable(R.drawable.bg_fp_type_yes));
                 tvZZ.setBackground(activity.getResources().getDrawable(R.drawable.bg_fp_type_no));
                 tvPT.setTextColor(activity.getResources().getColor(android.R.color.white));
                 tvZZ.setTextColor(activity.getResources().getColor(R.color.color_666666));
                 break;
            //展示增值税发票
            case R.id.tv_zz_fp:
                 relPT.setVisibility(View.GONE);
                 relZZ.setVisibility(View.VISIBLE);
                 tvPT.setBackground(activity.getResources().getDrawable(R.drawable.bg_fp_type_no));
                 tvZZ.setBackground(activity.getResources().getDrawable(R.drawable.bg_fp_type_yes));
                 tvZZ.setTextColor(activity.getResources().getColor(android.R.color.white));
                 tvPT.setTextColor(activity.getResources().getColor(R.color.color_666666));
                 break;
            //网络类型：个人
            case R.id.tv_gr:
                 tvGR.setBackground(activity.getResources().getDrawable(R.drawable.bg_fp_type_yes));
                 tvDW.setBackground(activity.getResources().getDrawable(R.drawable.bg_fp_type_no));
                 tvGR.setTextColor(activity.getResources().getColor(android.R.color.white));
                 tvDW.setTextColor(activity.getResources().getColor(R.color.color_666666));
                 break;
            //网络类型：单位
            case R.id.tv_dw:
                 tvGR.setBackground(activity.getResources().getDrawable(R.drawable.bg_fp_type_no));
                 tvDW.setBackground(activity.getResources().getDrawable(R.drawable.bg_fp_type_yes));
                 tvDW.setTextColor(activity.getResources().getColor(android.R.color.white));
                 tvGR.setTextColor(activity.getResources().getColor(R.color.color_666666));
                 break;
            //发票内容：商品明细
            case R.id.tv_goods_details:
                 tvGoodDetails.setBackground(activity.getResources().getDrawable(R.drawable.bg_fp_type_yes));
                 tvGoodType.setBackground(activity.getResources().getDrawable(R.drawable.bg_fp_type_no));
                 tvGoodDetails.setTextColor(activity.getResources().getColor(android.R.color.white));
                 tvGoodType.setTextColor(activity.getResources().getColor(R.color.color_666666));
                 break;
            //发票内容：商品分类
            case R.id.tv_goods_type:
                 tvGoodDetails.setBackground(activity.getResources().getDrawable(R.drawable.bg_fp_type_no));
                 tvGoodType.setBackground(activity.getResources().getDrawable(R.drawable.bg_fp_type_yes));
                 tvGoodType.setTextColor(activity.getResources().getColor(android.R.color.white));
                 tvGoodDetails.setTextColor(activity.getResources().getColor(R.color.color_666666));
                 break;
            case R.id.img_close:
                 popupWindow.dismiss();
                 break;
        }
    }
}
