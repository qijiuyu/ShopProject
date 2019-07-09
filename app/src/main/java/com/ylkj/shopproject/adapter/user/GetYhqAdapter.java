package com.ylkj.shopproject.adapter.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylkj.shopproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 领取优惠券
 */
public class GetYhqAdapter extends BaseAdapter {

	private Context context;
	private List<String> list=new ArrayList<>();
	public GetYhqAdapter(Context context, List<String> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_get_yhq, null);
			holder.tvMoney=view.findViewById(R.id.tv_money);
			holder.tvDes=view.findViewById(R.id.tv_des);
			holder.tvName=view.findViewById(R.id.tv_name);
			holder.tvTime=view.findViewById(R.id.tv_time);
			holder.tvGet=view.findViewById(R.id.tv_get);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}
		return view;
	}


	private class ViewHolder{
		private TextView tvMoney,tvDes,tvName,tvTime,tvGet;
	 }
}
