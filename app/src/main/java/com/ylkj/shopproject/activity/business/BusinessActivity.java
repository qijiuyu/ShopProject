package com.ylkj.shopproject.activity.business;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.business.fragment.BusinessFragment;
import com.ylkj.shopproject.view.PagerSlidingTabStrip;
import com.ylkj.shopproject.view.ViewPagerCallBack;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.BusinessMsg;
import com.zxdc.utils.library.bean.ZzfuType;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.CircleImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 生意圈
 */
public class BusinessActivity extends BaseActivity {

    private CircleImageView imgPic;
    private TextView tvNum;
    private PagerSlidingTabStrip tabs;
    private DisplayMetrics dm;
    //切换fragment的位置
    public static int index;
    private ViewPager pager;
    //分类对象集合
    public static List<ZzfuType.dataBean> typeList=new ArrayList<>();
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        initView();
        //获取分类
        getType();
        //生意圈消息提示
        getBusinessMsg();
    }


    /**
     * 初始化
     */
    private void initView(){
        imgPic=findViewById(R.id.img_trip);
        tvNum=findViewById(R.id.tv_num);
        dm = getResources().getDisplayMetrics();
        pager =  findViewById(R.id.pager);
        tabs =  findViewById(R.id.tabs);
        ZzfuType.dataBean dataBean1=new ZzfuType.dataBean();
        dataBean1.setId(0);
        dataBean1.setName("全部");
        ZzfuType.dataBean dataBean2=new ZzfuType.dataBean();
        dataBean2.setId(-2);
        dataBean2.setName("附近");
        ZzfuType.dataBean dataBean3=new ZzfuType.dataBean();
        dataBean3.setId(-3);
        dataBean3.setName("热门");
        typeList.add(dataBean1);typeList.add(dataBean2);typeList.add(dataBean3);
        //发布动态
        findViewById(R.id.img_add).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(BusinessActivity.this,AddBusinessActivity.class);
                intent.putExtra("typeList",(Serializable)typeList);
                startActivity(intent);
            }
        });

        //进入搜索界面
        findViewById(R.id.img_search).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setClass(SearchBusinessActivity.class);
            }
        });
        //进入消息界面
        findViewById(R.id.rel_trip).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setClass(BusinessNewsActivity.class);
            }
        });
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                //查询分类回执
                case HandlerConstant.GET_TYPE_SUCCESS:
                    final ZzfuType zzfuType= (ZzfuType) msg.obj;
                    if(null==zzfuType){
                        break;
                    }
                    if(zzfuType.isSussess()){
                        typeList.addAll(zzfuType.getData());
                        setTabsValue();
                    }else{
                        ToastUtil.showLong(zzfuType.getDesc());
                    }
                    break;
                //生意圈消息提示
                case HandlerConstant.GET_BUSINESS_MSG_SUCCESS:
                       BusinessMsg businessMsg= (BusinessMsg) msg.obj;
                       if(null==businessMsg){
                           break;
                       }
                       if(businessMsg.isSussess()){
                           if(null==businessMsg.getData()){
                               break;
                           }
                           findViewById(R.id.rel_trip).setVisibility(View.VISIBLE);
                           tvNum.setText(businessMsg.getData().getNum()+"条消息");
                           Glide.with(BusinessActivity.this).load(businessMsg.getData().getImgurl()).override(20,20).centerCrop().error(R.mipmap.default_icon).into(imgPic);
                       }
                      break;
                case HandlerConstant.REQUST_ERROR:
                    ToastUtil.showLong(getString(R.string.net_error));
                    break;
            }
            return false;
        }
    });


    /**
     * 对PagerSlidingTabStrip的各项属性进行赋值。
     */
    private void setTabsValue() {
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        pager.setOffscreenPageLimit(typeList.size());
        tabs.setViewPagerCallBack(viewPagerCallBack);
        tabs.setViewPager(pager);
        // 设置Tab是自动填充满屏幕的
        tabs.setShouldExpand(true);
        // 设置Tab的分割线是透明的
        tabs.setDividerColor(Color.TRANSPARENT);
        // 设置Tab底部线的高度
        tabs.setUnderlineHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 1, dm));
        // 设置Tab Indicator的高度
        tabs.setIndicatorHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 2, dm));
        // 设置Tab标题文字的大小
        tabs.setTextSize((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 14, dm));
        // 设置Tab Indicator的颜色
        tabs.setIndicatorColorResource(R.color.color_37C7B5);
        // 设置选中Tab文字的颜色 (这是我自定义的一个方法)
        tabs.setTextColorResource(R.color.color_33333);
        tabs.setSelectedTextColorResource(R.color.color_37C7B5);
        // 取消点击Tab时的背景色
        tabs.setTabBackground(0);
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return typeList.get(position).getName();
        }

        @Override
        public int getCount() {
            return typeList.size();
        }

        @Override
        public Fragment getItem(int position) {
            return new BusinessFragment();
        }

    }


    /**
     * 获取分类
     */
    private void getType(){
        DialogUtil.showProgress(this,"数据加载中");
        HttpMethod.getZzfuType("4",handler);
    }

    /**
     * 生意圈消息提示
     */
    private void getBusinessMsg(){
        HttpMethod.businessMsg(handler);
    }

    private ViewPagerCallBack viewPagerCallBack=new ViewPagerCallBack() {
        public void PageSelected(int position) {
            BusinessActivity.index=position;
        }
    };
}
