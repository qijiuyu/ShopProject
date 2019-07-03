package com.ylkj.shopproject.activity.main.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.zxdc.utils.library.base.BaseActivity;

/**
 * 消息详情页
 */
public class NewsDetailsActivity extends BaseActivity {


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        initView();
    }


    private void initView(){
        TextView tvTitle=findViewById(R.id.tv_title);
        TextView tvTime=findViewById(R.id.tv_time);
    }
}
