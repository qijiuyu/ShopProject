package com.ylkj.shopproject.activity.business;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.selectphoto.bean.Bimp;
import com.ylkj.shopproject.activity.selectphoto.bean.ImageItem;
import com.ylkj.shopproject.adapter.business.BusinessTypeAdapter;
import com.ylkj.shopproject.adapter.selectphoto.GridImageAdapter;
import com.ylkj.shopproject.util.GetLocation;
import com.ylkj.shopproject.util.PicturesUtil;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.UploadImg;
import com.zxdc.utils.library.bean.ZzfuType;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.BitMapUtil;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.SPUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyGridView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
/**
 * 发布生意圈
 */
public class AddBusinessActivity extends BaseActivity implements View.OnClickListener{

    private TextView tvType;
    private EditText etContent;
    private MyGridView gridView;
    private TextView tvAddress;
    private GridImageAdapter adapter;
    //分类对象集合
    public static List<ZzfuType.dataBean> typeList;
    private PopupWindow popupWindow;
    //分类的adapter
    private BusinessTypeAdapter businessTypeAdapter;
    //分类id
    private int typeId;
    //当前的经纬度
    private LatLng latLng;
    //压缩后的图片文件
    private List<File> listFile=new ArrayList<>();
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_business);
        initView();
    }

    /**
     * 初始化
     */
    private void initView(){
        typeList= (List<ZzfuType.dataBean>) getIntent().getSerializableExtra("typeList");
        tvType=findViewById(R.id.tv_type);
        etContent=findViewById(R.id.et_content);
        gridView=findViewById(R.id.gridview);
        tvAddress=findViewById(R.id.tv_address);
        //清空图片集合
        Bimp.selectBitmap.clear();
        adapter = new GridImageAdapter(getApplicationContext(), Bimp.selectBitmap);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if (arg2 == Bimp.selectBitmap.size()) {
                    PicturesUtil.selectPhoto(AddBusinessActivity.this);
                }
            }
        });

        findViewById(R.id.rel_type).setOnClickListener(this);
        findViewById(R.id.tv_cancle).setOnClickListener(this);
        findViewById(R.id.tv_send).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //显示分类
            case R.id.rel_type:
                  showType();
                  break;
            case R.id.tv_send:
                 final String content=etContent.getText().toString().trim();
                 if(typeId==0){
                     ToastUtil.showLong("请选择分类！");
                     return;
                 }
                 if(TextUtils.isEmpty(content)){
                     ToastUtil.showLong("请输入您要发布的动态内容！");
                     return;
                 }
                 if(Bimp.selectBitmap.size()==0){
                     ToastUtil.showLong("请选择您要发布的照片！");
                     return;
                 }
                 uploadImg();
                 break;
            case R.id.tv_cancle:
                 finish();
                 break;
        }
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                //图片上传回执
                case HandlerConstant.UPLOAD_IMG_SUCCESS:
                      final UploadImg uploadImg= (UploadImg) msg.obj;
                      sendContent(uploadImg);
                      break;
                //发布回执
                case HandlerConstant.ADD_BUSINESS_SUCCESS:
                      DialogUtil.closeProgress();
                      BaseBean baseBean= (BaseBean) msg.obj;
                      if(baseBean==null){
                          break;
                      }
                      if(baseBean.isSussess()){

                      }
                      ToastUtil.showLong(baseBean.getDesc());
                      break;
                case HandlerConstant.REQUST_ERROR:
                    ToastUtil.showLong(getString(R.string.net_error));
                    break;
            }
            return false;
        }
    });


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //返回拍照图片
            case PicturesUtil.CODE_CAMERA_REQUEST:
                if (resultCode == RESULT_OK) {
                    File file = new File(PicturesUtil.pai);
                    if(file.isFile()){
                        ImageItem takePhoto = new ImageItem();
                        takePhoto.setImagePath(file.getPath());
                        Bimp.selectBitmap.add(takePhoto);
                        Bimp.imgList.add(takePhoto);
                        adapter.notifyDataSetChanged();
                    }
                }
                break;
            //返回相册选择图片
            case PicturesUtil.CODE_GALLERY_REQUEST:
                adapter.notifyDataSetChanged();
                break;
            default:
                break;

        }
    }


    /**
     * 上传图片
     */
    private void uploadImg(){
        DialogUtil.showProgress(this,"数据加载中");
        handler.postDelayed(new Runnable() {
            public void run() {
                for (int i=0;i<Bimp.selectBitmap.size();i++){
                    final File file=new File(Bimp.selectBitmap.get(i).getImagePath());
                    if(!file.isFile()){
                        return;
                    }
                    final String newPath= BitMapUtil.compressBitMap(file);
                    final File file1=new File(newPath);
                    if(file1.isFile()){
                        listFile.add(file1);
                    }
                }
                LogUtils.e(listFile.size()+"+++++++++++++++++++++");
                HttpMethod.uploadImg(1,listFile,handler);
            }
        },100);
    }


    /**
     * 上传所有内容
     */
    private void sendContent(UploadImg uploadImg){
        if(null==uploadImg){
            return;
        }
        if(uploadImg.isSussess()){
            String[] imgs=uploadImg.getData();
            StringBuffer stringBuffer=new StringBuffer();
            for (int i=0;i<imgs.length;i++){
                  stringBuffer.append(imgs[i]+",");
            }
            final String content=etContent.getText().toString().trim();
            final String imgPath=stringBuffer.substring(0,stringBuffer.length()-1);
            final String address=tvAddress.getText().toString().trim();
            HttpMethod.addBusiness(typeId,content,imgPath,address,String.valueOf(latLng.latitude),String.valueOf(latLng.longitude),handler);

        }else{
            ToastUtil.showLong(uploadImg.getDesc());
            DialogUtil.closeProgress();
        }
    }


    /**
     * 展示默认选择的dialog
     */
    public void showType(){
        if(null!=popupWindow && popupWindow.isShowing()){
            popupWindow.dismiss();
            return;
        }
        RelativeLayout rel=findViewById(R.id.rel_type);
        final View view= LayoutInflater.from(activity).inflate(R.layout.dialog_business_type,null);
        popupWindow= DialogUtil.showBottom(view,rel);
        ListView listView=view.findViewById(R.id.listView);
        businessTypeAdapter=new BusinessTypeAdapter(this,typeList);
        listView.setAdapter(businessTypeAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popupWindow.dismiss();
                typeId=typeList.get(position).getId();
                tvType.setText(typeList.get(position).getName());
            }
        });

    }


    /**
     * 反地理编码
     */
    protected void reverseGeoCode(LatLng latLng) {
        // 创建地理编码检索实例
        GeoCoder geoCoder = GeoCoder.newInstance();
        OnGetGeoCoderResultListener listener = new OnGetGeoCoderResultListener() {
            // 反地理编码查询结果回调函数
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                if (result != null) {
                    tvAddress.setText(result.getAddress());
                }
            }

            // 地理编码查询结果回调函数
            public void onGetGeoCodeResult(GeoCodeResult result) {
            }
        };
        // 设置地理编码检索监听者
        geoCoder.setOnGetGeoCodeResultListener(listener);
        geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
        // 释放地理编码检索实例
        geoCoder.destroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        latLng= (LatLng) SPUtil.getInstance(this).getObject(SPUtil.LATLNG,LatLng.class);
        if(null!=latLng){
            reverseGeoCode(latLng);
        }
    }
}
