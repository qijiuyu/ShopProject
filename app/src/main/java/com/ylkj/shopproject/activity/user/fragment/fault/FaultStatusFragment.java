package com.ylkj.shopproject.activity.user.fragment.fault;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ylkj.shopproject.R;
import com.zxdc.utils.library.base.BaseFragment;

/**
 * 维修状态
 */
public class FaultStatusFragment extends BaseFragment {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_order, container, false);
        return view;
    }
}
