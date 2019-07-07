package com.ylkj.shopproject.activity.user;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.util.SelectPhoto;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.view.CircleImageView;
import java.io.File;
/**
 * 我的资料
 */
public class UserInfoActivity extends BaseActivity implements View.OnClickListener{

    private CircleImageView imgUser;
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
                 break;
            case R.id.lin_back:
                 finish();
                 break;
        }
    }


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
                     Glide.with(UserInfoActivity.this).load(Uri.fromFile(f)).into(imgUser);
                 }
                 break;
            default:
                break;

        }
    }
}
