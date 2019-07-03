package com.ylkj.shopproject.activity.selectphoto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.selectphoto.bean.Bimp;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.base.BaseApplication;
import com.zxdc.utils.library.http.HttpConstant;
import com.zxdc.utils.library.view.TouchImageView;

/**
 * @author Administrator
 */

public class BigPhotoActivity extends BaseActivity {
    private TouchImageView bigImage;
    private int id;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_img);
        Intent intent = getIntent();
        id = intent.getIntExtra("ID", 0);
        bigImage = (TouchImageView) findViewById(R.id.img_big_photo);
        for (int i = 0; i < Bimp.selectBitmap.size(); i++) {
            final String imgPath = Bimp.selectBitmap.get(i).getImagePath();
            if (id == i) {
                if (imgPath.indexOf(HttpConstant.IP) == -1) {
                        Glide.with(BaseApplication.getContext()).load("file://" + imgPath).diskCacheStrategy(DiskCacheStrategy.NONE).into(bigImage);
                } else {
                    Glide.with(BaseApplication.getContext()).load(imgPath).diskCacheStrategy(DiskCacheStrategy.NONE).into(bigImage);
                }
            }
        }
    }
}
