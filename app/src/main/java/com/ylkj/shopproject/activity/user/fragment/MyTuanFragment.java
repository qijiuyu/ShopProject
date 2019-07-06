package com.ylkj.shopproject.activity.user.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.main.pjsc.PinTuanStatusActivity;
import com.ylkj.shopproject.adapter.user.MyTuanAdapter;
import com.zxdc.utils.library.base.BaseFragment;

public class MyTuanFragment extends BaseFragment {

    private ListView listView;
    private MyTuanAdapter myTuanFragment;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_my_tuan, container, false);
        listView=view.findViewById(R.id.listView);

        myTuanFragment=new MyTuanAdapter(mActivity,null);
        listView.setAdapter(myTuanFragment);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setClass(PinTuanStatusActivity.class);
            }
        });
        return view;
    }
}
