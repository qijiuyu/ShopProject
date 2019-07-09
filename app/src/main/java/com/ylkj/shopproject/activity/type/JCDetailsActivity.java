package com.ylkj.shopproject.activity.type;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.main.persenter.JCDetailsPersenter;
import com.ylkj.shopproject.activity.showimg.ShowImgActivity;
import com.ylkj.shopproject.adapter.type.JC_Details_Name_Adapter;
import com.ylkj.shopproject.adapter.type.JC_Details_Type_Adapter;
import com.ylkj.shopproject.adapter.type.JC_Details_Type_DataAdapter;
import com.ylkj.shopproject.adapter.type.SelectColorAdapter;
import com.ylkj.shopproject.util.MyImgLoader;
import com.ylkj.shopproject.view.HorizontalListView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.util.SPUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MeasureListView;
import com.zxdc.utils.library.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * 机床详情页
 */
public class JCDetailsActivity extends BaseActivity implements View.OnClickListener{

    private Banner banner;
    private TextView tvDes,tvApp;
    private HorizontalListView listColor;
    private MeasureListView listType,listName;
    //选择颜色的adapter
    private SelectColorAdapter selectColorAdapter;
    //类型描述的adapter
    private JC_Details_Type_Adapter jc_details_type_adapter;
    //名称的adapter
    private JC_Details_Name_Adapter jc_details_name_adapter;
    private JCDetailsPersenter jcDetailsPersenter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jc_details);
        jcDetailsPersenter=new JCDetailsPersenter(this);
        initView();
        jcDetailsPersenter.setBanner(banner);
    }

    /**
     * 初始化
     */
    @SuppressLint("WrongViewCast")
    private void initView(){
        banner=findViewById(R.id.banner);
        tvDes=findViewById(R.id.tv_des);
        tvApp=findViewById(R.id.tv_app);
        listColor=findViewById(R.id.list_color);
        listName=findViewById(R.id.list_name);
        listType=findViewById(R.id.list_type);
        tvDes.setOnClickListener(this);
        tvApp.setOnClickListener(this);
        findViewById(R.id.img_kf).setOnClickListener(this);
        findViewById(R.id.img_collection).setOnClickListener(this);
        findViewById(R.id.lin_back).setOnClickListener(this);

        selectColorAdapter=new SelectColorAdapter(this,null);
        listColor.setAdapter(selectColorAdapter);

        jc_details_type_adapter=new JC_Details_Type_Adapter(this,null);
        listType.setAdapter(jc_details_type_adapter);

        jc_details_name_adapter=new JC_Details_Name_Adapter(this,null);
        listName.setAdapter(jc_details_name_adapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //功能介绍
            case R.id.tv_des:
                 break;
            //平台消息
            case R.id.tv_app:
                 Intent intent=new Intent(this, ShowImgActivity.class);
                 intent.putExtra("imgs", SPUtil.gson.toJson(jcDetailsPersenter.imgList));
                 startActivity(intent);
                 break;
            //客服
            case R.id.img_kf:
                 break;
           //收藏
            case R.id.img_collection:
                 break;
            case R.id.lin_back:
                  finish();
                  break;
        }
    }
}
