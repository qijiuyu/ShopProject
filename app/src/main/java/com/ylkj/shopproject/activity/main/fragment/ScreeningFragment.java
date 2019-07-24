package com.ylkj.shopproject.activity.main.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.main.pjsc.PeiJianListActivity;
import com.ylkj.shopproject.adapter.main.ScreeningTypeAdapter;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.zxdc.utils.library.base.BaseFragment;
import com.zxdc.utils.library.bean.Secreening;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MeasureListView;
import com.zxdc.utils.library.view.MyGridView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 筛选
 */
public class ScreeningFragment extends BaseFragment{

    private View view;
    private MeasureListView listView;
    private Secreening secreening;
    private ScreeningTypeAdapter screeningTypeAdapter;
    //存储选中的名称
    public static Map<Integer,String> nameMap=new HashMap<>();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nameMap.clear();
        EventBus.getDefault().register(this);
    }


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_screening, container, false);
        listView=view.findViewById(R.id.listView);
        //重置
        view.findViewById(R.id.tv_resume).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nameMap.clear();
                if(null==secreening){
                    return;
                }
                if(secreening.isSussess()){
                    screeningTypeAdapter=new ScreeningTypeAdapter(mActivity,secreening.getData());
                    listView.setAdapter(screeningTypeAdapter);
                }
            }
        });

        //确定
        view.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(nameMap.size()==0){
                    return;
                }
                Set<Integer> set = nameMap.keySet();
                StringBuffer stringBuffer=new StringBuffer();
                for (Integer in : set){
                    stringBuffer.append(nameMap.get(in)+",");
                }
                EventBus.getDefault().post(new EventBusType(EventStatus.SCREENING_DATA,stringBuffer.substring(0,stringBuffer.toString().length()-1)));
            }
        });
        return view;
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                case HandlerConstant.GET_SECREENING_SUCCESS:
                      secreening= (Secreening) msg.obj;
                      if(null==secreening){
                          break;
                      }
                      if(secreening.isSussess()){
                          screeningTypeAdapter=new ScreeningTypeAdapter(mActivity,secreening.getData());
                          listView.setAdapter(screeningTypeAdapter);
                      }else{
                          ToastUtil.showLong(secreening.getDesc());
                      }
                      break;
                case HandlerConstant.REQUST_ERROR:
                    ToastUtil.showLong(mActivity.getString(R.string.net_error));
                    break;
            }
            return false;
        }
    });



    /**
     * 获取配件商品筛选值列表接口
     */
    private void Secreening(int typeId){
        HttpMethod.Secreening(typeId,handler);
    }

    /**
     * EventBus注解
     */
    @Subscribe
    public void onEvent(EventBusType eventBusType){
        if(eventBusType.getStatus()== EventStatus.OPEN_SCREENING){
            //获取配件商品筛选值列表接口
            Secreening((Integer) eventBusType.getObject());
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
