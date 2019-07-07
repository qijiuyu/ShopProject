package com.ylkj.shopproject.activity.business;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.ylkj.shopproject.R;
import com.zxdc.utils.library.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 举报生意圈
 */
public class ReportActivity extends BaseActivity implements View.OnClickListener{

    private ImageView imgSQ,imgSR,imgMM,imgOther;
    private List<ImageView> list=new ArrayList<>();
    //选择的下标
    private int index;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        initView();
    }

    /**
     * 初始化
     */
    private void initView(){
        imgSQ=findViewById(R.id.img_sq);
        imgSR=findViewById(R.id.img_sr);
        imgMM=findViewById(R.id.img_mm);
        imgOther=findViewById(R.id.img_other);
        list.add(imgSQ);list.add(imgSR);list.add(imgMM);list.add(imgOther);
        findViewById(R.id.lin_back).setOnClickListener(this);
        findViewById(R.id.lin_sq).setOnClickListener(this);
        findViewById(R.id.lin_sr).setOnClickListener(this);
        findViewById(R.id.lin_mm).setOnClickListener(this);
        findViewById(R.id.lin_other).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lin_sq:
                 index=0;
                 setImg();
                 break;
            case R.id.lin_sr:
                index=1;
                setImg();
                break;
            case R.id.lin_mm:
                index=2;
                setImg();
                break;
            case R.id.lin_other:
                index=3;
                setImg();
                break;
            case R.id.lin_back:
                 finish();
                 break;
        }
    }


    private void setImg(){
        for (int i=0;i<list.size();i++){
             if(i==index){
                 list.get(i).setImageDrawable(getResources().getDrawable(R.mipmap.report_yes));
             }else{
                 list.get(i).setImageDrawable(getResources().getDrawable(R.mipmap.report_no));
             }
        }
    }
}