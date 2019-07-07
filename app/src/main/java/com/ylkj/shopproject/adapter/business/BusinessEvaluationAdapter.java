package com.ylkj.shopproject.adapter.business;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ylkj.shopproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 生意圈中的回复列表消息
 */
public class BusinessEvaluationAdapter extends BaseAdapter {

	private Context context;
	private List<String> list=new ArrayList<>();
	public BusinessEvaluationAdapter(Context context, List<String> list) {
		super();
		this.context = context;
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
			view = LayoutInflater.from(context).inflate(R.layout.item_business_evaluation, null);
			holder.tvEvalution=view.findViewById(R.id.tv_evaluation);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}
		holder.tvEvalution.setText(Html.fromHtml("<font color='#36C7B5'>张鹏：</font><font color='#150000'>回朝阳天街的路上 ,突然车子坏了，领里的朋友看到帮忙转发一下，谢谢。</font>"));
		return view;
	}


	private class ViewHolder{
		private TextView tvEvalution;
	 }
}
