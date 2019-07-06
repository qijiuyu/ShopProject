package com.ylkj.shopproject.activity.user.after;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.selectphoto.bean.Bimp;
import com.ylkj.shopproject.activity.selectphoto.bean.ImageItem;
import com.ylkj.shopproject.adapter.selectphoto.GridImageAdapter;
import com.ylkj.shopproject.util.PicturesUtil;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.view.MyGridView;
import com.zxdc.utils.library.view.OvalImage2Views;
import java.io.File;
/**
 * 申请售后
 */
public class ApplyForActivity extends BaseActivity implements View.OnClickListener{

    private TextView tvType,tvTitle,tvMoney,tvNum,tvAddress,tvCompany;
    private OvalImage2Views imgIcon;
    private EditText etDes;
    private MyGridView gridView;
    private RelativeLayout relType;
    private GridImageAdapter adapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_for);
        initView();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //选择售后方式
            case R.id.rel_type:
                 showDialogType();
                 break;
            //确认提交
            case R.id.tv_confirm:
                 setClass(ApplyForSuccessActivity.class);
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
     * 弹出选择售后方式的弹框
     */
    private void showDialogType(){
        final View view= LayoutInflater.from(ApplyForActivity.this).inflate(R.layout.dialog_applyfor_type,null);
        PopupWindow popupWindow=DialogUtil.showBottom(view,relType);
        view.findViewById(R.id.tv_hh).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tvType.setText("换货");
            }
        });
        view.findViewById(R.id.tv_th).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tvType.setText("退货");
            }
        });
    }

}
