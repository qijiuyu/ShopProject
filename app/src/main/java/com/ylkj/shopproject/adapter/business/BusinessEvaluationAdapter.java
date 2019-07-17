package com.ylkj.shopproject.adapter.business;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.zxdc.utils.library.bean.Business;

import java.util.ArrayList;
import java.util.List;

/**
 * 生意圈中的回复列表消息
 */
public class BusinessEvaluationAdapter extends BaseAdapter {

	private Context context;
	private List<Business.Common> list;
	public BusinessEvaluationAdapter(Context context, List<Business.Common> list) {
		super();
		this.context = context;
		this.list=list;
	}

	@Override
	public int getCount() {
		return list==null ? 0 : list.size();
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
			view = LayoutInflater.from(context).inflate(R.layout.item_business_evaluation, null);
			holder.tvEvalution=view.findViewById(R.id.tv_evaluation);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}
		Business.Common common=list.get(position);
		holder.tvEvalution.setText(Html.fromHtml("<font color='#36C7B5'>"+common.getNickname()+"：</font><font color='#150000'>"+common.getContent()+"</font>"));
		return view;
	}


	private class ViewHolder{
		private TextView tvEvalution;
	 }
}
