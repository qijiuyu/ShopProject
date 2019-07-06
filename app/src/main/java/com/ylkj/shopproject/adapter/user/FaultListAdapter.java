package com.ylkj.shopproject.adapter.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.zxdc.utils.library.view.OvalImage2Views;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的维修记录
 */
public class FaultListAdapter extends BaseAdapter {

	private Context context;
	private List<String> list=new ArrayList<>();
	public FaultListAdapter(Context context, List<String> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_fault, null);
			holder.tvCode=view.findViewById(R.id.tv_code);
			holder.tvStatus=view.findViewById(R.id.tv_status);
			holder.tvContent=view.findViewById(R.id.tv_content);
			holder.tvStartTime=view.findViewById(R.id.tv_start_time);
			holder.tvUpdateTime=view.findViewById(R.id.tv_update_time);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}
		return view;
	}


	private class ViewHolder{
		private TextView tvCode,tvStatus,tvContent,tvStartTime,tvUpdateTime;
	 }
}
