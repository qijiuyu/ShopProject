package com.ylkj.shopproject.activity.business;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.adapter.business.SearchBusinessAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.util.ToastUtil;

/**
 * 搜索生意圈
 */
public class SearchBusinessActivity extends BaseActivity implements View.OnClickListener{

    private EditText etKey;
    private ListView listView;
    private SearchBusinessAdapter searchBusinessAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_business);
        initView();
    }


    /**
     * 初始化
     */
    private void initView(){
        etKey=findViewById(R.id.et_keys);
        listView=findViewById(R.id.listView);
        findViewById(R.id.lin_back).setOnClickListener(this);
        findViewById(R.id.tv_search).setOnClickListener(this);

        searchBusinessAdapter=new SearchBusinessAdapter(this,null);
        listView.setAdapter(searchBusinessAdapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //搜索
            case R.id.tv_search:
                 final String key=etKey.getText().toString().trim();
                 if(TextUtils.isEmpty(key)){
                     ToastUtil.showLong("请输入关键字搜索");
                     return;
                 }
                 break;
            case R.id.lin_back:
                  finish();
                  break;
        }
    }
}
