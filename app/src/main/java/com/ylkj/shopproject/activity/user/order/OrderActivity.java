package com.ylkj.shopproject.activity.user.order;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.user.fragment.MyYhqFragment;
import com.ylkj.shopproject.activity.user.fragment.order.AllOrderFragment;
import com.ylkj.shopproject.activity.user.fragment.order.DFHfragment;
import com.ylkj.shopproject.activity.user.fragment.order.DFKfragment;
import com.ylkj.shopproject.activity.user.fragment.order.DSHfragment;
import com.ylkj.shopproject.activity.user.fragment.order.YQXfragment;
import com.ylkj.shopproject.activity.user.fragment.order.YWCfragment;
import com.ylkj.shopproject.activity.user.persenter.OrderPersenter;
import com.ylkj.shopproject.view.PagerSlidingTabStrip;
import com.ylkj.shopproject.view.ViewPagerCallBack;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.MyOrder;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;

/**
 * 我的团购
 */
public class OrderActivity extends BaseActivity {

    private PagerSlidingTabStrip tabs;
    private DisplayMetrics dm;
    //切换fragment的位置
    public static int index;
    private ViewPager pager;
    private AllOrderFragment allOrderFragment=new AllOrderFragment();//全部订单
    private DFKfragment dfKfragment=new DFKfragment();//待付款订单
    private DFHfragment dfHfragment=new DFHfragment();//待发货订单
    private DSHfragment dsHfragment=new DSHfragment();//待收货订单
    private YWCfragment ywCfragment=new YWCfragment();//已完成订单
    private YQXfragment yqXfragment=new YQXfragment();//已取消订单
    //MVP
    private OrderPersenter orderPersenter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        orderPersenter=new OrderPersenter(this);
        initView();
        setIntent();
    }


    /**
     * 初始化
     */
    private void initView(){
        allOrderFragment.setPersenter(orderPersenter);
        dfKfragment.setPersenter(orderPersenter);
        dfHfragment.setPersenter(orderPersenter);
        dsHfragment.setPersenter(orderPersenter);
        ywCfragment.setPersenter(orderPersenter);
        yqXfragment.setPersenter(orderPersenter);
        dm = getResources().getDisplayMetrics();
        pager =  findViewById(R.id.pager);
        tabs =  findViewById(R.id.tabs);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        pager.setOffscreenPageLimit(6);
        tabs.setViewPagerCallBack(viewPagerCallBack);
        tabs.setViewPager(pager);
        setTabsValue();
    }


    /**
     * 对PagerSlidingTabStrip的各项属性进行赋值。
     */
    private void setTabsValue() {
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


    /**
     * 设置跳转
     */
    private void setIntent(){
        final int type=getIntent().getIntExtra("type",-1);
        if(type!=-1){
            index=type;
            pager.setCurrentItem(type);
        }
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        private final String[] titles = { "全部","待付款","待发货","待收货","已完成","已取消"};

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                     return allOrderFragment;
                case 1:
                    return dfKfragment;
                case 2:
                    return dfHfragment;
                case 3:
                    return dsHfragment;
                case 4:
                    return ywCfragment;
                default:
                    return yqXfragment;
            }
        }

    }


    private ViewPagerCallBack viewPagerCallBack=new ViewPagerCallBack() {
        public void PageSelected(int position) {
            OrderActivity.index=position;
        }

    };
}
