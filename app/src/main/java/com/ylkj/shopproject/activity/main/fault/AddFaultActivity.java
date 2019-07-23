package com.ylkj.shopproject.activity.main.fault;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.main.persenter.AddFaultPersenter;
import com.ylkj.shopproject.activity.selectphoto.bean.Bimp;
import com.ylkj.shopproject.activity.selectphoto.bean.ImageItem;
import com.ylkj.shopproject.activity.user.address.AddressListActivity;
import com.ylkj.shopproject.adapter.selectphoto.GridImageAdapter;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.ylkj.shopproject.util.PicturesUtil;
import com.ylkj.shopproject.util.SelectPhoto;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.AddrBean;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.BitMapUtil;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyGridView;
import com.zxdc.utils.library.view.OvalImage2Views;
import com.zxdc.utils.library.view.time.TimePickerView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 在线报修
 */
public class AddFaultActivity extends BaseActivity implements View.OnClickListener{

    private TextView tvUserName,tvMobile,tvAddress,tvJxName,tvTime;
    private EditText etContent;
    private OvalImage2Views imgFault1,imgFault2;
    private MyGridView gridView;
    private GridImageAdapter adapter;
    private AddFaultPersenter addFaultPersenter;
    //选择的图片类型 0：品牌  1：机场照片
    private int imgType;
    /**
     * mpFile：名牌剪裁的图片
     * jcFile：机床剪裁的图片
     */
    private File mpFile,jcFile;
    //时间选择器
    private TimePickerView timePickerView;
    //收货地址对象
    private AddrBean addrBean;
    private List<File> fileList=new ArrayList<>();
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fault);
        //注册EventBus
        EventBus.getDefault().register(this);
        addFaultPersenter=new AddFaultPersenter(this);
        initView();
        //获取收货地址列表
        addFaultPersenter.getOrderAddr();
    }


    /**
     * 初始化
     */
    private void initView(){
        tvUserName=findViewById(R.id.tv_userName);
        tvMobile=findViewById(R.id.tv_mobile);
        tvAddress=findViewById(R.id.tv_address);
        tvJxName=findViewById(R.id.tv_jx_name);
        tvTime=findViewById(R.id.tv_time);
        etContent=findViewById(R.id.et_content);
        imgFault1=findViewById(R.id.img_fault);
        imgFault2=findViewById(R.id.img_fault2);
        gridView=findViewById(R.id.gv_other);
        imgFault1.setOnClickListener(this);
        imgFault2.setOnClickListener(this);
        tvJxName.setOnClickListener(this);
        tvTime.setOnClickListener(this);
        findViewById(R.id.rel_select_addr).setOnClickListener(this);
        findViewById(R.id.rel_addr).setOnClickListener(this);
        findViewById(R.id.tv_add_jx).setOnClickListener(this);
        findViewById(R.id.tv_confirm).setOnClickListener(this);
        findViewById(R.id.lin_back).setOnClickListener(this);

        //清空图片集合
        Bimp.selectBitmap.clear();
        adapter = new GridImageAdapter(getApplicationContext(), Bimp.selectBitmap);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if (arg2 == Bimp.selectBitmap.size()) {
                    PicturesUtil.selectPhoto(AddFaultActivity.this);
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()){
            //选择地址
            case R.id.rel_addr:
            case R.id.rel_select_addr:
                intent.setClass(this,AddressListActivity.class);
                intent.putExtra("type",1);
                startActivityForResult(intent,100);
                break;
            //选择机型名称
            case R.id.tv_jx_name:
                 intent.setClass(this,SelectJCActivity.class);
                 startActivityForResult(intent,200);
                 break;
            //添加机型名称
            case R.id.tv_add_jx:
                 intent.setClass(this,EditJCActivity.class);
                 startActivityForResult(intent,200);
                 break;
            //选择出厂日期
            case R.id.tv_time:
                 //初始化时间选择器
                 addFaultPersenter.initCustomTimePicker(tvTime);
                 addFaultPersenter.showTime(tvTime);
                 break;
             //选择名牌照片
            case R.id.img_fault:
                 imgType=0;
                 SelectPhoto.selectPhoto(this);
                 break;
            //选择机床照片
            case R.id.img_fault2:
                  imgType=1;
                  SelectPhoto.selectPhoto(this);
                 break;
            //提交
            case R.id.tv_confirm:
                  String jxName=tvJxName.getText().toString().trim();
                  String time=tvTime.getText().toString().trim();
                  String content=etContent.getText().toString().trim();
                  if(addrBean==null){
                      ToastUtil.showLong("请选择地址");
                      return;
                  }
                  if(TextUtils.isEmpty(jxName)){
                      ToastUtil.showLong("请选择机型名称");
                      return;
                  }
                  if(TextUtils.isEmpty(time)){
                      ToastUtil.showLong("请选择出厂日期");
                      return;
                  }
                if(TextUtils.isEmpty(content)){
                    ToastUtil.showLong("请输入故障信息");
                    return;
                }
                if(null==mpFile){
                    ToastUtil.showLong("请选择名牌照片");
                    return;
                }
                if(null==jcFile){
                    ToastUtil.showLong("请选择机床照片");
                    return;
                }
                addFaultPersenter.setContent(String.valueOf(addrBean.getId()),jxName,time,content);
                addFaultPersenter.uploadImg(mpFile,jcFile);
                 break;
            case R.id.lin_back:
                 finish();
                 break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //展示收货地址信息
            case 100:
                if(null==data){
                    return;
                }
                addrBean= (AddrBean) data.getSerializableExtra("addrBean");
                showAddress();
                break;
            //返回自定义的机型名称
            case 200:
                if(null==data){
                    return;
                }
                 final String name=data.getStringExtra("name");
                 tvJxName.setText(name);
                 break;
            //返回拍照图片
            case SelectPhoto.CODE_CAMERA_REQUEST:
                if (resultCode == RESULT_OK) {
                    File tempFile = new File(SelectPhoto.pai);
                    if (tempFile.isFile()) {
                        SelectPhoto.cropRawPhoto(Uri.fromFile(tempFile),this);
                    }
                }
                break;
            //返回相册选择图片
            case SelectPhoto.CODE_GALLERY_REQUEST:
                if (data != null) {
                    SelectPhoto.cropRawPhoto(data.getData(),this);
                }
                break;
            //返回裁剪的图片
            case SelectPhoto.CODE_RESULT_REQUEST:
                 if(imgType==0){
                     mpFile= new File(SelectPhoto.crop);
                     Glide.with(AddFaultActivity.this).load(Uri.fromFile(mpFile)).into(imgFault1);
                 }else{
                     jcFile= new File(SelectPhoto.crop);
                     Glide.with(AddFaultActivity.this).load(Uri.fromFile(jcFile)).into(imgFault2);
                 }
                break;
            //返回拍照图片
            case PicturesUtil.CODE_CAMERA_REQUEST:
                 if (resultCode == RESULT_OK) {
                      File file = new File(PicturesUtil.pai);
                      if(file.isFile()){
                        ImageItem takePhoto = new ImageItem();
                        takePhoto.setImagePath(file.getPath());
                        Bimp.selectBitmap.add(takePhoto);
                        Bimp.imgList.add(takePhoto);
                        adapter = new GridImageAdapter(getApplicationContext(), Bimp.selectBitmap);
                        gridView.setAdapter(adapter);
                    }
                }
                break;
            //返回相册选择图片
            case PicturesUtil.CODE_GALLERY_REQUEST:
                 adapter = new GridImageAdapter(getApplicationContext(), Bimp.selectBitmap);
                 gridView.setAdapter(adapter);
                 break;
            default:
                break;

        }
    }


    /**
     * EventBus注解
     */
    @Subscribe
    public void onEvent(EventBusType eventBusType) {
        switch (eventBusType.getStatus()) {
            //查询收货地址
            case EventStatus.GET_MY_ADDRESS:
                addrBean= (AddrBean) eventBusType.getObject();
                //展示收货地址信息
                showAddress();
                break;
        }
    }


    /**
     * 展示收货地址
     */
    private void showAddress(){
        if(null==addrBean){
            return;
        }
        findViewById(R.id.rel_select_addr).setVisibility(View.GONE);
        findViewById(R.id.rel_addr).setVisibility(View.VISIBLE);
        tvUserName.setText(addrBean.getName());
        tvMobile.setText(addrBean.getMobile());
        tvAddress.setText(addrBean.getAddress());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
