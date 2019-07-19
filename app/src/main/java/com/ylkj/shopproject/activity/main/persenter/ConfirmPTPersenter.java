package com.ylkj.shopproject.activity.main.persenter;

import android.app.Activity;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.zxdc.utils.library.bean.Address;
import com.zxdc.utils.library.bean.Zpzz;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
public class ConfirmPTPersenter implements View.OnClickListener{

    private Activity activity;
    public List<String> imgList = new ArrayList<>();
    private RelativeLayout relPT,relZZ;
    private TextView tvPT,tvZZ,tvGR,tvDW,tvGoodDetails,tvGoodType,tvCompany,tvCode,tvAddress,tvMobile,tvBank,tvBankCode,tvSPName,tvSPMobile,tvSPAddr;
    private EditText etTT,etSPName,etSPMobile,etSPAddr;
    private PopupWindow popupWindow;
    //发票类型 0不需要发票 1普通发票 2增值税发票
    private int invoiceType=0;
    //发票抬头类型(0:个人 1:单位)
    private int titletype;
    //发票内容(0:商品明细 1:商品类别)
    private int contenttype;
    //增值税法发票对象
    private Zpzz zpzz;
    public ConfirmPTPersenter(Activity activity) {
        this.activity = activity;
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                //获取地址列表回执
                case HandlerConstant.GET_ADDR_LIST_SUCCESS:
                     Address address= (Address) msg.obj;
                     if(null==address){
                         break;
                     }
                     if(address.isSussess()){
                         EventBus.getDefault().post(new EventBusType(EventStatus.GET_MY_ADDRESS,address.getData()));
                     }
                     break;
                //查询增票资质
                case HandlerConstant.GET_ZPZZ_SUCCESS:
                     zpzz= (Zpzz) msg.obj;
                     if(null==zpzz){
                         break;
                     }
                     if(zpzz.isSussess() && null!=zpzz.getData()){
                         if(zpzz.getData().getStatus()!=1){
                             ToastUtil.showLong(zpzz.getDesc());
                             break;
                         }
                         //展示增值税收发票信息
                         showFp(zpzz.getData());
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
        etSPName=view.findViewById(R.id.et_sp_name);
        etSPMobile=view.findViewById(R.id.et_sp_mobile);
        etSPAddr=view.findViewById(R.id.et_sp_addr);
        tvGoodDetails=view.findViewById(R.id.tv_goods_details);
        tvGoodType=view.findViewById(R.id.tv_goods_type);
        tvCompany=view.findViewById(R.id.tv_company);
        tvCode=view.findViewById(R.id.tv_code);
        tvAddress=view.findViewById(R.id.tv_address);
        tvMobile=view.findViewById(R.id.tv_mobile);
        tvBank=view.findViewById(R.id.tv_bank);
        tvBankCode=view.findViewById(R.id.tv_bank_code);
        tvSPName=view.findViewById(R.id.tv_sp_name);
        tvSPMobile=view.findViewById(R.id.tv_sp_mobile);
        tvSPAddr=view.findViewById(R.id.tv_sp_addr);
        tvGR.setOnClickListener(this);
        tvDW.setOnClickListener(this);
        tvGoodDetails.setOnClickListener(this);
        tvGoodType.setOnClickListener(this);
        tvPT.setOnClickListener(this);
        tvZZ.setOnClickListener(this);
        view.findViewById(R.id.tv_submit_pf).setOnClickListener(this);
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
                 //获取增票资质信息
                 getZpzz();
                 relPT.setVisibility(View.GONE);
                 relZZ.setVisibility(View.VISIBLE);
                 tvPT.setBackground(activity.getResources().getDrawable(R.drawable.bg_fp_type_no));
                 tvZZ.setBackground(activity.getResources().getDrawable(R.drawable.bg_fp_type_yes));
                 tvZZ.setTextColor(activity.getResources().getColor(android.R.color.white));
                 tvPT.setTextColor(activity.getResources().getColor(R.color.color_666666));
                 break;
            //网络类型：个人
            case R.id.tv_gr:
                 titletype=0;
                 tvGR.setBackground(activity.getResources().getDrawable(R.drawable.bg_fp_type_yes));
                 tvDW.setBackground(activity.getResources().getDrawable(R.drawable.bg_fp_type_no));
                 tvGR.setTextColor(activity.getResources().getColor(android.R.color.white));
                 tvDW.setTextColor(activity.getResources().getColor(R.color.color_666666));
                 break;
            //网络类型：单位
            case R.id.tv_dw:
                 titletype=1;
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
            //普通发票提交
            case R.id.tv_submit_pf:
                  String tt=etTT.getText().toString().trim();
                  String name=etSPName.getText().toString().trim();
                  String mobile=etSPMobile.getText().toString().trim();
                  String address=etSPAddr.getText().toString().trim();
                 if(TextUtils.isEmpty(tt)){
                     ToastUtil.showLong("请输入发票抬头");
                     return;
                 }
                  if(TextUtils.isEmpty(name)){
                      ToastUtil.showLong("请输入收票人姓名");
                      return;
                  }
                 if(TextUtils.isEmpty(mobile)){
                     ToastUtil.showLong("请输入收票人手机号");
                     return;
                  }
                 if(TextUtils.isEmpty(address)){
                     ToastUtil.showLong("请输入收票地址");
                     return;
                  }
                  invoiceType=1;
                  break;
            //增值税发票提交
            case R.id.tv_submit_zz:
                if(zpzz.isSussess() && null!=zpzz.getData()){
                    if(zpzz.getData().getStatus()!=1){
                        ToastUtil.showLong(zpzz.getDesc());
                        break;
                    }
                    invoiceType=2;
                }
                  break;
            case R.id.img_close:
                 popupWindow.dismiss();
                 break;
        }
    }

    /**
     * 展示增值税收发票信息
     */
    private void showFp(Zpzz.DataBean dataBean){
        tvCompany.setText(dataBean.getCompanyname());
        tvCode.setText(dataBean.getTaxnum());
        tvAddress.setText(dataBean.getLoginaddress());
        tvMobile.setText(dataBean.getLoginphone());
        tvBank.setText(dataBean.getBankname());
        tvBankCode.setText(dataBean.getBankcode());
        tvSPName.setText(dataBean.getName());
        tvSPMobile.setText(dataBean.getPhone());
        tvSPAddr.setText(dataBean.getAddress());
    }

    /**
     * 获取收货地址列表
     */
    public void getAddrList(){
        HttpMethod.getAddrList(handler);
    }


    /**
     * 获取增票资质信息
     */
    private void getZpzz(){
        HttpMethod.getZpzz(handler);
    }
}
