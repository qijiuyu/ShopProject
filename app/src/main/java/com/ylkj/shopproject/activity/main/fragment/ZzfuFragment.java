package com.ylkj.shopproject.activity.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.main.zzfu.ZzfuTypeActivity;
import com.ylkj.shopproject.activity.main.zzfu.ZzfwDetailsActivity;
import com.ylkj.shopproject.activity.webview.WebViewActivity;
import com.ylkj.shopproject.adapter.main.AppNewsAdapter;
import com.ylkj.shopproject.adapter.main.ZzfuTypeAdapter;
import com.zxdc.utils.library.base.BaseFragment;
import com.zxdc.utils.library.bean.ZzfuType;
import com.zxdc.utils.library.bean.ZzfuTypeList;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyRefreshLayout;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 增值服务
 */
public class ZzfuFragment extends BaseFragment {

    private View  view;
    private ListView listView;
    private ZzfuTypeAdapter zzfuTypeAdapter;
    //fragment是否可见
    private boolean isVisibleToUser=false;
    //获取的数据集合
    private List<ZzfuTypeList.ListBean> list=new ArrayList<>();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.listview, container, false);
        listView=view.findViewById(R.id.listView);
        //增值服务分类下的列表
        getZzfuList();
        return view;
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                //查询分类列表数据回执
                case HandlerConstant.GET_ZZFU_LIST_SUCCESS:
                     final ZzfuTypeList zzfuTypeList= (ZzfuTypeList) msg.obj;
                     if(null==zzfuTypeList){
                         break;
                     }
                     if(zzfuTypeList.isSussess()){
                         list=zzfuTypeList.getData();
                         zzfuTypeAdapter=new ZzfuTypeAdapter(mActivity,list);
                         listView.setAdapter(zzfuTypeAdapter);
                         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                 ZzfuTypeList.ListBean listBean=list.get(position);
                                 Intent intent=new Intent(mActivity, WebViewActivity.class);
                                 intent.putExtra("type",6);
                                 intent.putExtra("url",String.valueOf(listBean.getId()));
                                 startActivity(intent);
                             }
                         });
                     }else{
                         ToastUtil.showLong(zzfuTypeList.getDesc());
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
     * 增值服务分类下的列表
     */
    private void getZzfuList(){
        if(null!=view && isVisibleToUser && list.size()==0){
            HttpMethod.getZzfuList(ZzfuTypeActivity.typeList.get(ZzfuTypeActivity.index).getId()+"",handler);
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser=isVisibleToUser;
        //增值服务分类下的列表
        getZzfuList();
    }

    @Override
    public void onDestroy() {
        removeHandler(handler);
        super.onDestroy();
    }
}
