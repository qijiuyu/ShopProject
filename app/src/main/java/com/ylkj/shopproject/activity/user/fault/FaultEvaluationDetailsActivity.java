package com.ylkj.shopproject.activity.user.fault;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.adapter.selectphoto.GridImageAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.view.CircleImageView;
import com.zxdc.utils.library.view.MyGridView;
import com.zxdc.utils.library.view.OvalImage2Views;

import java.util.ArrayList;
import java.util.List;

/**
 * 维修评价详情界面
 */
public class FaultEvaluationDetailsActivity extends BaseActivity {

    private CircleImageView imgIcon;
    private TextView tvName,tvMobile,tvContent;
    private ImageView img1,img2,img3,img4,img5;
    private MyGridView gridView;
    private GridImageAdapter adapter;
    private List<ImageView> listImg=new ArrayList<>();
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fault_evaluation_details);
        initView();
    }


    /**
     * 初始化
     */
    private void initView(){
        imgIcon=findViewById(R.id.img_icon);
        tvName=findViewById(R.id.tv_name);
        tvMobile=findViewById(R.id.tv_mobile);
        img1=findViewById(R.id.img_x1);
        img2=findViewById(R.id.img_x2);
        img3=findViewById(R.id.img_x3);
        img4=findViewById(R.id.img_x4);
        img5=findViewById(R.id.img_x5);
        tvContent=findViewById(R.id.tv_content);
        gridView=findViewById(R.id.gridview);
        listImg.add(img1);listImg.add(img2);listImg.add(img3);listImg.add(img4);listImg.add(img5);
        findViewById(R.id.lin_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FaultEvaluationDetailsActivity.this.finish();
            }
        });
    }
}
