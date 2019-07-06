package com.ylkj.shopproject.activity.user.fault;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.selectphoto.bean.Bimp;
import com.ylkj.shopproject.activity.selectphoto.bean.ImageItem;
import com.ylkj.shopproject.adapter.selectphoto.GridImageAdapter;
import com.ylkj.shopproject.util.PicturesUtil;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.view.CircleImageView;
import com.zxdc.utils.library.view.MyGridView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 维修评价
 */
public class FaultEvaluationActivity extends BaseActivity implements View.OnClickListener{

    private TextView tvName;
    private CircleImageView imgUser;
    private ImageView img1,img2,img3,img4,img5;
    private EditText etDes;
    private MyGridView gridView;
    private GridImageAdapter adapter;
    private List<ImageView> listImg=new ArrayList<>();
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fault_evaluation);
        initView();
    }


    /**
     * 初始化
     */
    private void initView(){
        tvName=findViewById(R.id.tv_name);
        imgUser=findViewById(R.id.img_user);
        etDes=findViewById(R.id.et_des);
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
                    PicturesUtil.selectPhoto(FaultEvaluationActivity.this);
                }
            }
        });
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
        for (int i=0;i<listImg.size();i++){
            if(i<index){
                listImg.get(i).setImageDrawable(getResources().getDrawable(R.mipmap.yes_xing));
            }else{
                listImg.get(i).setImageDrawable(getResources().getDrawable(R.mipmap.no_xing));
            }
        }
    }
}
