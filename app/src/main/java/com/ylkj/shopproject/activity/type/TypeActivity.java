package com.ylkj.shopproject.activity.type;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.main.search.SearchActivity;
import com.ylkj.shopproject.adapter.type.TypeAdapter;
import com.zxdc.utils.library.base.BaseActivity;

/**
 * 底部菜单:分类
 */
public class TypeActivity extends BaseActivity {

    private ListView listView;
    private TypeAdapter typeAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);
        initView();
    }


    /**
     * 初始化
     */
    private void initView(){
        listView=findViewById(R.id.listView);
        typeAdapter=new TypeAdapter(this,null);
        listView.setAdapter(typeAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setClass(TypeDetailsActivity.class);
            }
        });
        //进入搜索界面
        findViewById(R.id.lin_search).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setClass(SearchActivity.class);
            }
        });
    }
}
