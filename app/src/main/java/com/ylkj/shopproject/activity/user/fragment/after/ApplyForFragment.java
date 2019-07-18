package com.ylkj.shopproject.activity.user.fragment.after;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.adapter.user.ApplyForAdapter;
import com.zxdc.utils.library.base.BaseFragment;
import com.zxdc.utils.library.bean.After;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyRefreshLayout;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;
import java.util.ArrayList;
import java.util.List;
/**
 * 售后申请
 */
public class ApplyForFragment extends BaseFragment  implements MyRefreshLayoutListener {

    private View view;
    private MyRefreshLayout mRefreshLayout;
    private ListView listView;
    private ApplyForAdapter applyForAdapter;
    //fragment是否可见
    private boolean isVisibleToUser = false;
    //当前页数
    private int page = 1;
    //数据集合
    private List<After.DataBean> listAll=new ArrayList<>();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.listview, container, false);
        listView = view.findViewById(R.id.listView);
        mRefreshLayout = view.findViewById(R.id.re_list);
        //刷新加载
        mRefreshLayout.setMyRefreshLayoutListener(this);
        //获取售后申请
        getAfterOrder(HandlerConstant.GET_AFTER_ORDER_SUCCESS1);
        return view;
    }


    private Handler handler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what) {
                //查询数据回执
                case HandlerConstant.GET_AFTER_ORDER_SUCCESS1:
                    listAll.clear();
                    mRefreshLayout.refreshComplete();
                    refresh((After) msg.obj);
                    break;
                case HandlerConstant.GET_AFTER_ORDER_SUCCESS2:
                    mRefreshLayout.loadMoreComplete();
                    refresh((After) msg.obj);
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
    private void refresh(After after) {
        if (null == after) {
            return;
        }
        if (after.isSussess()) {
            List<After.DataBean> list = after.getData();
            listAll.addAll(list);
            if (null == applyForAdapter) {
                applyForAdapter=new ApplyForAdapter(mActivity,listAll);
                listView.setAdapter(applyForAdapter);
            } else {
                applyForAdapter.notifyDataSetChanged();
            }
            if (list.size() < 20) {
                mRefreshLayout.setIsLoadingMoreEnabled(false);
            }
        } else {
            ToastUtil.showLong(after.getDesc());
        }
    }

    @Override
    public void onRefresh(View view) {
        page=1;
        HttpMethod.getAfterOrder( page, HandlerConstant.GET_AFTER_ORDER_SUCCESS1, handler);
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        HttpMethod.getAfterOrder(page, HandlerConstant.GET_AFTER_ORDER_SUCCESS1, handler);
    }


    /**
     * 获取消息数据
     */
    private void getAfterOrder(int index) {
        if (null != view && isVisibleToUser && listAll.size() == 0) {
            HttpMethod.getAfterOrder(page, index, handler);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        //获取售后申请
        getAfterOrder(HandlerConstant.GET_AFTER_ORDER_SUCCESS1);
    }

    @Override
    public void onDestroy() {
        removeHandler(handler);
        super.onDestroy();
    }
}
