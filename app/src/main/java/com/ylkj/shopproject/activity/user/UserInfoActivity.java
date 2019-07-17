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
import com.zxdc.utils.library.bean.UploadImg;
import com.zxdc.utils.library.bean.UserInfo;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.CircleImageView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
/**
 * 我的资料
 */
public class UserInfoActivity extends BaseActivity implements View.OnClickListener{

    private CircleImageView imgUser;
    private EditText etNick,etDes;
    //头像文件
    private File file;
    private String nick,des,imgPath;
    //用户个人资料对象
    private UserInfo userInfo;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        initView();
        showData();
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

    /**
     * 展示个人信息
     */
    private void showData(){
        userInfo= (UserInfo) getIntent().getSerializableExtra("userInfo");
        if(null==userInfo){
            return;
        }
        etNick.setText(userInfo.getData().getNickname());
        etDes.setText(userInfo.getData().getIntroduction());
        imgPath=userInfo.getData().getImgurl();
        Glide.with(this).load(imgPath).centerCrop().error(R.mipmap.default_icon).into(imgUser);
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
                 nick=etNick.getText().toString().trim();
                 des=etDes.getText().toString().trim();
                if(null==file && null==userInfo){
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
                 if(TextUtils.isEmpty(imgPath)){
                     uploadImg();
                 }else{
                    setUserInfo();
                 }
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
                //图片上传成功
                case HandlerConstant.UPLOAD_IMG_SUCCESS:
                      final UploadImg uploadImg= (UploadImg) msg.obj;
                      if(null==uploadImg){
                          break;
                      }
                      if(uploadImg.isSussess() && uploadImg.getData().length>0){
                          imgPath=uploadImg.getData()[0];
                          //设置用户资料
                          setUserInfo();
                      }else{
                          ToastUtil.showLong(uploadImg.getDesc());
                      }
                      break;
                //数据设置回执
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
                 file = new File(SelectPhoto.crop);
                 if(file.isFile()){
                     imgPath=null;
                     Glide.with(UserInfoActivity.this).load(Uri.fromFile(file)).into(imgUser);
                 }
                 break;
            default:
                break;

        }
    }

    /**
     * 上传图片
     */
    private void uploadImg(){
        DialogUtil.showProgress(this,"数据设置中");
        List<File> list=new ArrayList<>();
        list.add(file);
        HttpMethod.uploadImg(1,list,handler);
    }

    /**
     * 设置用户资料
     */
    private void setUserInfo(){
        DialogUtil.showProgress(UserInfoActivity.this,"数据设置中");
        HttpMethod.editUser(nick,imgPath,des,handler);
    }
}
