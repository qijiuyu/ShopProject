package com.ylkj.shopproject.adapter.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ylkj.shopproject.R;
import com.zxdc.utils.library.bean.PJType;
import com.zxdc.utils.library.bean.Type;
import java.util.List;

/**
 * 订单消息
 */
public class PeiJianTypeAdapter extends BaseAdapter {

	private Context context;
	private List<PJType.dataBean> list;
	//选中的下标
	public int index=0;
	public PeiJianTypeAdapter(Context context,List<PJType.dataBean> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_pj_type, null);
			holder.tvName=view.findViewById(R.id.tv_name);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}

		final PJType.dataBean dataBeans=list.get(position);
		holder.tvName.setText(dataBeans.getName());
		if(position==index){
			holder.tvName.setTextColor(context.getResources().getColor(R.color.color_37C7B5));
		}else{
			holder.tvName.setTextColor(context.getResources().getColor(R.color.color_666666));
		}
		return view;
	}


	public void setIndex(int index){
		this.index=index;
	}


	private class ViewHolder{
		private TextView tvName;
	 }
}
