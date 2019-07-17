package com.ylkj.shopproject.activity.business;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.ylkj.shopproject.R;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 举报生意圈
 */
public class ReportActivity extends BaseActivity implements View.OnClickListener{

    private ImageView imgSQ,imgSR,imgMM,imgOther;
    private List<ImageView> list=new ArrayList<>();
    //选择的下标
    private int index=1;
    //生意圈id
    private int id;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        initView();
    }

    /**
     * 初始化
     */
    private void initView(){
        id=getIntent().getIntExtra("id",0);
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
        findViewById(R.id.tv_submit).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lin_sq:
                 index=1;
                 setImg();
                 break;
            case R.id.lin_sr:
                index=2;
                setImg();
                break;
            case R.id.lin_mm:
                index=3;
                setImg();
                break;
            case R.id.lin_other:
                index=4;
                setImg();
                break;
            //提交
            case R.id.tv_submit:
                  report();
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
                //举报回执
                case HandlerConstant.REPORT_SUCCESS:
                      BaseBean baseBean= (BaseBean) msg.obj;
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


    private void setImg(){
        for (int i=0;i<list.size();i++){
             if(i==(index-1)){
                 list.get(i).setImageDrawable(getResources().getDrawable(R.mipmap.report_yes));
             }else{
                 list.get(i).setImageDrawable(getResources().getDrawable(R.mipmap.report_no));
             }
        }
    }

    /**
     * 举报
     */
    private void report(){
        DialogUtil.showProgress(this,"举报中");
        HttpMethod.report(id,String.valueOf(index),handler);
    }
}
