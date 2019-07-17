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
 * 密码重置
 */
public class UpdatePwdActivity extends BaseActivity implements View.OnClickListener{

    private TextView tvMobile,tvSend;
    private EditText etCode,etPwd;
    //计数器
    private Timer mTimer;
    private int time = 0;
    private String mobile;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pwd);
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
        tvSend.setOnClickListener(this);
        findViewById(R.id.tv_confirm).setOnClickListener(this);
        findViewById(R.id.lin_back).setOnClickListener(this);

        //显示手机号码
        mobile=APPAplication.login.getLoginName();
        tvMobile.setText(Html.fromHtml("您登录的当前账号<font color='#00273E'>"+mobile.substring(0, 3) + "****" + mobile.substring(mobile.length() - 4, mobile.length())+"</font>点击获取验证码"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //获取验证码
            case R.id.tv_send:
                 if(time>0){
                     return;
                 }
                 DialogUtil.showProgress(this,"获取验证码中");
                 HttpMethod.getSmsCode(mobile,"1",handler);
                 break;
            //确定
            case R.id.tv_confirm:
                 final String code=etCode.getText().toString().trim();
                 final String pwd=etPwd.getText().toString().trim();
                 if(TextUtils.isEmpty(code)){
                     ToastUtil.showLong("请输入验证码!");
                     return;
                 }
                if(TextUtils.isEmpty(pwd)){
                    ToastUtil.showLong("请输入新密码!");
                    return;
                }
                 DialogUtil.showProgress(this,"密码修改中");
                 HttpMethod.updatePwd(pwd,code,handler);
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
                        SPUtil.getInstance(activity).addString("updatePwd_time", String.valueOf((System.currentTimeMillis() + 60000)));
                        time = 60;
                        startTime();
                    }else{
                        ToastUtil.showLong(baseBean.getDesc());
                    }
                    break;
                //修改密码回执
                case HandlerConstant.UPDATE_PWD_SUCCESS:
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
                            SPUtil.getInstance(activity).removeMessage("updatePwd_time");
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
        String stoptime = SPUtil.getInstance(activity).getString("updatePwd_time");
        if (!TextUtils.isEmpty(stoptime)) {
            int t = (int) ((Double.parseDouble(stoptime) - System.currentTimeMillis()) / 1000);
            if (t > 0) {
                time = t;
                startTime();
            }
        }
    }
}
