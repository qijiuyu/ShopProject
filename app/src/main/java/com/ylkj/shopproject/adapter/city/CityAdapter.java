package com.ylkj.shopproject.adapter.city;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.zxdc.utils.library.bean.City;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择省市区
 */
public class CityAdapter extends BaseAdapter {

	private Context context;
	private List<City.CityBean> list=new ArrayList<>();
	private int type;
	public CityAdapter(Context context, List<City.CityBean> list,int type) {
		super();
		this.context = context;
		this.list=list;
		this.type=type;
	}

	@Override
	public int getCount() {
		return list==null ? 0 : list.size();
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
			view = LayoutInflater.from(context).inflate(R.layout.item_city, null);
			holder.tvName=view.findViewById(R.id.tv_name);
			holder.imgNext=view.findViewById(R.id.img_next);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}
		holder.tvName.setText(list.get(position).getName());
		if(type==2){
			holder.imgNext.setVisibility(View.GONE);
		}else{
			holder.imgNext.setVisibility(View.VISIBLE);
		}
		return view;
	}


	private class ViewHolder{
		private TextView tvName;
		private ImageView imgNext;
	 }
}
