package com.ylkj.shopproject.activity.user.fragment.after;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.user.after.ApplyForDetailsActivity;
import com.ylkj.shopproject.adapter.user.ApplyForListAdapter;
import com.zxdc.utils.library.base.BaseFragment;

/**
 * 申请记录
 */
public class ApplyForListFragment extends BaseFragment {

    private ListView listView;
    private ApplyForListAdapter applyForListAdapter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_my_tuan, container, false);
        listView=view.findViewById(R.id.listView);
        applyForListAdapter=new ApplyForListAdapter(mActivity,null);
        listView.setAdapter(applyForListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setClass(ApplyForDetailsActivity.class);
            }
        });
        return view;
    }
}
