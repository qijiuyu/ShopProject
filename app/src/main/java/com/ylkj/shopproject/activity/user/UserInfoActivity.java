package com.ylkj.shopproject.activity.user;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.util.SelectPhoto;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.UserInfo;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.BitMapUtil;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.CircleImageView;
import java.io.File;
/**
 * 我的资料
 */
public class UserInfoActivity extends BaseActivity implements View.OnClickListener{

    private CircleImageView imgUser;
    private EditText etNick,etDes;
    //头像地址
    private String imgPath;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        initView();
    }


    /**
     * 初始化控件
     */
    private void initView(){
        imgUser=findViewById(R.id.img_user);
        etNick=findViewById(R.id.et_nick);
        etDes=findViewById(R.id.et_des);
        imgUser.setOnClickListener(this);
        findViewById(R.id.tv_save).setOnClickListener(this);
        findViewById(R.id.lin_back).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //选择头衔
            case R.id.img_user:
                 SelectPhoto.selectPhoto(this);
                 break;
            //保存
            case R.id.tv_save:
                 final String nick=etNick.getText().toString().trim();
                 final String des=etDes.getText().toString().trim();
                if(TextUtils.isEmpty(imgPath)){
                    ToastUtil.showLong("请选择头像！");
                    return;
                }
                 if(TextUtils.isEmpty(nick)){
                     ToastUtil.showLong("请输入昵称！");
                     return;
                 }
                 if(TextUtils.isEmpty(des)){
                     ToastUtil.showLong("请输入您的简介！");
                     return;
                 }
                DialogUtil.showProgress(this,"数据设置中");
                HttpMethod.editUser(nick,imgPath,des,handler);
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
                case HandlerConstant.EDIT_USER_SUCCESS:
                      final UserInfo userInfo= (UserInfo) msg.obj;
                      if(null==userInfo){
                          break;
                      }
                      if(userInfo.isSussess()){
                          Intent intent=new Intent();
                          intent.putExtra("userInfo",userInfo);
                          setResult(100,intent);
                          finish();
                      }
                      ToastUtil.showLong(userInfo.getDesc());
                      break;
                case HandlerConstant.REQUST_ERROR:
                    ToastUtil.showLong(getString(R.string.net_error));
                    break;
            }
            return false;
        }
    });


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //返回拍照图片
            case SelectPhoto.CODE_CAMERA_REQUEST:
                if (resultCode == RESULT_OK) {
                    File tempFile = new File(SelectPhoto.pai);
                    if (tempFile.isFile()) {
                        SelectPhoto.cropRawPhoto(Uri.fromFile(tempFile),this);
                    }
                }
                break;
            //返回相册选择图片
            case SelectPhoto.CODE_GALLERY_REQUEST:
                if (data != null) {
                    SelectPhoto.cropRawPhoto(data.getData(),this);
                }
                break;
            //返回裁剪的图片
            case SelectPhoto.CODE_RESULT_REQUEST:
                 final File f = new File(SelectPhoto.crop);
                 if(f.isFile()){
                     //生成base64字符串
                     imgPath= BitMapUtil.getBase64Str(SelectPhoto.crop);
                     Glide.with(UserInfoActivity.this).load(Uri.fromFile(f)).into(imgUser);
                 }
                 break;
            default:
                break;

        }
    }
}
