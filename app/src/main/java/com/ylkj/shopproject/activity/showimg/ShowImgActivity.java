package com.ylkj.shopproject.activity.showimg;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.util.SPUtil;
import com.zxdc.utils.library.view.TouchImageView;

import java.util.List;

/**
 * 展示图片
 */
public class ShowImgActivity extends BaseActivity {

    private ViewPager viewPager;
    private TextView tvNum,tvTotal;
    private List<String> listImg;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_img);
        getImages();
        initView();
    }


    /**
     * 获取图片数据
     */
    private void getImages(){
        final String imgs=getIntent().getStringExtra("imgs");
        listImg= SPUtil.gson.fromJson(imgs,List.class);
    }


    /**
     * 初始化
     */
    private void initView() {
        tvNum=findViewById(R.id.tv_num);
        tvTotal=findViewById(R.id.tv_total);
        tvTotal.setText(listImg.size()+"");
        viewPager =findViewById(R.id.view_pager);
        //ViewPager相关
        ViewPagerAdater myAdater = new ViewPagerAdater(this);
        viewPager.setAdapter(myAdater);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            public void onPageSelected(int position) {
                tvNum.setText(++position+"");
            }
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    public class ViewPagerAdater extends PagerAdapter {
        private Context context;

        public ViewPagerAdater(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return listImg.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TouchImageView imageView = new TouchImageView(context);
            Glide.with(context).load(listImg.get(position)).into(imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ImageView) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
