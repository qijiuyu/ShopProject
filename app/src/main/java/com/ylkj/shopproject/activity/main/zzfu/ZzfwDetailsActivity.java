package com.ylkj.shopproject.activity.main.zzfu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.ZzfuTypeList;
/**
 * 增值服务详情页
 */
public class ZzfwDetailsActivity extends BaseActivity {

    private TextView tvTitle,tvContent;
    private ImageView imgIcon;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zzfw_details);
        initView();
    }

    /**
     * 初始化
     */
    private void initView(){
        tvTitle=findViewById(R.id.tv_title);
        tvContent=findViewById(R.id.tv_content);
        imgIcon=findViewById(R.id.img_icon);
        final ZzfuTypeList.ListBean listBean= (ZzfuTypeList.ListBean) getIntent().getSerializableExtra("listBean");
        if(null!=listBean){
            tvTitle.setText(listBean.getTitle());
            tvContent.setText(Html.fromHtml(listBean.getContent()));
            Glide.with(ZzfwDetailsActivity.this).load(listBean.getImgurl()).override(345,153).centerCrop().into(imgIcon);
        }
        findViewById(R.id.lin_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ZzfwDetailsActivity.this.finish();
            }
        });
    }
}
