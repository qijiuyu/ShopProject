package com.ylkj.shopproject.activity.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.adapter.main.PJDetailsTypeDataAdapter;
import com.zxdc.utils.library.base.BaseFragment;
import com.zxdc.utils.library.view.MyGridView;

/**
 * 筛选
 */
public class ScreeningFragment extends BaseFragment{

    private MyGridView gridView;
    private PJDetailsTypeDataAdapter pjDetailsTypeDataAdapter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_screening, container, false);
        gridView=view.findViewById(R.id.gv_type);

        pjDetailsTypeDataAdapter=new PJDetailsTypeDataAdapter(mActivity,null);
        gridView.setAdapter(pjDetailsTypeDataAdapter);
        return view;

    }

}
