package com.ylkj.shopproject.activity.business;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.selectphoto.bean.Bimp;
import com.ylkj.shopproject.activity.selectphoto.bean.ImageItem;
import com.ylkj.shopproject.adapter.selectphoto.GridImageAdapter;
import com.ylkj.shopproject.util.PicturesUtil;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.view.MyGridView;
import java.io.File;
/**
 * 发布生意圈
 */
public class AddBusinessActivity extends BaseActivity implements View.OnClickListener{

    private MyGridView gridView;
    private GridImageAdapter adapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_business);
        initView();
    }

    /**
     * 初始化
     */
    private void initView(){
        gridView=findViewById(R.id.gridview);
        //清空图片集合
        Bimp.selectBitmap.clear();
        adapter = new GridImageAdapter(getApplicationContext(), Bimp.selectBitmap);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if (arg2 == Bimp.selectBitmap.size()) {
                    PicturesUtil.selectPhoto(AddBusinessActivity.this);
                }
            }
        });

        findViewById(R.id.tv_cancle).setOnClickListener(this);
        findViewById(R.id.tv_send).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_send:
                 break;
            case R.id.tv_cancle:
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

}
