package com.ylkj.shopproject.activity.user.after;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.selectphoto.bean.Bimp;
import com.ylkj.shopproject.activity.selectphoto.bean.ImageItem;
import com.ylkj.shopproject.adapter.selectphoto.GridImageAdapter;
import com.ylkj.shopproject.util.PicturesUtil;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.After;
import com.zxdc.utils.library.bean.AfterAddress;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.UploadImg;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.BitMapUtil;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyGridView;
import com.zxdc.utils.library.view.OvalImage2Views;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
/**
 * 申请售后
 */
public class ApplyForActivity extends BaseActivity implements View.OnClickListener{

    private TextView tvType,tvTitle,tvMoney,tvNum,tvAddress,tvCompany,tvPhone;
    private OvalImage2Views imgIcon;
    private EditText etDes;
    private MyGridView gridView;
    private RelativeLayout relType;
    private GridImageAdapter adapter;
    //商品对象
    private After.DataBean dataBean;
    //售后申请类型 4-退货 5-换货
    private String applytype;
    //压缩后的图片文件
    private List<File> listFile=new ArrayList<>();
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_for);
        initView();
        //获取对售后寄回地址信息接口
        getAfterAddr();
        //展示商品信息
        showData();
    }


    /**
     * 初始化
     */
    private void initView(){
        tvType=findViewById(R.id.tv_type);
        imgIcon=findViewById(R.id.img_icon);
        tvTitle=findViewById(R.id.tv_title);
        tvMoney=findViewById(R.id.tv_money);
        tvNum=findViewById(R.id.tv_num);
        etDes=findViewById(R.id.et_des);
        gridView=findViewById(R.id.gridview);
        tvAddress=findViewById(R.id.tv_address);
        tvCompany=findViewById(R.id.tv_company);
        tvPhone=findViewById(R.id.tv_phone);
        relType=findViewById(R.id.rel_type);
        relType.setOnClickListener(this);
        findViewById(R.id.tv_confirm).setOnClickListener(this);
        findViewById(R.id.lin_back).setOnClickListener(this);

        //清空图片集合
        Bimp.selectBitmap.clear();
        adapter = new GridImageAdapter(getApplicationContext(), Bimp.selectBitmap);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if (arg2 == Bimp.selectBitmap.size()) {
                    PicturesUtil.selectPhoto(ApplyForActivity.this);
                }
            }
        });
    }


    /**
     * 展示商品信息
     */
    private void showData(){
        dataBean= (After.DataBean) getIntent().getSerializableExtra("dataBean");
        if(null==dataBean){
            return;
        }
        tvTitle.setText(dataBean.getProname());
        tvMoney.setText("¥"+dataBean.getProprice());
        tvNum.setText("x"+dataBean.getProcount());
        Glide.with(this).load(dataBean.getProimg()).centerCrop().error(R.mipmap.default_icon).into(imgIcon);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //选择售后方式
            case R.id.rel_type:
                 showDialogType();
                 break;
            //确认提交
            case R.id.tv_confirm:
                 if(TextUtils.isEmpty(applytype)){
                     ToastUtil.showLong("请选择服务类型！");
                     return;
                 }
                 if(TextUtils.isEmpty(etDes.getText().toString())){
                     ToastUtil.showLong("请输入问题描述！");
                     return;
                 }
                 if(Bimp.selectBitmap.size()==0){
                    ToastUtil.showLong("请选择申请售后的照片！");
                    return;
                 }
                 //先上传图片
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
                //获取对售后寄回地址信息
                case HandlerConstant.GET_AFTER_ADDR_SUCCESS:
                      AfterAddress afterAddress= (AfterAddress) msg.obj;
                      if(null==afterAddress){
                          break;
                      }
                      if(afterAddress.isSussess()){
                          tvAddress.setText("寄回地址  "+afterAddress.getData().getAddress());
                          tvCompany.setText("门店名称  "+afterAddress.getData().getShopname());
                          tvPhone.setText("联系方式  "+afterAddress.getData().getPhone());
                      }
                      break;
                //图片上传回执
                case HandlerConstant.UPLOAD_IMG_SUCCESS:
                    final UploadImg uploadImg= (UploadImg) msg.obj;
                    applyAfter(uploadImg);
                    break;
                //发布回执
                case HandlerConstant.ADD_BUSINESS_SUCCESS:
                    BaseBean baseBean= (BaseBean) msg.obj;
                    if(baseBean==null){
                        break;
                    }
                    if(baseBean.isSussess()){
                        setClass(ApplyForSuccessActivity.class);
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
            case PicturesUtil.CODE_CAMERA_REQUEST:
                if (resultCode == RESULT_OK) {
                    File file = new File(PicturesUtil.pai);
                    if(file.isFile()){
                        ImageItem takePhoto = new ImageItem();
                        takePhoto.setImagePath(file.getPath());
                        Bimp.selectBitmap.add(takePhoto);
                        Bimp.imgList.add(takePhoto);
                        adapter.notifyDataSetChanged();
                    }
                }
                break;
            //返回相册选择图片
            case PicturesUtil.CODE_GALLERY_REQUEST:
                adapter.notifyDataSetChanged();
                break;
            default:
                break;

        }
    }


    /**
     * 弹出选择售后方式的弹框
     */
    private PopupWindow popupWindow;
    private void showDialogType(){
        if(null!=popupWindow && popupWindow.isShowing()){
            popupWindow.dismiss();
        }
        final View view= LayoutInflater.from(ApplyForActivity.this).inflate(R.layout.dialog_applyfor_type,null);
        popupWindow=DialogUtil.showBottom(view,relType);
        view.findViewById(R.id.tv_hh).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popupWindow.dismiss();
                applytype="5";
                tvType.setText("换货");
            }
        });
        view.findViewById(R.id.tv_th).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popupWindow.dismiss();
                applytype="4";
                tvType.setText("退货");
            }
        });
    }


    /**
     * 获取对售后寄回地址信息接口
     */
    private void getAfterAddr(){
        HttpMethod.getAfterAddr(handler);
    }


    /**
     * 上传图片
     */
    private void uploadImg(){
        DialogUtil.showProgress(this,"数据加载中");
        handler.postDelayed(new Runnable() {
            public void run() {
                for (int i=0;i<Bimp.selectBitmap.size();i++){
                    final File file=new File(Bimp.selectBitmap.get(i).getImagePath());
                    if(!file.isFile()){
                        return;
                    }
                    final String newPath= BitMapUtil.compressBitMap(file);
                    final File file1=new File(newPath);
                    if(file1.isFile()){
                        listFile.add(file1);
                    }
                }
                HttpMethod.uploadImg(1,listFile,handler);
            }
        },100);
    }

    /**
     * 订单售后申请接口
     */
    private void applyAfter(UploadImg uploadImg){
        if(null==uploadImg){
            return;
        }
        if(uploadImg.isSussess()){
            String[] imgs=uploadImg.getData();
            StringBuffer stringBuffer=new StringBuffer();
            for (int i=0;i<imgs.length;i++){
                stringBuffer.append(imgs[i]+",");
            }
            DialogUtil.showProgress(this,"数据加载中");
            HttpMethod.applyAfter(dataBean.getOrderid(),applytype,etDes.getText().toString().trim(),stringBuffer.substring(0,stringBuffer.length()-1),handler);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeHandler(handler);
    }
}
