package com.ylkj.shopproject.activity.user.evaluation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.adapter.selectphoto.GridImageAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.view.MyGridView;
import com.zxdc.utils.library.view.OvalImage2Views;

import java.util.ArrayList;
import java.util.List;

/**
 * 评价成功界面
 */
public class EvaluationSuccessActivity extends BaseActivity {

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation_success);
        initView();
    }


    /**
     * 初始化
     */
    private void initView(){
        findViewById(R.id.tv_main).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EvaluationSuccessActivity.this.finish();
            }
        });
        findViewById(R.id.lin_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EvaluationSuccessActivity.this.finish();
            }
        });
    }
}
