package com.ylkj.shopproject.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.selectphoto.ImageGridActivity;
import com.ylkj.shopproject.activity.selectphoto.bean.Bimp;
import com.zxdc.utils.library.util.FileUtils;
import java.io.File;

/**
 * 选择图片工具类
 * Created by Administrator on 2017/12/29 0029.
 */

public class PicturesUtil {

    /* 请求识别码 */
    public static final int CODE_GALLERY_REQUEST = 0xa0;
    public static final int CODE_CAMERA_REQUEST = 0xa1;
    public static String pai = FileUtils.getSdcardPath();

    /**
     * @param context
     * 初始化数据
     */
    public static  void selectPhoto(final Activity context){
        View view_choise = LayoutInflater.from(context).inflate(R.layout.select_photo_dialog, null);
        final Dialog dialog  = new Dialog(context, R.style.ActionSheetDialogStyle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setTitle(null);
        dialog.setCancelable(true);
        dialog.setContentView(view_choise);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);  //此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.mystyle);  //添加动画
        dialog.show();
        //拍照
        view_choise.findViewById(R.id.textview_choseimg_camera).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
                pai=pai+System.currentTimeMillis()+".jpg";
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(pai)));
                context.startActivityForResult(intent, CODE_CAMERA_REQUEST);
            }
        });
        //本地相册选择
        view_choise.findViewById(R.id.textview_choseimg_gallery).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent=new Intent(context, ImageGridActivity.class);
                context.startActivityForResult(intent,CODE_GALLERY_REQUEST );
                Bimp.imgList.clear();
            }
        });
    }
}
