package com.ylkj.shopproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import com.ylkj.shopproject.activity.business.BusinessActivity;
import com.ylkj.shopproject.activity.main.MainActivity;
import com.ylkj.shopproject.activity.shopping.ShoppingActivity;
import com.ylkj.shopproject.activity.type.TypeActivity;
import com.ylkj.shopproject.activity.user.UserActivity;
import com.ylkj.shopproject.activity.video.PlayVideoActivity;
import com.zxdc.utils.library.util.ActivitysLifecycle;
import com.zxdc.utils.library.util.StatusBarUtils;
import com.zxdc.utils.library.util.ToastUtil;
import java.util.ArrayList;
import java.util.List;

public class TabActivity extends android.app.TabActivity implements View.OnClickListener{

    // 按两次退出
    protected long exitTime = 0;
    private TabHost tabHost;
    private ImageView imgMain,imgType,imgBusiness,imgShopping,imgUser;
    private TextView tvMain,tvType,tvBusiness,tvShopping,tvUser;
    private List<ImageView> imgList=new ArrayList<>();
    private List<TextView> tvList=new ArrayList<>();
    private int[] notClick=new int[]{R.mipmap.main_no,R.mipmap.type_no,R.mipmap.business_no,R.mipmap.shopping_no,R.mipmap.user_no};
    private int[] yesClick=new int[]{R.mipmap.main_yes,R.mipmap.type_yes,R.mipmap.business_yes,R.mipmap.shopping_yes,R.mipmap.user_yes};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.transparencyBar(this);
        setContentView(R.layout.activity_tab);
        initView();
    }


    /**
     * 初始化
     */
    private void initView(){
        imgMain=findViewById(R.id.img_main);
        imgType=findViewById(R.id.img_type);
        imgBusiness=findViewById(R.id.img_business);
        imgShopping=findViewById(R.id.img_shopping);
        imgUser=findViewById(R.id.img_user);
        tvMain=findViewById(R.id.tv_main);
        tvType=findViewById(R.id.tv_type);
        tvBusiness=findViewById(R.id.tv_business);
        tvShopping=findViewById(R.id.tv_shopping);
        tvUser=findViewById(R.id.tv_user);
        findViewById(R.id.lin_main).setOnClickListener(this);
        findViewById(R.id.lin_type).setOnClickListener(this);
        findViewById(R.id.lin_business).setOnClickListener(this);
        findViewById(R.id.lin_shopping).setOnClickListener(this);
        findViewById(R.id.lin_user).setOnClickListener(this);
        imgList.add(imgMain);imgList.add(imgType);imgList.add(imgBusiness);imgList.add(imgShopping);imgList.add(imgUser);
        tvList.add(tvMain);tvList.add(tvType);tvList.add(tvBusiness);tvList.add(tvShopping);tvList.add(tvUser);
        tabHost=this.getTabHost();
        TabHost.TabSpec spec;
        spec=tabHost.newTabSpec("首页").setIndicator("首页").setContent(new Intent(this, MainActivity.class));
        tabHost.addTab(spec);
        spec=tabHost.newTabSpec("分类").setIndicator("分类").setContent(new Intent(this, TypeActivity.class));
        tabHost.addTab(spec);
        spec=tabHost.newTabSpec("生意圈").setIndicator("生意圈").setContent(new Intent(this, BusinessActivity.class));
        tabHost.addTab(spec);
        spec=tabHost.newTabSpec("购物车").setIndicator("购物车").setContent(new Intent(this, ShoppingActivity.class));
        tabHost.addTab(spec);
        spec=tabHost.newTabSpec("我的").setIndicator("我的").setContent(new Intent(this, UserActivity.class));
        tabHost.addTab(spec);
        tabHost.setCurrentTab(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //首页
            case R.id.lin_main:
                 updateImg(0);
                 tabHost.setCurrentTabByTag("首页");
                 break;
            //分类
            case R.id.lin_type:
                 updateImg(1);
                 tabHost.setCurrentTabByTag("分类");
                 break;
             //生意圈
            case R.id.lin_business:
                 updateImg(2);
                 tabHost.setCurrentTabByTag("生意圈");
                 break;
             //购物车
            case R.id.lin_shopping:
                 updateImg(3);
                 tabHost.setCurrentTabByTag("购物车");
                 break;
             //我的
            case R.id.lin_user:
                 updateImg(4);
                 tabHost.setCurrentTabByTag("我的");
                 break;
             default:
                 break;
        }
    }


    private void updateImg(int type){
        for(int i=0;i<5;i++){
            if(i==type){
                imgList.get(i).setImageDrawable(getResources().getDrawable(yesClick[i]));
                tvList.get(i).setTextColor(getResources().getColor(R.color.color_36C7B5));
            }else{
                imgList.get(i).setImageDrawable(getResources().getDrawable(notClick[i]));
                tvList.get(i).setTextColor(getResources().getColor(R.color.color_666666));
            }
        }
    }


    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN ) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtil.showLong("再按一次退出程序!");
                exitTime = System.currentTimeMillis();
            } else {
                //关闭广播
                ActivitysLifecycle.getInstance().exit();
            }
            return false;
        }
        return super.dispatchKeyEvent(event);
    }
}
