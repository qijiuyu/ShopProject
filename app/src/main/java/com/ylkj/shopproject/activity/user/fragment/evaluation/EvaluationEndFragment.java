package com.ylkj.shopproject.activity.user.fragment.evaluation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.main.pjsc.PinTuanStatusActivity;
import com.ylkj.shopproject.adapter.user.EvaluationAdapter;
import com.ylkj.shopproject.adapter.user.MyTuanAdapter;
import com.zxdc.utils.library.base.BaseFragment;

/**
 * 已评价
 */
public class EvaluationEndFragment extends BaseFragment {

    private ListView listView;
    private EvaluationAdapter evaluationAdapter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_my_tuan, container, false);
        listView=view.findViewById(R.id.listView);

        evaluationAdapter=new EvaluationAdapter(mActivity,null,1);
        listView.setAdapter(evaluationAdapter);
        return view;
    }
}
