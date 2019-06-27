package com.ylkj.shopproject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.LogUtils;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                HttpMethod.login("15011224467","1234",mHandler);
            }
        });
    }


    private Handler mHandler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            if(msg.what== HandlerConstant.GET_ACCESS_TOKEN_SUCCESS){
                LogUtils.e(msg.obj.toString()+"+++++++++++++++++++");
            }
            return false;
        }
    });
}
