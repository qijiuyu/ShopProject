package com.ylkj.shopproject.activity.user.certification;

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
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.Certification;
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
 * 机构认证
 */
public class CertificationActivity extends BaseActivity  implements View.OnClickListener{

    private EditText etCompany,etAddress,etProject,etName,etMobile;
    private OvalImage2Views imgOne,imgTwo;
    //选择的图片类型 0：企业营业执照  1：开户许可证
    private int imgType;
    /**
     * yyFile：企业营业执照剪裁的图片
     * xkFile：开户许可证剪裁的图片
     */
    private File yyFile,xkFile;
    private String company,address,project,name,mobile;
    //机构认证数据对象
    private Certification certification;
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
        etProject=findViewById(R.id.et_project);
        etName=findViewById(R.id.et_name);
        etMobile=findViewById(R.id.et_mobile);
        imgOne=findViewById(R.id.img_one);
        imgTwo=findViewById(R.id.img_two);
        imgOne.setOnClickListener(this);
        imgTwo.setOnClickListener(this);
        findViewById(R.id.tv_confirm).setOnClickListener(this);
        findViewById(R.id.lin_back).setOnClickListener(this);
    }


    /**
     * 展示需要编辑的数据
     */
    private void showData(){
        certification= (Certification) getIntent().getSerializableExtra("certification");
        if(null==certification){
            return;
        }
        Certification.DataBean dataBean=certification.getData();
        if(null==dataBean){
            return;
        }
        etCompany.setText(dataBean.getInstname());
        etAddress.setText(dataBean.getAddress());
        etProject.setText(dataBean.getProname());
        etName.setText(dataBean.getName());
        etMobile.setText(dataBean.getPhone());
        Glide.with(this).load(dataBean.getLicenseimg()).override(107,107).centerCrop().into(imgOne);
        Glide.with(this).load(dataBean.getPermitimg()).override(107,107).centerCrop().into(imgTwo);

    }

    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                //图片上传成功
                case HandlerConstant.UPLOAD_IMG_SUCCESS:
                    final UploadImg uploadImg= (UploadImg) msg.obj;
                    if(uploadImg.isSussess()){
                        setInst(uploadImg);
                    }else{
                        ToastUtil.showLong(uploadImg.getDesc());
                    }
                    break;
                //数据提交回执
                case HandlerConstant.SET_INST_SUCCESS:
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
            //提交
            case R.id.tv_confirm:
                company=etCompany.getText().toString().trim();
                address=etAddress.getText().toString().trim();
                project=etProject.getText().toString().trim();
                name=etName.getText().toString().trim();
                 mobile=etMobile.getText().toString().trim();
                if(TextUtils.isEmpty(company)){
                    ToastUtil.showLong("请输入企业名称");
                    return;
                }
                if(TextUtils.isEmpty(address)){
                    ToastUtil.showLong("请输入企业地址");
                    return;
                }
                if(TextUtils.isEmpty(project)){
                    ToastUtil.showLong("请输入项目名称");
                    return;
                }
                if(TextUtils.isEmpty(name)){
                    ToastUtil.showLong("请输入联系人名称");
                    return;
                }
                if(TextUtils.isEmpty(mobile)){
                    ToastUtil.showLong("请输入联系方式");
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
                //上传图片
                uploadImg();
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
                if(imgType==0){
                    yyFile=new File(SelectPhoto.crop);
                    Glide.with(CertificationActivity.this).load(Uri.fromFile(yyFile)).into(imgOne);
                }else{
                    xkFile=new File(SelectPhoto.crop);
                    Glide.with(CertificationActivity.this).load(Uri.fromFile(xkFile)).into(imgTwo);
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
     * 提交信息
     */
    private void setInst(UploadImg uploadImg){
        DialogUtil.showProgress(this,"数据提交中");
        String[] imgs=uploadImg.getData();
        if(imgs.length==2){
            HttpMethod.setInst(company,address,project,name,mobile,imgs[0],imgs[1],handler);
        }
    }

    @Override
    protected void onDestroy() {
        removeHandler(handler);
        super.onDestroy();
    }

}
