package com.ylkj.shopproject.activity.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.adapter.user.AddressListAdapter;
import com.zxdc.utils.library.base.BaseActivity;

/**
 * 收货地址
 */
public class AddressListActivity extends BaseActivity {

    private ListView listView;
    private AddressListAdapter addressListAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);
        initView();
    }

    /**
     * 初始化
     */
    private void initView(){
        listView=findViewById(R.id.listView);

        addressListAdapter=new AddressListAdapter(this,null);
        listView.setAdapter(addressListAdapter);

        //添加新地址
        findViewById(R.id.tv_add).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setClass(AddAddressActivity.class);
            }
        });
        //返回
        findViewById(R.id.lin_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AddressListActivity.this.finish();
            }
        });
    }
}
