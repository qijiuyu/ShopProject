package com.ylkj.shopproject.adapter.user;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.user.after.ApplyForActivity;
import com.ylkj.shopproject.activity.user.evaluation.AddEvaluationActivity;
import com.ylkj.shopproject.activity.user.evaluation.EvaluationDetailsActivity;
import com.zxdc.utils.library.view.OvalImage2Views;

import java.util.ArrayList;
import java.util.List;

/**
 * 申请售后
 */
public class EvaluationAdapter extends BaseAdapter {

	private Context context;
	private List<String> list=new ArrayList<>();
	//0：待评价   1：已评价
	private int type;
	public EvaluationAdapter(Context context, List<String> list,int type) {
		super();
		this.context = context;
		this.type=type;
	}

	@Override
	public int getCount() {
		return 5;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	ViewHolder holder = null;
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		if(view==null){
			holder = new ViewHolder(); 
			view = LayoutInflater.from(context).inflate(R.layout.item_evaluation, null);
			holder.imgIcon=view.findViewById(R.id.img_icon);
			holder.tvTitle=view.findViewById(R.id.tv_title);
			holder.tvEvalution=view.findViewById(R.id.tv_evaluation);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}
		if(type==0){
			holder.tvEvalution.setText("待评价");
		}else{
			holder.tvEvalution.setText("查看评价");
		}
		//去评价/查看评价详情
		holder.tvEvalution.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent();
				if(type==0){
					intent.setClass(context, AddEvaluationActivity.class);
				}else{
					intent.setClass(context, EvaluationDetailsActivity.class);
				}
				context.startActivity(intent);
			}
		});
		return view;
	}


	private class ViewHolder{
		private OvalImage2Views imgIcon;
		private TextView tvTitle,tvEvalution;
	 }
}
