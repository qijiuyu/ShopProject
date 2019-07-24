package com.ylkj.shopproject.adapter.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.zxdc.utils.library.bean.PJGoodDetails;
import com.zxdc.utils.library.bean.Secreening;
import com.zxdc.utils.library.view.MyGridView;

import java.util.List;

public class ScreeningTypeAdapter extends BaseAdapter {

	private Context context;
	private List<Secreening.DataBean> list;
	private ScreeningTypeDataAdapter screeningTypeDataAdapter;
	public ScreeningTypeAdapter(Context context, List<Secreening.DataBean> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_peijian_details_type, null);
			holder.tvName=view.findViewById(R.id.tv_name);
			holder.gridView=view.findViewById(R.id.gv_type);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}
		final Secreening.DataBean dataBean=list.get(position);
		holder.tvName.setText(dataBean.getName());

		//展示具体规则数据
		screeningTypeDataAdapter=new ScreeningTypeDataAdapter(context,dataBean.getSearchVals(),dataBean.getIscheckbox(),position);
		holder.gridView.setAdapter(screeningTypeDataAdapter);
		return view;
	}


	private class ViewHolder{
		private MyGridView gridView;
		private TextView tvName;
	 }
}
