package com.ylkj.shopproject.activity.user.setting;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.ylkj.shopproject.R;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

/**
 * 意见反馈
 */
public class FeedBackActivity extends BaseActivity {

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        initView();
    }

    /**
     * 初始化
     */
    private void initView(){
        final EditText etContent=findViewById(R.id.et_content);
        findViewById(R.id.tv_submit).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String content=etContent.getText().toString().trim();
                if(TextUtils.isEmpty(content)){
                    ToastUtil.showLong("请输入您的想法和意见!");
                    return;
                }
                DialogUtil.showProgress(FeedBackActivity.this,"提交数据中");
                HttpMethod.feedBack(content,handler);
            }
        });
        findViewById(R.id.lin_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FeedBackActivity.this.finish();
            }
        });
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                case HandlerConstant.FEED_BACK_SUCCESS:
                      final BaseBean baseBean= (BaseBean) msg.obj;
                      if(null==baseBean){
                          break;
                      }
                      if(baseBean.isSussess()){
                          finish();
                      }
                      ToastUtil.showLong(baseBean.getDesc());
                      break;
                case HandlerConstant.REQUST_ERROR:
                    ToastUtil.showLong(getString(R.string.net_error));
                    break;
            }
            return false;
        }
    });


    @Override
    protected void onDestroy() {
        removeHandler(handler);
        super.onDestroy();
    }
}
