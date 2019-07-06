package com.ylkj.shopproject.activity.user.fault;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.user.evaluation.EvaluationSuccessActivity;
import com.ylkj.shopproject.adapter.user.FaultListAdapter;
import com.zxdc.utils.library.base.BaseActivity;

/**
 * 我的维修记录
 */
public class FaultListActivity extends BaseActivity {

    private ListView listView;
    private FaultListAdapter faultListAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fault_list);
        initView();
    }

    /**
     * 初始化
     */
    private void initView(){
        listView=findViewById(R.id.listview);
        faultListAdapter=new FaultListAdapter(this,null);
        listView.setAdapter(faultListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setClass(FaultDetailsActivity.class);
            }
        });
        findViewById(R.id.lin_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FaultListActivity.this.finish();
            }
        });
    }
}
