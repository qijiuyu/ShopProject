package com.ylkj.shopproject.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.user.address.AddressListActivity;
import com.ylkj.shopproject.activity.user.after.AfterActivity;
import com.ylkj.shopproject.activity.user.business.MyBusinessActivity;
import com.ylkj.shopproject.activity.user.certification.CertificationActivity;
import com.ylkj.shopproject.activity.user.collection.MyCollectionActivity;
import com.ylkj.shopproject.activity.user.company.EditCompanyActivity;
import com.ylkj.shopproject.activity.user.evaluation.EvaluationActivity;
import com.ylkj.shopproject.activity.user.fault.FaultListActivity;
import com.ylkj.shopproject.activity.user.order.OrderActivity;
import com.ylkj.shopproject.activity.user.setting.SettingActivity;
import com.ylkj.shopproject.activity.user.tuan.MyTuanActivity;
import com.ylkj.shopproject.activity.user.yhq.MyYhqActivity;
import com.ylkj.shopproject.activity.user.zpzz.EditZpzzActivity;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.UserInfo;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.CircleImageView;

/**
 * 我的
 */
public class UserActivity extends BaseActivity implements View.OnClickListener{

    private CircleImageView imgUser;
    private TextView tvName;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initView();
        //获取用户信息
        getUser();
    }


    /**
     * 初始化控件
     */
    private void initView(){
        imgUser=findViewById(R.id.img_user);
        tvName=findViewById(R.id.tv_name);
        imgUser.setOnClickListener(this);
        findViewById(R.id.img_customer).setOnClickListener(this);
        findViewById(R.id.tv_dfk).setOnClickListener(this);
        findViewById(R.id.tv_dfh).setOnClickListener(this);
        findViewById(R.id.tv_dsh).setOnClickListener(this);
        findViewById(R.id.tv_dpj).setOnClickListener(this);
        findViewById(R.id.tv_sh).setOnClickListener(this);
        findViewById(R.id.rel_pt).setOnClickListener(this);
        findViewById(R.id.rel_wxjl).setOnClickListener(this);
        findViewById(R.id.rel_yhq).setOnClickListener(this);
        findViewById(R.id.rel_sc).setOnClickListener(this);
        findViewById(R.id.rel_jl).setOnClickListener(this);
        findViewById(R.id.rel_jgrz).setOnClickListener(this);
        findViewById(R.id.rel_zpzz).setOnClickListener(this);
        findViewById(R.id.rel_shdz).setOnClickListener(this);
        findViewById(R.id.rel_qyxx).setOnClickListener(this);
        findViewById(R.id.rel_setting).setOnClickListener(this);
        findViewById(R.id.img_news).setOnClickListener(this);
        findViewById(R.id.tv_all_order).setOnClickListener(this);
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                //查询用户资料回执
                case HandlerConstant.GET_USER_SUCCESS:
                    final UserInfo userInfo= (UserInfo) msg.obj;
                    if(null==userInfo){
                        break;
                    }
                    if(userInfo.isSussess()){
                        showUserInfo(userInfo);
                    }else{
                        ToastUtil.showLong(userInfo.getDesc());
                    }
                    break;
                case HandlerConstant.REQUST_ERROR:
                    ToastUtil.showLong(getString(R.string.net_error));
                    break;
            }
            return false;
        }
    });


    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()){
            //我的资料
            case R.id.img_user:
                 intent.setClass(this,UserInfoActivity.class);
                 startActivityForResult(intent,100);
                 break;
            //全部订单
            case R.id.tv_all_order:
                 intentOrder(0);
                 break;
             //待付款
            case R.id.tv_dfk:
                 intentOrder(1);
                 break;
            //待发货
            case R.id.tv_dfh:
                 intentOrder(2);
                 break;
            //待收货
            case R.id.tv_dsh:
                 intentOrder(3);
                 break;
            //待评价
            case R.id.tv_dpj:
                 setClass(EvaluationActivity.class);
                 break;
            //售后
            case R.id.tv_sh:
                 setClass(AfterActivity.class);
                 break;
           //我的拼团
            case R.id.rel_pt:
                 setClass(MyTuanActivity.class);
                 break;
            //我的维修记录
            case R.id.rel_wxjl:
                 setClass(FaultListActivity.class);
                 break;
            //我的优惠券
            case R.id.rel_yhq:
                 setClass(MyYhqActivity.class);
                 break;
            //我的收藏
            case R.id.rel_sc:
                 setClass(MyCollectionActivity.class);
                 break;
            //我的交流
            case R.id.rel_jl:
                 setClass(MyBusinessActivity.class);
                 break;
            //机构认证
            case R.id.rel_jgrz:
                 setClass(CertificationActivity.class);
                 break;
            //增票资质
            case R.id.rel_zpzz:
                 setClass(EditZpzzActivity.class);
                 break;
            //收货地址
            case R.id.rel_shdz:
                 setClass(AddressListActivity.class);
                 break;
            //企业信息
            case R.id.rel_qyxx:
                 setClass(EditCompanyActivity.class);
                 break;
            //设置
            case R.id.rel_setting:
                 setClass(SettingActivity.class);
                 break;
            //客服
            case R.id.img_customer:
                 break;
            //消息
            case R.id.img_news:
                 break;
        }
    }


    /**
     * 跳转到订单
     * @param type
     */
    private void intentOrder(int type){
        Intent intent=new Intent(this,OrderActivity.class);
        intent.putExtra("type",type);
        startActivity(intent);
    }


    /**
     * 显示用户资料信息
     */
    private void showUserInfo(UserInfo userInfo){
        tvName.setText(userInfo.getData().getNickname());
        Glide.with(this).load(userInfo.getData().getImgurl()).centerCrop().error(R.mipmap.default_icon).into(imgUser);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==100){
            final UserInfo userInfo= (UserInfo) data.getSerializableExtra("userInfo");
            showUserInfo(userInfo);
        }
    }

    /**
     * 获取用户信息
     */
    private void getUser(){
        HttpMethod.getUser(handler);
    }
}
