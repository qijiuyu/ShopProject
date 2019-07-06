package com.ylkj.shopproject.adapter.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.zxdc.utils.library.view.OvalImage2Views;

import java.util.ArrayList;
import java.util.List;

/**
 * 申请售后
 */
public class ApplyForListAdapter extends BaseAdapter {

	private Context context;
	private List<String> list=new ArrayList<>();
	public ApplyForListAdapter(Context context, List<String> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_apply_for_list, null);
			holder.imgIcon=view.findViewById(R.id.img_icon);
			holder.tvTime=view.findViewById(R.id.tv_time);
			holder.tvTitle=view.findViewById(R.id.tv_title);
			holder.tvRefund=view.findViewById(R.id.tv_refund);
			holder.tvAudit=view.findViewById(R.id.tv_audit);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}
		return view;
	}


	private class ViewHolder{
		private OvalImage2Views imgIcon;
		private TextView tvTime,tvTitle,tvRefund,tvAudit;
	 }
}
