package com.ylkj.shopproject.activity.main.fault;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.ylkj.shopproject.R;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.util.ToastUtil;

/**
 * 自定义机型名称
 */
public class EditJCActivity extends BaseActivity {

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_jc);
        initView();
    }


    private void initView(){
        final EditText etName=findViewById(R.id.et_name);
        findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name=etName.getText().toString().trim();
                if(TextUtils.isEmpty(name)){
                    ToastUtil.showLong("请输入机型名称！");
                    return;
                }
                Intent intent=new Intent();
                intent.putExtra("name",name);
                setResult(200,intent);
                EditJCActivity.this.finish();
            }
        });

        findViewById(R.id.lin_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditJCActivity.this.finish();
            }
        });
    }
}
