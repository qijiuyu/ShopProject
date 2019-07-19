package com.ylkj.shopproject.activity.user.evaluation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.selectphoto.bean.Bimp;
import com.ylkj.shopproject.activity.selectphoto.bean.ImageItem;
import com.ylkj.shopproject.activity.user.after.ApplyForActivity;
import com.ylkj.shopproject.adapter.selectphoto.GridImageAdapter;
import com.ylkj.shopproject.util.PicturesUtil;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.CommOrder;
import com.zxdc.utils.library.bean.MyOrder;
import com.zxdc.utils.library.bean.UploadImg;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.BitMapUtil;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyGridView;
import com.zxdc.utils.library.view.OvalImage2Views;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 添加评价
 */
public class AddEvaluationActivity extends BaseActivity implements View.OnClickListener{

    private OvalImage2Views imgIcon;
    private TextView tvTitle,tvMoney,tvOldMoney,tvNum;
    private EditText etContent;
    private ImageView img1,img2,img3,img4,img5;
    private MyGridView gridView;
    private GridImageAdapter adapter;
    private List<ImageView> listImg=new ArrayList<>();
    //子订单对象
    private CommOrder.DataBean dataBean;
    //星级
    private int starNum=1;
    //压缩后的图片文件
    private List<File> listFile=new ArrayList<>();
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_evaluation);
        initView();
        showData();
    }


    /**
     * 初始化
     */
    private void initView(){
        imgIcon=findViewById(R.id.img_icon);
        tvTitle=findViewById(R.id.tv_title);
        tvMoney=findViewById(R.id.tv_money);
        tvOldMoney=findViewById(R.id.tv_old_money);
        tvNum=findViewById(R.id.tv_num);
        etContent=findViewById(R.id.et_content);
        img1=findViewById(R.id.img_x1);
        img2=findViewById(R.id.img_x2);
        img3=findViewById(R.id.img_x3);
        img4=findViewById(R.id.img_x4);
        img5=findViewById(R.id.img_x5);
        gridView=findViewById(R.id.gridview);
        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);
        img5.setOnClickListener(this);
        listImg.add(img1);listImg.add(img2);listImg.add(img3);listImg.add(img4);listImg.add(img5);
        findViewById(R.id.tv_submit).setOnClickListener(this);
        findViewById(R.id.lin_back).setOnClickListener(this);

        //清空图片集合
        Bimp.selectBitmap.clear();
        adapter = new GridImageAdapter(getApplicationContext(), Bimp.selectBitmap);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if (arg2 == Bimp.selectBitmap.size()) {
                    PicturesUtil.selectPhoto(AddEvaluationActivity.this);
                }
            }
        });
    }

    /**
     * 展示商品数据
     */
    private void showData(){
        dataBean= (CommOrder.DataBean) getIntent().getSerializableExtra("dataBean");
        if(null==dataBean){
            return;
        }
        Glide.with(this).load(dataBean.getProimg()).override(90,90).centerCrop().into(imgIcon);
        tvTitle.setText(dataBean.getProname());
        tvMoney.setText("¥"+dataBean.getPrice());
        tvOldMoney.setText("¥"+dataBean.getOldprice());
        tvNum.setText("x"+dataBean.getCount());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_x1:
                 setXing(1);
                 break;
            case R.id.img_x2:
                 setXing(2);
                 break;
            case R.id.img_x3:
                 setXing(3);
                 break;
            case R.id.img_x4:
                 setXing(4);
                 break;
            case R.id.img_x5:
                 setXing(5);
                 break;
            //提交评价
            case R.id.tv_submit:
                 String content=etContent.getText().toString().trim();
                 if(TextUtils.isEmpty(content)){
                     ToastUtil.showLong("请输入评价信息！");
                     return;
                 }
                if(Bimp.selectBitmap.size()==0){
                    ToastUtil.showLong("请选择评价的照片！");
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
            switch (msg.what){
                //图片上传回执
                case HandlerConstant.UPLOAD_IMG_SUCCESS:
                    final UploadImg uploadImg= (UploadImg) msg.obj;
                    evalOrder(uploadImg);
                    break;
                //评价回执
                case HandlerConstant.EVAL_ORDER_SUCCESS:
                    DialogUtil.closeProgress();
                    BaseBean baseBean= (BaseBean) msg.obj;
                    if(baseBean==null){
                        break;
                    }
                    if(baseBean.isSussess()){
                        setClass(EvaluationSuccessActivity.class);
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
     * 设置星级
     */
    private void setXing(int index){
        starNum=index;
        for (int i=0;i<listImg.size();i++){
            if(i<index){
                listImg.get(i).setImageDrawable(getResources().getDrawable(R.mipmap.yes_xing));
            }else{
                listImg.get(i).setImageDrawable(getResources().getDrawable(R.mipmap.no_xing));
            }
        }
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
     * 评价订单
     */
    private void evalOrder(UploadImg uploadImg){
        if(null==uploadImg){
            return;
        }
        if(uploadImg.isSussess()){
            String[] imgs=uploadImg.getData();
            StringBuffer stringBuffer=new StringBuffer();
            for (int i=0;i<imgs.length;i++){
                stringBuffer.append(imgs[i]+",");
            }
            final String content=etContent.getText().toString().trim();
            final String imgPath=stringBuffer.substring(0,stringBuffer.length()-1);
            HttpMethod.evalOrder("0",starNum,content,imgPath,dataBean.getDetailid(),handler);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeHandler(handler);
    }
}
