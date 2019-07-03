package com.ylkj.shopproject.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * 图片加载监听
 */
public class MyImgLoader extends ImageLoader {
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load((String) path).into(imageView);
    }
}
