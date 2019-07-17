package com.ylkj.shopproject.activity.main.zzfu;

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
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.main.fragment.ZzfuFragment;
import com.ylkj.shopproject.view.PagerSlidingTabStrip;
import com.ylkj.shopproject.view.ViewPagerCallBack;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.ZzfuType;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;
/**
 * 增值服务分类页
 */
public class ZzfuTypeActivity extends BaseActivity {

    private PagerSlidingTabStrip tabs;
    private DisplayMetrics dm;
    private ViewPager pager;
    //切换fragment的位置
    public static int index;
    //分类对象集合
    public static List<ZzfuType.dataBean> typeList=new ArrayList<>();
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zzfu);
        initView();
        //获取分类
        getType();
    }


    /**
     * 初始化
     */
    private void initView(){
        dm = getResources().getDisplayMetrics();
        pager =  findViewById(R.id.pager);
        tabs =  findViewById(R.id.tabs);
        //返回
        findViewById(R.id.lin_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ZzfuTypeActivity.this.finish();
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
                         typeList=zzfuType.getData();
                         setTabsValue();
                     }else{
                         ToastUtil.showLong(zzfuType.getDesc());
                     }
                     break;
                case HandlerConstant.REQUST_ERROR:
                     ToastUtil.showLong(getString(R.string.net_error));
                     break;
            }
            return false;
        }
    });


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
            return new ZzfuFragment();
        }

    }


    /**
     * 获取分类
     */
    private void getType(){
        DialogUtil.showProgress(this,"数据加载中");
        HttpMethod.getZzfuType("3",handler);
    }

    private ViewPagerCallBack viewPagerCallBack=new ViewPagerCallBack() {
        public void PageSelected(int position) {
            ZzfuTypeActivity.index=position;
        }

    };
}
