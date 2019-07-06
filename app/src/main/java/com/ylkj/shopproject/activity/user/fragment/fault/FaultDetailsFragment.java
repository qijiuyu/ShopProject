package com.ylkj.shopproject.activity.user.fragment.fault;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.zxdc.utils.library.base.BaseFragment;
import com.zxdc.utils.library.view.MyGridView;

/**
 * 维修详情
 */
public class FaultDetailsFragment extends BaseFragment {

    private TextView tvCode,tvTime,tvJxName,tvAge,tvName,tvMobile,tvAddress,tvDes;
    private MyGridView gridView;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_fault_details, container, false);
        tvCode=view.findViewById(R.id.tv_code);
        tvTime=view.findViewById(R.id.tv_time);
        tvJxName=view.findViewById(R.id.tv_jx_name);
        tvAge=view.findViewById(R.id.tv_age);
        tvName=view.findViewById(R.id.tv_name);
        tvMobile=view.findViewById(R.id.tv_mobile);
        tvAddress=view.findViewById(R.id.tv_address);
        tvDes=view.findViewById(R.id.tv_des);
        gridView=view.findViewById(R.id.gridview);
        return view;
    }
}
