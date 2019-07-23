package com.ylkj.shopproject.activity.main.fault;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.adapter.main.SelectJCAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.JCName;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择机型名称
 */
public class SelectJCActivity extends BaseActivity implements View.OnClickListener{

    private EditText etKey;
    private ListView listView;
    private SelectJCAdapter selectJCAdapter;
    private List<JCName.DataBean> list=new ArrayList<>();
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_jc);
        initView();
        //获取机床机型列表接口
        getJcName(null);
    }


    /**
     * 初始化
     */
    private void initView(){
        etKey=findViewById(R.id.et_keys);
        listView=findViewById(R.id.listView);
        selectJCAdapter=new SelectJCAdapter(SelectJCActivity.this,list);
        listView.setAdapter(selectJCAdapter);
        findViewById(R.id.tv_confirm).setOnClickListener(this);
        findViewById(R.id.tv_submit).setOnClickListener(this);
        findViewById(R.id.lin_back).setOnClickListener(this);
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
                  getJcName(key);
                  break;
            case R.id.tv_submit:
                  if(selectJCAdapter.index==-1){
                      return;
                  }
                  Intent intent=new Intent();
                  intent.putExtra("name",list.get(selectJCAdapter.index).getName());
                  setResult(200,intent);
                  finish();
                  break;
            case R.id.lin_back:
                 finish();
                 break;
        }
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                //查询回执
                case HandlerConstant.GET_JC_NAME_SUCCESS:
                      list.clear();
                      JCName jcName= (JCName) msg.obj;
                      if(null==jcName){
                          break;
                      }
                      if(jcName.isSussess()){
                          list.addAll(jcName.getData());
                          selectJCAdapter.notifyDataSetChanged();
                      }else{
                          ToastUtil.showLong(jcName.getDesc());
                      }
                      break;
                case HandlerConstant.REQUST_ERROR:
                    ToastUtil.showLong(activity.getString(R.string.net_error));
                    break;
            }
            return false;
        }
    });


    /**
     * 获取机床机型列表接口
     */
    public void getJcName(String key){
        DialogUtil.showProgress(this,"数据查询中");
        HttpMethod.getJcName(key,handler);
    }
}
