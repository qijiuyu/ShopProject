package com.ylkj.shopproject.adapter.business;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ylkj.shopproject.R;
import com.zxdc.utils.library.bean.ZzfuType;
import java.util.List;
/**
 * 生意圈分类
 */
public class BusinessTypeAdapter extends BaseAdapter {

	private Context context;
	private List<ZzfuType.dataBean> list;
	public BusinessTypeAdapter(Context context, List<ZzfuType.dataBean> list) {
		super();
		this.context = context;
		this.list=list;
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
			view = LayoutInflater.from(context).inflate(R.layout.item_business_type, null);
			holder.tvName=view.findViewById(R.id.tv_name);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}
		ZzfuType.dataBean dataBean=list.get(position);
		holder.tvName.setText(dataBean.getName());
		return view;
	}


	private class ViewHolder{
		private TextView tvName;
	 }
}
