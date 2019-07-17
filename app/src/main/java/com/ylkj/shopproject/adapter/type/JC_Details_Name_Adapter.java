package com.ylkj.shopproject.adapter.type;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.zxdc.utils.library.bean.JCGoodDetails;
import com.zxdc.utils.library.view.MeasureListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 详情下的名称
 */
public class JC_Details_Name_Adapter extends BaseAdapter {

	private Context context;
	private List<JCGoodDetails.machineAttrsList> list;
	//规格值的adapter
	private JC_Details_Name_Data_Adapter jc_details_name_data_adapter;
	public JC_Details_Name_Adapter(Context context, List<JCGoodDetails.machineAttrsList> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_jc_details_name, null);
			holder.tvName=view.findViewById(R.id.tv_name);
			holder.listView=view.findViewById(R.id.list_name);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}

		final JCGoodDetails.machineAttrsList machineAttrsList=list.get(position);
		holder.tvName.setText(machineAttrsList.getName());

		//展示规格值
		jc_details_name_data_adapter=new JC_Details_Name_Data_Adapter(context,machineAttrsList.getMachineAttrValues(),machineAttrsList.getIsmany());
		holder.listView.setAdapter(jc_details_name_data_adapter);
		return view;
	}


	private class ViewHolder{
		private MeasureListView listView;
		private TextView tvName;
	 }
}
