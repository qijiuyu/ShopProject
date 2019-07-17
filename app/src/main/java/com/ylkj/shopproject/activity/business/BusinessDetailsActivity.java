package com.ylkj.shopproject.activity.business;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.showimg.ShowImgActivity;
import com.ylkj.shopproject.adapter.business.BusinessCommAdapter;
import com.ylkj.shopproject.adapter.business.BusinessImgAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.Business;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.CircleImageView;
import com.zxdc.utils.library.view.MeasureListView;
import com.zxdc.utils.library.view.MyGridView;
/**
 * 生意圈详情
 */
public class BusinessDetailsActivity extends BaseActivity implements View.OnClickListener{

    private CircleImageView imgUser;
    private TextView tvCompany,tvContent,tvType,tvLocation,tvTop,tvCommNum,tvTime,tvDzNum,tvPlNum;
    private MyGridView gridView;
    private MeasureListView listView;
    private EditText etComm;
    //详情对象
    private Business.DataBean dataBean;
    //发布过的图片的adapter
    private BusinessImgAdapter businessImgAdapter;
    //评论的adapter
    private BusinessCommAdapter businessCommAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_details);
        initView();
        showData();
    }

    /**
     * 初始化
     */
    private void initView(){
        imgUser=findViewById(R.id.img_user);
        tvCompany=findViewById(R.id.tv_company);
        tvContent=findViewById(R.id.tv_content);
        tvType=findViewById(R.id.tv_type);
        tvLocation=findViewById(R.id.tv_location);
        tvTop=findViewById(R.id.tv_top);
        gridView=findViewById(R.id.gridview);
        tvCommNum=findViewById(R.id.tv_comm_num);
        listView=findViewById(R.id.listView);
        tvTime=findViewById(R.id.tv_time);
        tvDzNum=findViewById(R.id.tv_dz_num);
        tvPlNum=findViewById(R.id.tv_pl_num);
        tvDzNum.findViewById(R.id.tv_dz_num);
        etComm=findViewById(R.id.et_comm);
        tvDzNum.setOnClickListener(this);
        findViewById(R.id.tv_send).setOnClickListener(this);
        findViewById(R.id.img_phone).setOnClickListener(this);
        findViewById(R.id.img_report).setOnClickListener(this);
        findViewById(R.id.lin_back).setOnClickListener(this);
    }

    /**
     * 展示详情数据
     */
    private void showData(){
        dataBean= (Business.DataBean) getIntent().getSerializableExtra("dataBean");
        Glide.with(this).load(dataBean.getUserimg()).override(42,42).centerCrop().into(imgUser);
        tvCompany.setText(dataBean.getCompanyname());
        tvContent.setText(dataBean.getContent());
        tvType.setText("#"+dataBean.getTypename()+"#");
        tvLocation.setText(dataBean.getAddress());
        tvTime.setText(dataBean.getDistance()+"km    "+dataBean.getCreatetime());
        tvCommNum.setText("最新评论（"+dataBean.getCommentcount()+"）");
        tvDzNum.setText(String.valueOf(dataBean.getPraiselist().length));
        tvPlNum.setText(String.valueOf(dataBean.getCommentcount()));
        if(dataBean.getIstop()==1){
            tvTop.setVisibility(View.VISIBLE);
        }
        //展示发布过的图片
        businessImgAdapter=new BusinessImgAdapter(this,dataBean.getImglist());
        gridView.setAdapter(businessImgAdapter);

        //评论列表
        businessCommAdapter=new BusinessCommAdapter(this,dataBean.getCommentlist());
        listView.setAdapter(businessCommAdapter);
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                //点赞回执
                case HandlerConstant.LIKE_BUSINESS_SUCCESS:
                     final BaseBean baseBean= (BaseBean) msg.obj;
                     if(baseBean.isSussess()){
                         int num=Integer.parseInt(tvDzNum.getText().toString().trim());
                         if(dataBean.getIspraise()==1){
                             dataBean.setIspraise(0);
                             --num;
                         }else{
                             dataBean.setIspraise(1);
                             ++num;
                         }
                         tvDzNum.setText(String.valueOf(num));
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
        Intent intent=new Intent();
        switch (v.getId()){
            //点赞
            case R.id.tv_dz_num:
                  likeBusiness();
                  break;
             //发表评论
            case R.id.tv_send:
                  final String content=etComm.getText().toString().trim();
                  if(TextUtils.isEmpty(content)){
                      ToastUtil.showLong("请输入您要评论的内容！");
                      return;
                  }
                  addComment(content);
                  break;
            //举报
            case R.id.img_report:
                 intent.setClass(this,ReportActivity.class);
                 intent.putExtra("id",dataBean.getId());
                 startActivity(intent);
                  break;
            //打电话
            case R.id.img_phone:
                 intent.setAction(Intent.ACTION_CALL);
                 Uri data = Uri.parse("tel:" + dataBean.getPhone());
                 intent.setData(data);
                 startActivity(intent);
                  break;
            case R.id.lin_back:
                  finish();
                  break;
        }
    }


    /**
     * 点赞生意圈
     */
    private void likeBusiness(){
        DialogUtil.showProgress(this,"点赞中");
        HttpMethod.likeBusiness(dataBean.getId(),handler);
    }

    /**
     * 评论生意圈
     */
    private void addComment(String content){
        DialogUtil.showProgress(this,"评论中");
        HttpMethod.addComment(dataBean.getId(),content,handler);
    }
}
