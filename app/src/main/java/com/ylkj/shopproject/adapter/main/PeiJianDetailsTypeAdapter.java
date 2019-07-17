package com.ylkj.shopproject.adapter.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.zxdc.utils.library.bean.PJGoodDetails;
import com.zxdc.utils.library.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

public class PeiJianDetailsTypeAdapter extends BaseAdapter {

	private Context context;
	private List<PJGoodDetails.proSpecsBean> list;
	private PJDetailsTypeDataAdapter pjDetailsTypeDataAdapter;
	public PeiJianDetailsTypeAdapter(Context context, List<PJGoodDetails.proSpecsBean> list) {
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
		final PJGoodDetails.proSpecsBean proSpecsBean=list.get(position);
		holder.tvName.setText(proSpecsBean.getSpecsname());

		//展示具体规则数据
		pjDetailsTypeDataAdapter=new PJDetailsTypeDataAdapter(context,proSpecsBean.getProSpecsVals());
		holder.gridView.setAdapter(pjDetailsTypeDataAdapter);
		return view;
	}


	private class ViewHolder{
		private MyGridView gridView;
		private TextView tvName;
	 }
}
