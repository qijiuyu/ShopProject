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
 * 分类
 */
public class AddressListAdapter extends BaseAdapter {

	private Context context;
	private List<String> list=new ArrayList<>();
	public AddressListAdapter(Context context, List<String> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_address, null);
			holder.tvName=view.findViewById(R.id.tv_name);
			holder.tvMobile=view.findViewById(R.id.tv_mobile);
			holder.tvType=view.findViewById(R.id.tv_type);
			holder.tvAddress=view.findViewById(R.id.tv_address);
			holder.imgSelect=view.findViewById(R.id.img_select);
			holder.tvUpdate=view.findViewById(R.id.tv_update);
			holder.tvDelete=view.findViewById(R.id.tv_delete);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}
		return view;
	}


	private class ViewHolder{
		private TextView tvName,tvMobile,tvType,tvAddress,tvUpdate,tvDelete;
		private ImageView imgSelect;
	 }
}
