package com.ylkj.shopproject.adapter.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.zxdc.utils.library.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单消息
 */
public class PJDetailsTypeDataAdapter extends BaseAdapter {

	private Context context;
	private List<String> list=new ArrayList<>();
	private int index=-1;
	public PJDetailsTypeDataAdapter(Context context, List<String> list) {
		super();
		this.context = context;
	}

	@Override
	public int getCount() {
		return 4;
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
			view = LayoutInflater.from(context).inflate(R.layout.item_pj_details_type_data, null);
			holder.tvName=view.findViewById(R.id.tv_name);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}

		if(position==index){
			holder.tvName.setTextColor(context.getResources().getColor(R.color.color_CE5798));
			holder.tvName.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.yes_click_type));
		}else{
			holder.tvName.setTextColor(context.getResources().getColor(R.color.color_33333));
			holder.tvName.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.no_click_type));
		}
		holder.tvName.setTag(position);
		holder.tvName.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				index=(int)v.getTag();
				PJDetailsTypeDataAdapter.this.notifyDataSetChanged();
			}
		});
		return view;
	}


	private class ViewHolder{
		private TextView tvName;
	 }
}
