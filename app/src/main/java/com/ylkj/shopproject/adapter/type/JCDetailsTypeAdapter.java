package com.ylkj.shopproject.adapter.type;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ylkj.shopproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类
 */
public class JCDetailsTypeAdapter extends BaseAdapter {

	private Context context;
	private List<String> list=new ArrayList<>();
	//选中类型的下标
	public int index=-1;
	public JCDetailsTypeAdapter(Context context, List<String> list) {
		super();
		this.context = context;
	}

	@Override
	public int getCount() {
		return 3;
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
			view = LayoutInflater.from(context).inflate(R.layout.item_jc_type, null);
			holder.lin=view.findViewById(R.id.lin);
			holder.tvName=view.findViewById(R.id.tv_name);
			holder.tvDes=view.findViewById(R.id.tv_des);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}
		if(index==position){
			holder.tvName.setTextColor(context.getResources().getColor(R.color.color_37C7B5));
			holder.tvDes.setTextColor(context.getResources().getColor(R.color.color_37C7B5));
			holder.lin.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.type_yes_select));
		}else{
			holder.tvName.setTextColor(context.getResources().getColor(R.color.color_666666));
			holder.tvDes.setTextColor(context.getResources().getColor(R.color.color_999999));
			holder.lin.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.type_no_select));
		}
		holder.lin.setTag(position);
		holder.lin.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				index=(int)v.getTag();
				JCDetailsTypeAdapter.this.notifyDataSetChanged();
			}
		});
		return view;
	}


	private class ViewHolder{
		private LinearLayout lin;
		private TextView tvName,tvDes;
	 }
}
