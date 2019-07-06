package com.ylkj.shopproject.activity.user.after;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.zxdc.utils.library.base.BaseActivity;

/**
 * 申请成功
 */
public class ApplyForSuccessActivity  extends BaseActivity {

    private TextView tvTime;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_for_success);
        initView();
    }


    /**
     * 初始化
     */
    private void initView(){
        tvTime=findViewById(R.id.tv_time);
        findViewById(R.id.lin_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ApplyForSuccessActivity.this.finish();
            }
        });
        findViewById(R.id.lin_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
        findViewById(R.id.lin_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
    }
}
