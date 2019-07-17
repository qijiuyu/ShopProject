package com.ylkj.shopproject.activity.user.setting;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.application.APPAplication;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.SPUtil;
import com.zxdc.utils.library.util.ToastUtil;
import java.util.Timer;
import java.util.TimerTask;
/**
 * 密码手机号
 */
public class UpdateMobileActivity extends BaseActivity implements View.OnClickListener{

    private TextView tvMobile,tvSend;
    private EditText etCode,etPwd,etMobile;
    private String mobile;
    //计数器
    private Timer mTimer;
    private int time = 0;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_mobile);
        initView();
        checkTime();
    }

    /**
     * 初始化
     */
    private void initView(){
        tvMobile=findViewById(R.id.tv_mobile);
        etCode=findViewById(R.id.et_code);
        tvSend=findViewById(R.id.tv_send);
        etPwd=findViewById(R.id.et_pwd);
        etMobile=findViewById(R.id.et_mobile);
        tvSend.setOnClickListener(this);
        findViewById(R.id.tv_confirm).setOnClickListener(this);
        findViewById(R.id.lin_back).setOnClickListener(this);
        //显示手机号码
        mobile= APPAplication.login.getLoginName();
        tvMobile.setText(Html.fromHtml("您登录的当前账号<font color='#00273E'>"+mobile.substring(0, 3) + "****" + mobile.substring(mobile.length() - 4, mobile.length())+"</font>请输入登录密码设置"));
    }

    @Override
    public void onClick(View v) {
        final String mobile=etMobile.getText().toString().trim();
        final String code=etCode.getText().toString().trim();
        final String pwd=etPwd.getText().toString().trim();
        switch (v.getId()){
            //获取验证码
            case R.id.tv_send:
                 if(TextUtils.isEmpty(mobile)){
                    ToastUtil.showLong("请输入手机号!");
                    return;
                 }
                if(time>0){
                    return;
                }
                DialogUtil.showProgress(this,"获取验证码中");
                HttpMethod.getSmsCode(mobile,"4",handler);
                 break;
            //确定
            case R.id.tv_confirm:
                 if(TextUtils.isEmpty(code)){
                     ToastUtil.showLong("请输入验证码!");
                     return;
                 }
                if(TextUtils.isEmpty(pwd)){
                    ToastUtil.showLong("请输入新密码!");
                    return;
                }
                if(TextUtils.isEmpty(mobile)){
                    ToastUtil.showLong("请输入手机号!");
                    return;
                }
                 DialogUtil.showProgress(this,"手机号修改中");
                 HttpMethod.updateMobile(mobile,pwd,code,handler);
                 break;
            case R.id.lin_back:
                finish();
                break;
        }
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            BaseBean baseBean;
            switch (msg.what){
                //获取短信验证码
                case HandlerConstant.GET_SMS_CODE_SUCCESS:
                    baseBean= (BaseBean) msg.obj;
                    if(baseBean==null){
                        break;
                    }
                    if(baseBean.isSussess()){
                        //先保存计时时间
                        SPUtil.getInstance(activity).addString("updateMobile_time", String.valueOf((System.currentTimeMillis() + 60000)));
                        time = 60;
                        startTime();
                    }else{
                        ToastUtil.showLong(baseBean.getDesc());
                    }
                    break;
                //修改手机号回执
                case HandlerConstant.UPDATE_MOBILE_SUCCESS:
                    baseBean= (BaseBean) msg.obj;
                    if(baseBean==null){
                        break;
                    }
                    if(baseBean.isSussess()){
                        ToastUtil.showLong(baseBean.getDesc());
                        finish();
                    }else{
                        ToastUtil.showLong(baseBean.getDesc());
                    }
                    break;
                case HandlerConstant.REQUST_ERROR:
                    ToastUtil.showLong(getString(R.string.net_error));
                    break;
            }
            return false;
        }
    });


    /**
     * 动态改变验证码秒数
     */
    private void startTime() {
        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (time <= 0) {
                    handler.post(new Runnable() {
                        public void run() {
                            mTimer.cancel();
                            tvSend.setText("获取短信验证码");
                            SPUtil.getInstance(activity).removeMessage("updateMobile_time");
                        }
                    });
                } else {
                    --time;
                    handler.post(new Runnable() {
                        public void run() {
                            tvSend.setText(time + "秒");
                        }
                    });
                }
            }
        }, 0, 1000);
    }


    /**
     * 判断验证码秒数是否超过一分钟
     */
    private void checkTime() {
        String stoptime = SPUtil.getInstance(activity).getString("updateMobile_time");
        if (!TextUtils.isEmpty(stoptime)) {
            int t = (int) ((Double.parseDouble(stoptime) - System.currentTimeMillis()) / 1000);
            if (t > 0) {
                time = t;
                startTime();
            }
        }
    }
}
