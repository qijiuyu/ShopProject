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
import com.zxdc.utils.library.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 详情下的名称的值
 */
public class JC_Details_Name_Data_Adapter extends BaseAdapter {

	private Context context;
	private List<JCGoodDetails.machineValueList> list;
	//是否多选(0:否 1:是)
	private int ismany;
	//选中的名称
	private Map<Integer,Integer> map=new HashMap();
	public JC_Details_Name_Data_Adapter(Context context, List<JCGoodDetails.machineValueList> list,int ismany) {
		super();
		this.context = context;
		this.list=list;
		this.ismany=ismany;
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
			view = LayoutInflater.from(context).inflate(R.layout.item_jc_details_name_data, null);
			holder.imgSelect=view.findViewById(R.id.img_select);
			holder.tvName=view.findViewById(R.id.tv_name);
			holder.tvMoney=view.findViewById(R.id.tv_money);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}

		final JCGoodDetails.machineValueList machineValueList=list.get(position);
		holder.tvName.setText(machineValueList.getTitle());
		holder.tvMoney.setText("¥"+ Util.setDouble(machineValueList.getPrice(),2));
		if(map.get(position)!=null){
			holder.imgSelect.setImageDrawable(context.getResources().getDrawable(R.mipmap.pt_success));
		}else{
			holder.imgSelect.setImageDrawable(context.getResources().getDrawable(R.mipmap.select_btn));
		}

		holder.imgSelect.setTag(position);
		holder.imgSelect.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				final int position=(int)v.getTag();
				//单选
				if(ismany==0){
					map.clear();
					map.put(position,position);
				}
				//可以多选
				if(ismany==1){
					if(map.get(position)==null){
						map.put(position,position);
					}else{
						map.remove(position);
					}
				}
				JC_Details_Name_Data_Adapter.this.notifyDataSetChanged();
			}
		});
		return view;
	}


	private class ViewHolder{
		private ImageView imgSelect;
		private TextView tvName,tvMoney;
	 }
}
