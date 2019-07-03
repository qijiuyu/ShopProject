package com.ylkj.shopproject.adapter.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ylkj.shopproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单消息
 */
public class ZzfuTypeAdapter extends BaseAdapter {

	private Context context;
	private List<String> list=new ArrayList<>();
	public ZzfuTypeAdapter(Context context, List<String> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_zzfw, null);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}
		return view;
	}


	private class ViewHolder{
		private TextView tvTime,tvContent;
	 }
}
