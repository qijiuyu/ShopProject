package com.ylkj.shopproject.activity.user.fragment.after;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.adapter.user.ApplyForAdapter;
import com.zxdc.utils.library.base.BaseFragment;

/**
 * 售后申请
 */
public class ApplyForFragment extends BaseFragment {

    private ListView listView;
    private ApplyForAdapter applyForAdapter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_my_tuan, container, false);
        listView=view.findViewById(R.id.listView);
        applyForAdapter=new ApplyForAdapter(mActivity,null);
        listView.setAdapter(applyForAdapter);
        return view;
    }
}
