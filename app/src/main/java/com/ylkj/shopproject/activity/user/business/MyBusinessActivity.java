package com.ylkj.shopproject.activity.user.business;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.adapter.user.MyBusinessAdapter;
import com.zxdc.utils.library.base.BaseActivity;

/**
 * 我的交流
 */
public class MyBusinessActivity extends BaseActivity {

    private ListView listView;
    private MyBusinessAdapter myBusinessAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_business);
        initView();
    }


    /**
     * 初始化控件
     */
    private void initView(){
        listView=findViewById(R.id.listView);
        myBusinessAdapter=new MyBusinessAdapter(this,null);
        listView.setAdapter(myBusinessAdapter);
    }
}
