package com.ylkj.shopproject.activity.user.address;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.city.ProvinceActivity;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.AddrBean;
import com.zxdc.utils.library.bean.Address;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * 添加收货地址
 */
public class AddAddressActivity extends BaseActivity implements View.OnClickListener{

    private EditText etName,etMobile,etAddress,etCompany;
    private TextView tvRegion;
    private ImageView imgType;
    //是否设置默认，1   0不是
    private int isDefalt=0;
    //存储省市区号和名称
    private String[] strCity;
    //修改的地址对象
    private AddrBean addressBean;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        EventBus.getDefault().register(this);
        initView();
        //展示要修改的数据
        showData();
    }


    /**
     * 初始化
     */
    private void initView(){
        etName=findViewById(R.id.et_name);
        etMobile=findViewById(R.id.et_mobile);
        tvRegion=findViewById(R.id.tv_region);
        etAddress=findViewById(R.id.et_address);
        etCompany=findViewById(R.id.et_company);
        imgType=findViewById(R.id.img_type);
        imgType.setOnClickListener(this);
        findViewById(R.id.tv_select).setOnClickListener(this);
        findViewById(R.id.tv_save).setOnClickListener(this);
        findViewById(R.id.lin_back).setOnClickListener(this);
    }


    /**
     * 展示要修改的数据
     */
    private void showData(){
        addressBean= (AddrBean) getIntent().getSerializableExtra("addressBean");
        if(null==addressBean){
            return;
        }
        etName.setText(addressBean.getName());
        etMobile.setText(addressBean.getMobile());
        tvRegion.setText(addressBean.getProvincename()+","+addressBean.getCityname()+","+addressBean.getAreaname());
        strCity=new String[]{addressBean.getProvincecode(),addressBean.getCitycode(),addressBean.getAreacode(),addressBean.getProvincename(),addressBean.getCityname(),addressBean.getAreaname()};
        etAddress.setText(addressBean.getAddress());
        etCompany.setText(addressBean.getCompanyname());
        if(addressBean.getIsdefault()==1){
            isDefalt=1;
            imgType.setImageDrawable(getResources().getDrawable(R.mipmap.addr_open_type));
        }else{
            isDefalt=0;
            imgType.setImageDrawable(getResources().getDrawable(R.mipmap.addr_close_type));
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //选择地区
            case R.id.tv_select:
                 Intent intent=new Intent(this,ProvinceActivity.class);
                 startActivityForResult(intent,1);
                 break;
            //选择是否默认
            case R.id.img_type:
                 if(isDefalt==1){
                     isDefalt=0;
                     imgType.setImageDrawable(getResources().getDrawable(R.mipmap.addr_close_type));
                 }else{
                     isDefalt=1;
                     imgType.setImageDrawable(getResources().getDrawable(R.mipmap.addr_open_type));
                 }
                 break;
            //保存
            case R.id.tv_save:
                 final String name=etName.getText().toString().trim();
                 final String mobile=etMobile.getText().toString().trim();
                 final String region=tvRegion.getText().toString().trim();
                 final String address=etAddress.getText().toString().trim();
                 final String company=etCompany.getText().toString().trim();
                 if(TextUtils.isEmpty(name)){
                     ToastUtil.showLong("请输入收货人姓名！");
                     return;
                 }
                if(TextUtils.isEmpty(mobile)){
                    ToastUtil.showLong("请输入手机号码！");
                    return;
                }
                if(TextUtils.isEmpty(region)){
                    ToastUtil.showLong("请选择所在地区！");
                    return;
                }
                if(TextUtils.isEmpty(address)){
                    ToastUtil.showLong("请输入详细地址！");
                    return;
                }
                if(TextUtils.isEmpty(company)){
                    ToastUtil.showLong("请输入公司名称！");
                    return;
                }
                if(null==addressBean){
                    DialogUtil.showProgress(this,"添加地址中");
                    HttpMethod.addAddress(strCity[3],strCity[4],strCity[5],name,strCity[0],strCity[1],strCity[2],address,mobile,company,isDefalt,handler);
                }else{
                    DialogUtil.showProgress(this,"修改地址中");
                    HttpMethod.updAddr(strCity[3],strCity[4],strCity[5],addressBean.getId(),name,strCity[0],strCity[1],strCity[2],address,mobile,company,isDefalt,handler);
                }
                 break;
            case R.id.lin_back:
                 finish();
                 break;
        }
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                //添加地址回执
                case HandlerConstant.ADD_ADDRESS_SUCCESS:
                     final BaseBean baseBean= (BaseBean) msg.obj;
                     if(null==baseBean){
                         break;
                     }
                     if(baseBean.isSussess()){
                         Intent intent=new Intent();
                         setResult(100,intent);
                         finish();
                     }else{
                         ToastUtil.showLong(baseBean.getDesc());
                     }
                     break;
                case HandlerConstant.REQUST_ERROR:
                     ToastUtil.showLong(getString(R.string.net_error));
                     break;
            }
            return false;
        }
    });


    /**
     * EventBus注解
     */
    @Subscribe
    public void onEvent(EventBusType eventBusType){
        if(eventBusType.getStatus()== EventStatus.GET_CITY){
            final String city=(String)eventBusType.getObject();
            if(!TextUtils.isEmpty(city)){
                strCity=city.split(",");
                tvRegion.setText(strCity[3]+","+strCity[4]+","+strCity[5]);
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeHandler(handler);
        EventBus.getDefault().unregister(this);
    }
}
