package com.ylkj.shopproject.activity.main.zzfu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.adapter.main.ZzfuTypeAdapter;
import com.zxdc.utils.library.base.BaseActivity;

/**
 * 增值服务分类页
 */
public class ZzfuTypeActivity extends BaseActivity {

    private ListView listView;
    private ZzfuTypeAdapter zzfuTypeAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zzfw);
        initView();
    }


    /**
     * 初始化
     */
    private void initView(){
        listView=findViewById(R.id.listView);
        zzfuTypeAdapter=new ZzfuTypeAdapter(this,null);
        listView.setAdapter(zzfuTypeAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        findViewById(R.id.lin_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ZzfuTypeActivity.this.finish();
            }
        });
    }

}
