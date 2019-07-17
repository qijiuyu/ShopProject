package com.ylkj.shopproject.activity.type;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.adapter.type.TypeDetailsAdapter;
import com.ylkj.shopproject.util.MyImgLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Type;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MeasureListView;
import java.util.ArrayList;
import java.util.List;
/**
 * 二级分类
 */
public class TypeDetailsActivity extends BaseActivity {

    private Banner banner;
    private MeasureListView listView;
    private List<String> imgList=new ArrayList<>();
    private TypeDetailsAdapter typeDetailsAdapter;
    //子分类列表对象
    private List<Type.TypeBean> list;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_details);
        initView();
        setBanner();
    }


    /**
     * 初始化
     */
    private void initView(){
        list= (List<Type.TypeBean>) getIntent().getSerializableExtra("children");
        banner=findViewById(R.id.banner);
        listView=findViewById(R.id.listView);
        typeDetailsAdapter=new TypeDetailsAdapter(this,list);
        listView.setAdapter(typeDetailsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Type.TypeBean typeBean=list.get(position);
                Intent intent=new Intent(TypeDetailsActivity.this,TypeListActivity.class);
                intent.putExtra("typeBean",typeBean);
                startActivity(intent);
            }
        });

        findViewById(R.id.lin_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TypeDetailsActivity.this.finish();
            }
        });

        imgList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        imgList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        imgList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
        imgList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");
    }


    private void setBanner(){
        //设置样式，里面有很多种样式可以自己都看看效果
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置轮播的动画效果,里面有很多种特效,可以都看看效果。
        banner.setBannerAnimation(Transformer.ZoomOutSlide);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyImgLoader());
        //设置图片集合
        banner.setImages(imgList);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是true
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，居中显示
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.setOnBannerListener(new OnBannerListener() {
            public void OnBannerClick(int position) {
                ToastUtil.showLong(position+"");
            }
        });
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

}
