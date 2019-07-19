package com.ylkj.shopproject.activity.business;

import android.graphics.Color;
import android.os.Bundle;
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
import com.ylkj.shopproject.activity.business.persenter.BusinessPersenter;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.ylkj.shopproject.view.PagerSlidingTabStrip;
import com.ylkj.shopproject.view.ViewPagerCallBack;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.BusinessMsg;
import com.zxdc.utils.library.bean.ZzfuType;
import com.zxdc.utils.library.view.CircleImageView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
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
    private BusinessPersenter businessPersenter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        businessPersenter=new BusinessPersenter(this);
        EventBus.getDefault().register(this);
        initView();
        //获取分类
        businessPersenter.getType();
        //生意圈消息提示
        businessPersenter.getBusinessMsg();
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
                businessPersenter.getCompany();
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
     * EventBus注解
     */
    @Subscribe
    public void onEvent(EventBusType eventBusType){
        switch (eventBusType.getStatus()){
            //获取生意圈分类
            case EventStatus.BUSINESS_TYPE:
                  List<ZzfuType.dataBean> list= (List<ZzfuType.dataBean>) eventBusType.getObject();
                  typeList.addAll(list);
                  setTabsValue();
                  break;
            //生意圈消息获取
            case EventStatus.BUSINESS_NEW:
                  BusinessMsg.DataBean dataBean= (BusinessMsg.DataBean) eventBusType.getObject();
                  findViewById(R.id.rel_trip).setVisibility(View.VISIBLE);
                  tvNum.setText(dataBean.getNum()+"条消息");
                  Glide.with(BusinessActivity.this).load(dataBean.getImgurl()).override(20,20).centerCrop().error(R.mipmap.default_icon).into(imgPic);
                  break;
        }
    }


    private ViewPagerCallBack viewPagerCallBack=new ViewPagerCallBack() {
        public void PageSelected(int position) {
            BusinessActivity.index=position;
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
