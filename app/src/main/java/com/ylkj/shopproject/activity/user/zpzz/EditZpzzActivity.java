package com.ylkj.shopproject.activity.user.zpzz;

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
import com.ylkj.shopproject.activity.user.persenter.ZpzzPersenter;
import com.ylkj.shopproject.util.SelectPhoto;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Zpzz;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.UploadImg;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.OvalImage2Views;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
/**
 * 设置增票资质
 */
public class EditZpzzActivity extends BaseActivity implements View.OnClickListener{

    private EditText etUnitName,etCode,etAddress,etMobile,etBack,etBackCode,etSpName,etSpMobile,etSpAddr;
    private OvalImage2Views imgOne,imgTwo;
    //选择的图片类型 0：企业营业执照  1：开户许可证
    private int imgType;
    /**
     * yyFile：企业营业执照剪裁的图片
     * xkFile：开户许可证剪裁的图片
     */
    private File yyFile,xkFile;
    private ZpzzPersenter zpzzPersenter;
    private String unitName,code,address,mobile,bank,bankCode,spName,spMobile,spAddr;
    //增票资质对象
    private Zpzz zpzz;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editzpzz);
        zpzzPersenter=new ZpzzPersenter(this);
        initView();
        showData();
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
        etSpName=findViewById(R.id.et_sp_name);
        etSpMobile=findViewById(R.id.et_sp_mobile);
        etSpAddr=findViewById(R.id.et_sp_addr);
        imgOne=findViewById(R.id.img_one);
        imgTwo=findViewById(R.id.img_two);
        imgOne.setOnClickListener(this);
        imgTwo.setOnClickListener(this);
        findViewById(R.id.tv_submit).setOnClickListener(this);
        findViewById(R.id.lin_back).setOnClickListener(this);
    }

    /**\
     * 显示要编辑的数据
     */
    private void showData(){
        zpzz= (Zpzz) getIntent().getSerializableExtra("zpzz");
        if(null==zpzz){
            return;
        }
        Zpzz.DataBean dataBean=zpzz.getData();
        if(null==dataBean){
            return;
        }
        etUnitName.setText(dataBean.getCompanyname());
        etCode.setText(dataBean.getTaxnum());
        etAddress.setText(dataBean.getLoginaddress());
        etMobile.setText(dataBean.getLoginphone());
        etBack.setText(dataBean.getBankname());
        etBackCode.setText(dataBean.getBankcode());
        Glide.with(this).load(dataBean.getLicenseimg()).override(107,107).centerCrop().into(imgOne);
        Glide.with(this).load(dataBean.getPermitimg()).override(107,107).centerCrop().into(imgTwo);
        etSpName.setText(dataBean.getName());
        etSpMobile.setText(dataBean.getPhone());
        etSpAddr.setText(dataBean.getAddress());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_one:
                imgType=0;
                SelectPhoto.selectPhoto(this);
                 break;
            case R.id.img_two:
                imgType=1;
                SelectPhoto.selectPhoto(this);
                break;
            case R.id.tv_submit:
                 unitName=etUnitName.getText().toString().trim();
                 code=etCode.getText().toString().trim();
                 address=etAddress.getText().toString().trim();
                 mobile=etMobile.getText().toString().trim();
                 bank=etBack.getText().toString().trim();
                 bankCode=etBackCode.getText().toString().trim();
                 spName=etSpName.getText().toString().trim();
                 spMobile=etSpMobile.getText().toString().trim();
                 spAddr=etSpAddr.getText().toString().trim();
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
                if(yyFile==null){
                    ToastUtil.showLong("请选择企业营业执照!");
                    return;
                }
                if(xkFile==null){
                    ToastUtil.showLong("请选择开户许可证!");
                    return;
                }
                if(TextUtils.isEmpty(spName)){
                    ToastUtil.showLong("请输入收票人姓名!");
                    return;
                }
                if(TextUtils.isEmpty(spMobile)){
                    ToastUtil.showLong("请输入收票人联系方式!");
                    return;
                }
                if(TextUtils.isEmpty(spAddr)){
                    ToastUtil.showLong("请输入收票人地址!");
                    return;
                }
                //图片上传中
                uploadImg();
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
                      if(uploadImg.isSussess()){
                          uploadData(uploadImg);
                      }else{
                          ToastUtil.showLong(uploadImg.getDesc());
                      }
                      break;
                //回执
                case HandlerConstant.ADD_ZPZZ_SUCCESS:
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
                if(imgType==0){
                    yyFile=new File(SelectPhoto.crop);
                    Glide.with(EditZpzzActivity.this).load(Uri.fromFile(yyFile)).into(imgOne);
                }else{
                    xkFile=new File(SelectPhoto.crop);
                    Glide.with(EditZpzzActivity.this).load(Uri.fromFile(xkFile)).into(imgTwo);
                }
                break;
            default:
                break;

        }
    }


    /**
     * 上传图片
     */
    private  void uploadImg(){
        List<File> fileList=new ArrayList<>();
        fileList.add(yyFile);
        fileList.add(xkFile);
        DialogUtil.showProgress(this,"数据提交中");
        HttpMethod.uploadImg(1,fileList,handler);
    }

    /**
     * 上传数据
     */
    private void uploadData(UploadImg uploadImg){
        DialogUtil.showProgress(this,"数据提交中");
        String[] imgs=uploadImg.getData();
        if(imgs.length==2){
            HttpMethod.add_zpzz(unitName,code,address,mobile,bank,bankCode,imgs[0],imgs[1],spName,spMobile,spAddr,handler);
        }
    }


    @Override
    protected void onDestroy() {
        removeHandler(handler);
        super.onDestroy();
    }
}
