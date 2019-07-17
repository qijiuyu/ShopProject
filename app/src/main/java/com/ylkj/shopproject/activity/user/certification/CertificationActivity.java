package com.ylkj.shopproject.activity.user.certification;

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
 * 机构认证
 */
public class CertificationActivity extends BaseActivity {

    private EditText etCompany,etAddress,etUser,etMobile;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_certification);
        initView();
    }


    /**
     * 初始化
     */
    private void initView(){
        etCompany=findViewById(R.id.et_company);
        etAddress=findViewById(R.id.et_address);
        etUser=findViewById(R.id.et_user);
        etMobile=findViewById(R.id.et_mobile);

        //确定
        findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String company=etCompany.getText().toString().trim();
                final String address=etAddress.getText().toString().trim();
                final String user=etUser.getText().toString().trim();
                final String mobile=etMobile.getText().toString().trim();
                if(TextUtils.isEmpty(company)){
                    ToastUtil.showLong("请输入企业名称");
                    return;
                }
                if(TextUtils.isEmpty(address)){
                    ToastUtil.showLong("请输入企业地址");
                    return;
                }
                if(TextUtils.isEmpty(user)){
                    ToastUtil.showLong("请输入联系人名称");
                    return;
                }
                if(TextUtils.isEmpty(mobile)){
                    ToastUtil.showLong("请输入联系方式");
                    return;
                }
                DialogUtil.showProgress(CertificationActivity.this,"数据提交中");
                HttpMethod.editCompany(company,address,user,mobile,handler);
            }
        });
    }

    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                //回执
                case HandlerConstant.EDIT_COMPANY_SUCCESS:
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
