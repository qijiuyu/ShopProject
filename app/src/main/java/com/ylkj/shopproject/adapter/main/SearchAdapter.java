package com.ylkj.shopproject.adapter.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.zxdc.utils.library.view.OvalImageViews;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页的热门配件
 */
public class SearchAdapter extends BaseAdapter {

	private Context context;
	private List<String> list=new ArrayList<>();
	private onClick onClick;
	public SearchAdapter(Context context,List<String> list,onClick onClick) {
		super();
		this.context = context;
		this.list=list;
		this.onClick=onClick;
	}


	public interface onClick{
		public void delete(int position);
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
			view = LayoutInflater.from(context).inflate(R.layout.item_search, null);
			holder.tvKey=view.findViewById(R.id.tv_key);
			holder.imgClear=view.findViewById(R.id.img_clear);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}
		holder.tvKey.setText(list.get(position));
		holder.imgClear.setTag(position);
		holder.imgClear.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(null==v.getTag()){
					return;
				}
				final int position=(int)v.getTag();
				onClick.delete(position);
			}
		});
		return view;
	}


	private class ViewHolder{
		private TextView tvKey;
		private ImageView imgClear;
	 }
}
