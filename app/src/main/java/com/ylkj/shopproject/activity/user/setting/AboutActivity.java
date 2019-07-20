package com.ylkj.shopproject.activity.user.setting;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.About;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

/**
 * 关于我们
 */
public class AboutActivity extends BaseActivity {

    private TextView tvTitle,tvContent;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initView();
        about();
    }

    /**
     * 初始化
     */
    private void initView(){
        tvTitle=findViewById(R.id.tv_title);
        tvContent=findViewById(R.id.tv_content);
        findViewById(R.id.lin_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AboutActivity.this.finish();
            }
        });
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                case HandlerConstant.GET_ABOUT_SUCCESS:
                      final About about= (About) msg.obj;
                      if(null==about){
                          break;
                      }
                      if(about.isSussess()){
                          tvTitle.setText(about.getData().getTitle());
                          tvContent.setText(Html.fromHtml(about.getData().getContent()));
                      }else{
                          ToastUtil.showLong(about.getDesc());
                      }
                      break;
                case HandlerConstant.REQUST_ERROR:
                    ToastUtil.showLong(getString(R.string.net_error));
                    break;
            }
            return false;
        }
    });


    /**\
     * 查询关于我们的信息
     */
    private void about(){
        DialogUtil.showProgress(this,"数据加载中");
        HttpMethod.about("0",handler);
    }


    @Override
    protected void onDestroy() {
        removeHandler(handler);
        super.onDestroy();
    }
}
