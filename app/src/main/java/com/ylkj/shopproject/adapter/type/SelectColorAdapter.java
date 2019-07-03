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
public class SelectColorAdapter extends BaseAdapter {

	private Context context;
	private List<String> list=new ArrayList<>();
	//选中的颜色
	private int index=-1;
	public SelectColorAdapter(Context context, List<String> list) {
		super();
		this.context = context;
	}

	@Override
	public int getCount() {
		return 7;
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
			view = LayoutInflater.from(context).inflate(R.layout.item_select_color, null);
			holder.lin=view.findViewById(R.id.lin_select);
			holder.imgColor=view.findViewById(R.id.img_color);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}

		if(index==position){
			holder.lin.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.select_color));
		}else{
			holder.lin.setBackgroundDrawable(null);
		}
		holder.lin.setTag(position);
		holder.lin.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				index=(int)v.getTag();
				SelectColorAdapter.this.notifyDataSetChanged();
			}
		});

		return view;
	}


	private class ViewHolder{
		private LinearLayout lin;
		private ImageView imgColor;
	 }
}
