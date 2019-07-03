package com.ylkj.shopproject.adapter.main;

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
 * 订单消息
 */
public class PeiJianTypeAdapter extends BaseAdapter {

	private Context context;
	private List<String> list=new ArrayList<>();
	//选中的下标
	public int index=-1;
	public PeiJianTypeAdapter(Context context, List<String> list) {
		super();
		this.context = context;
	}

	@Override
	public int getCount() {
		return 20;
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
			view = LayoutInflater.from(context).inflate(R.layout.item_pj_type, null);
			holder.tvName=view.findViewById(R.id.tv_name);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}
		if(position==index){
			holder.tvName.setTextColor(context.getResources().getColor(R.color.color_37C7B5));
		}else{
			holder.tvName.setTextColor(context.getResources().getColor(R.color.color_666666));
		}
		holder.tvName.setTag(position);
		holder.tvName.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				index=(int)v.getTag();
				PeiJianTypeAdapter.this.notifyDataSetChanged();
			}
		});
		return view;
	}


	private class ViewHolder{
		private TextView tvName;
	 }
}
