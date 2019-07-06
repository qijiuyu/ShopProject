package com.ylkj.shopproject.activity.user.zpzz;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.main.persenter.AddFaultPersenter;
import com.ylkj.shopproject.activity.user.persenter.ZpzzPersenter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.OvalImage2Views;
import java.io.File;
/**
 * 设置增票资质
 */
public class EditZpzzActivity extends BaseActivity implements View.OnClickListener{

    private EditText etUnitName,etCode,etAddress,etMobile,etBack,etBackCode;
    private OvalImage2Views imgOne,imgTwo;
    //选择的图片类型 0：企业营业执照  1：开户许可证
    private int imgType;
    /**
     * mpCropPath：企业营业执照剪裁的图片
     * jcCropPath：开户许可证剪裁的图片
     */
    private String yyCropPath,xkCropPath;
    private ZpzzPersenter zpzzPersenter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editzpzz);
        zpzzPersenter=new ZpzzPersenter(this);
        initView();
    }


    /**
     * 初始化
     */
    private void initView(){
        etUnitName=findViewById(R.id.et_unit_name);
        etCode=findViewById(R.id.et_code);
        etAddress=findViewById(R.id.et_address);
        etMobile=findViewById(R.id.et_mobile);
        etBack=findViewById(R.id.et_back);
        etBackCode=findViewById(R.id.et_back_code);
        imgOne=findViewById(R.id.img_one);
        imgTwo=findViewById(R.id.img_two);
        imgOne.setOnClickListener(this);
        imgTwo.setOnClickListener(this);
        findViewById(R.id.tv_submit).setOnClickListener(this);
        findViewById(R.id.lin_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_one:
                imgType=0;
                zpzzPersenter.selectPhoto();
                 break;
            case R.id.img_two:
                imgType=1;
                zpzzPersenter.selectPhoto();
                break;
            case R.id.tv_submit:
                 final String unitName=etUnitName.getText().toString().trim();
                 final String code=etCode.getText().toString().trim();
                 final String address=etAddress.getText().toString().trim();
                 final String mobile=etMobile.getText().toString().trim();
                 final String bank=etBack.getText().toString().trim();
                 final String bankCode=etBackCode.getText().toString().trim();
                 if(TextUtils.isEmpty(unitName)){
                     ToastUtil.showLong("请输入单位名称!");
                     return;
                 }
                if(TextUtils.isEmpty(code)){
                    ToastUtil.showLong("请输入纳税人号码!");
                    return;
                }
                if(TextUtils.isEmpty(address)){
                    ToastUtil.showLong("请输入注册地址!");
                    return;
                }
                if(TextUtils.isEmpty(mobile)){
                    ToastUtil.showLong("请输入电话号码!");
                    return;
                }
                if(TextUtils.isEmpty(bank)){
                    ToastUtil.showLong("请输入开户银行!");
                    return;
                }
                if(TextUtils.isEmpty(bankCode)){
                    ToastUtil.showLong("请输入银行账号!");
                    return;
                }
                setClass(ZpzzSuccessActivity.class);
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
            case AddFaultPersenter.CODE_CAMERA_REQUEST:
                if (resultCode == RESULT_OK) {
                    File tempFile = new File(AddFaultPersenter.pai);
                    if (tempFile.isFile()) {
                        zpzzPersenter.cropRawPhoto(Uri.fromFile(tempFile));
                    }
                }
                break;
            //返回相册选择图片
            case AddFaultPersenter.CODE_GALLERY_REQUEST:
                if (data != null) {
                    zpzzPersenter.cropRawPhoto(data.getData());
                }
                break;
            //返回裁剪的图片
            case AddFaultPersenter.CODE_RESULT_REQUEST:
                final File f = new File(ZpzzPersenter.crop);
                if(imgType==0){
                    yyCropPath=ZpzzPersenter.crop;
                    Glide.with(EditZpzzActivity.this).load(Uri.fromFile(f)).into(imgOne);
                }else{
                    xkCropPath=ZpzzPersenter.crop;
                    Glide.with(EditZpzzActivity.this).load(Uri.fromFile(f)).into(imgTwo);
                }
                break;
            default:
                break;

        }
    }
}
