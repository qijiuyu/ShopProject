package com.zxdc.utils.library.Activity.selectphoto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zxdc.utils.library.R;
import com.zxdc.utils.library.base.BaseApplication;
import com.zxdc.utils.library.http.HttpConstant;
import com.zxdc.utils.library.util.selectphoto.Bimp;
import com.zxdc.utils.library.view.TouchImageView;

/**
 * @author Administrator
 */

public class BigPhotoActivity extends Activity {
    private TouchImageView bigImage;
    private int id;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.big_photo);
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
