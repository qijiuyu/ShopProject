package com.ylkj.shopproject.activity.user.address;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.adapter.user.AddressListAdapter;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Address;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * 收货地址
 */
public class AddressListActivity extends BaseActivity {

    private ListView listView;
    private AddressListAdapter addressListAdapter;
    //地址列表集合
    private List<Address.AddressBean> list=new ArrayList<>();
    //默认，删除的下标
    private int position;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);
        EventBus.getDefault().register(this);
        initView();
        getAddrList();
    }

    /**
     * 初始化
     */
    private void initView(){
        listView=findViewById(R.id.listView);
        //添加新地址
        findViewById(R.id.tv_add).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(AddressListActivity.this,AddAddressActivity.class);
                startActivityForResult(intent,100);
            }
        });
        //返回
        findViewById(R.id.lin_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AddressListActivity.this.finish();
            }
        });
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            BaseBean baseBean;
            switch (msg.what){
                //获取地址列表回执
                case HandlerConstant.GET_ADDR_LIST_SUCCESS:
                     Address address= (Address) msg.obj;
                     list=address.getData();
                     addressListAdapter=new AddressListAdapter(AddressListActivity.this,list);
                     listView.setAdapter(addressListAdapter);
                     break;
                //修改默认收货地址
                case HandlerConstant.SET_ADDR_DEFAULT_SUCCESS:
                      baseBean= (BaseBean) msg.obj;
                      if(null==baseBean){
                          break;
                      }
                      if(baseBean.isSussess()){
                          for(int i=0;i<list.size();i++){
                              if(i==position){
                                  Address.AddressBean addressBean=list.get(i);
                                  addressBean.setIsdefault(1);
                                  list.remove(i);
                                  list.add(0,addressBean);
                              }else{
                                  list.get(i).setIsdefault(0);
                              }
                          }
                          addressListAdapter.notifyDataSetChanged();
                      }
                      ToastUtil.showLong(baseBean.getDesc());
                      break;
                //删除收货地址
                case HandlerConstant.DEL_ADDR_SUCCESS:
                        baseBean= (BaseBean) msg.obj;
                        if(null==baseBean){
                            break;
                        }
                        if(baseBean.isSussess()){
                            for(int i=0;i<list.size();i++){
                                if(i==position){
                                    list.remove(i);
                                    break;
                                }
                            }
                            addressListAdapter.notifyDataSetChanged();
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


    /**
     * EventBus注解
     */
    @Subscribe
    public void onEvent(EventBusType eventBusType){
        switch (eventBusType.getStatus()){
            //设置为默认
            case EventStatus.SET_ADDR_DEFAULT:
                  position= (int) eventBusType.getObject();
                  if(list.get(position).getIsdefault()==0){
                      HttpMethod.setAddrDefault(list.get(position).getId(),handler);
                  }
                  break;
             //删除地址
             case EventStatus.DEL_ADDR:
                  position= (int) eventBusType.getObject();
                  HttpMethod.delAddr(list.get(position).getId(),handler);
                  break;
            //修改地址
            case EventStatus.UPD_ADDR:
                 position= (int) eventBusType.getObject();
                 Intent intent=new Intent(this,AddAddressActivity.class);
                 intent.putExtra("addressBean",list.get(position));
                 startActivityForResult(intent,100);
                 break;
        }
    }


    /**
     * 获取收货地址列表
     */
    private void getAddrList(){
        DialogUtil.showProgress(this,"地址数据加载中");
        HttpMethod.getAddrList(handler);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==100){
            getAddrList();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        removeHandler(handler);
    }
}
