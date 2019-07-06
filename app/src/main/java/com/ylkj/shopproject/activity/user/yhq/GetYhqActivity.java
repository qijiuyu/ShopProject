package com.ylkj.shopproject.activity.user.yhq;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.adapter.user.MyYhqAdapter;
import com.zxdc.utils.library.base.BaseActivity;

/**
 * 领券中心
 */
public class GetYhqActivity extends BaseActivity {

    private ListView listView;
    private MyYhqAdapter myYhqAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_yhq);
        initView();
    }


    private void initView(){
        listView=findViewById(R.id.listview);
        myYhqAdapter=new MyYhqAdapter(this,null);
        listView.setAdapter(myYhqAdapter);
    }
}
