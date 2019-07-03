package com.ylkj.shopproject.activity.main.fault;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.adapter.main.SelectJCAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.util.ToastUtil;

/**
 * 选择机型名称
 */
public class SelectJCActivity extends BaseActivity implements View.OnClickListener{

    private EditText etKey;
    private ListView listView;
    private SelectJCAdapter selectJCAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_jc);
        initView();
    }


    /**
     * 初始化
     */
    private void initView(){
        etKey=findViewById(R.id.et_keys);
        listView=findViewById(R.id.listView);
        findViewById(R.id.tv_confirm).setOnClickListener(this);
        findViewById(R.id.lin_back).setOnClickListener(this);

        selectJCAdapter=new SelectJCAdapter(this,null);
        listView.setAdapter(selectJCAdapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //点击搜索
            case R.id.tv_confirm:
                  final String key=etKey.getText().toString().trim();
                  if(TextUtils.isEmpty(key)){
                      ToastUtil.showLong("请输入要搜索的关键字！");
                      return;
                  }
                  break;
            case R.id.lin_back:
                 finish();
                 break;
        }
    }
}
