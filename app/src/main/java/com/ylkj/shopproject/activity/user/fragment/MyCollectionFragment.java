package com.ylkj.shopproject.activity.user.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.user.collection.MyCollectionActivity;
import com.ylkj.shopproject.adapter.user.MyCollectionAdapter;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.zxdc.utils.library.base.BaseFragment;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.Collection;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.util.ArrayList;
import java.util.List;
public class MyCollectionFragment extends BaseFragment {

    private View  view;
    private ListView listView;
    private MyCollectionAdapter myCollectionAdapter;
    //fragment是否可见
    private boolean isVisibleToUser=false;
    //收藏的数据集合
    private List<Collection.DataList> list=new ArrayList<>();
    //商品的id
    private int spuid;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_tuan, container, false);
        listView=view.findViewById(R.id.listView);
        //获取收藏数据
        myCollection();
        return view;
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                //收藏数据回执
                case HandlerConstant.MY_COLLECTION_SUCCESS:
                     Collection collection= (Collection) msg.obj;
                     if(null==collection){
                         break;
                     }
                     if(collection.isSussess()){
                         list=collection.getData();
                         myCollectionAdapter=new MyCollectionAdapter(mActivity,list);
                         listView.setAdapter(myCollectionAdapter);
                     }else{
                         ToastUtil.showLong(collection.getDesc());
                     }
                      break;
               //取消收藏
                case HandlerConstant.COLLECTION_SUCCESS:
                     BaseBean baseBean= (BaseBean) msg.obj;
                     if(null==baseBean){
                         break;
                     }
                     if(baseBean.isSussess()){
                         for (int i=0;i<list.size();i++){
                              if(spuid==list.get(i).getSpuid()){
                                  list.remove(i);
                                  break;
                              }
                         }
                         myCollectionAdapter.notifyDataSetChanged();
                     }
                     ToastUtil.showLong(baseBean.getDesc());
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
            //取消收藏
            case EventStatus.CANCLE_COLLECTION:
                  if(getUserVisibleHint() && null!=view){
                      spuid= (int) eventBusType.getObject();
                      cancleColl();
                  }
                  break;
        }
    }


    /**
     * 获取收藏数据
     */
    private void myCollection(){
        if(isVisibleToUser && null!=view && list.size()==0){
            DialogUtil.showProgress(mActivity,"数据加载中");
            HttpMethod.myCollection(String.valueOf(++MyCollectionActivity.index),handler);
        }
    }


    /**
     * 取消收藏
     */
    private void cancleColl(){
        DialogUtil.showProgress(mActivity,"取消中");
        HttpMethod.collection(spuid,handler);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser=isVisibleToUser;
        //获取收藏数据
        myCollection();
    }

    @Override
    public void onDestroy() {
        removeHandler(handler);
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
