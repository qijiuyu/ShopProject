package com.ylkj.shopproject.activity.user.yhq;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.adapter.user.GetYhqAdapter;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.Coupon;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyRefreshLayout;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.util.ArrayList;
import java.util.List;
/**
 * 领券中心
 */
public class GetYhqActivity extends BaseActivity  implements MyRefreshLayoutListener {

    private MyRefreshLayout mRefreshLayout;
    private ListView listView;
    private GetYhqAdapter getYhqAdapter;
    //当前页数
    private int page=1;
    //优惠券数据集合
    private List<Coupon.DataBean> listAll=new ArrayList<>();
    //优惠券ID
    private int couponid;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_yhq);
        //注册eventbus
        EventBus.getDefault().register(this);
        initView();
        getCouponList(HandlerConstant.GET_COUPON_LIST_SUCCESS1);
    }


    /**
     * 初始化
     */
    private void initView(){
        mRefreshLayout=findViewById(R.id.re_list);
        listView=findViewById(R.id.listView);
        //刷新加载
        mRefreshLayout.setMyRefreshLayoutListener(this);
        findViewById(R.id.lin_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                GetYhqActivity.this.finish();
            }
        });
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                //查询数据回执
                case HandlerConstant.GET_COUPON_LIST_SUCCESS1:
                    listAll.clear();
                    mRefreshLayout.refreshComplete();
                    refresh((Coupon) msg.obj);
                    break;
                case HandlerConstant.GET_COUPON_LIST_SUCCESS2:
                    mRefreshLayout.loadMoreComplete();
                    refresh((Coupon) msg.obj);
                    break;
                //领取优惠券
                case HandlerConstant.TAKE_COUPON_SUCCESS:
                      final BaseBean baseBean= (BaseBean) msg.obj;
                      if(null==baseBean){
                          break;
                      }
                      if(baseBean.isSussess()){
                          for (int i=0;i<listAll.size();i++){
                               if(couponid==listAll.get(i).getId()){
                                   listAll.remove(i);
                                   break;
                               }
                          }
                          getYhqAdapter.notifyDataSetChanged();
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
     * 刷新界面数据
     */
    private void refresh(Coupon coupon){
        if(null==coupon){
            return;
        }
        if(coupon.isSussess()){
            List<Coupon.DataBean> list=coupon.getData();
            listAll.addAll(list);
            if(null==getYhqAdapter){
                getYhqAdapter=new GetYhqAdapter(this,listAll);
                listView.setAdapter(getYhqAdapter);
            }else{
                getYhqAdapter.notifyDataSetChanged();
            }
            if(list.size()<20){
                mRefreshLayout.setIsLoadingMoreEnabled(false);
            }
        }else{
            ToastUtil.showLong(coupon.getDesc());
        }
    }


    /**
     * EventBus注解
     */
    @Subscribe
    public void onEvent(EventBusType eventBusType){
        switch (eventBusType.getStatus()){
            //领取优惠券
            case EventStatus.TAKE_COUPON:
                 couponid= (int) eventBusType.getObject();
                 takeCoupon();
                 break;
        }
    }


    @Override
    public void onRefresh(View view) {
        page=1;
        getCouponList(HandlerConstant.GET_COUPON_LIST_SUCCESS1);
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        getCouponList(HandlerConstant.GET_COUPON_LIST_SUCCESS2);
    }


    /**
     * 获取优惠券列表
     */
    private void getCouponList(int index){
        HttpMethod.getCouponList(null,page,index,handler);
    }


    /**
     * 领取优惠券
     */
    private void takeCoupon(){
        DialogUtil.showProgress(this,"领取中");
        HttpMethod.takeCoupon(couponid,handler);
    }

    @Override
    protected void onDestroy() {
        removeHandler(handler);
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
