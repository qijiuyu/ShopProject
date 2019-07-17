package com.ylkj.shopproject.adapter.type;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.zxdc.utils.library.bean.JCGoodDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分类里面的选项
 */
public class JC_Details_Type_DataAdapter extends BaseAdapter {

	private Context context;
	private List<JCGoodDetails.machineValueList> list;
	//是否多选(0:否 1:是)
	private int ismany;
	//选中的下标
	private Map<Integer,Integer> map=new HashMap<>();
	public JC_Details_Type_DataAdapter(Context context, List<JCGoodDetails.machineValueList> list,int ismany) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_jc_type, null);
			holder.lin=view.findViewById(R.id.lin);
			holder.tvName=view.findViewById(R.id.tv_name);
			holder.tvDes=view.findViewById(R.id.tv_des);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}

		final JCGoodDetails.machineValueList machineValueList=list.get(position);
		holder.tvName.setText(machineValueList.getTitle());
		holder.tvDes.setText(machineValueList.getSubtitle());
		if(map.get(position)!=null){
			holder.tvName.setTextColor(context.getResources().getColor(R.color.color_37C7B5));
			holder.tvDes.setTextColor(context.getResources().getColor(R.color.color_37C7B5));
			holder.lin.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.type_yes_select));
		}else{
			holder.tvName.setTextColor(context.getResources().getColor(R.color.color_666666));
			holder.tvDes.setTextColor(context.getResources().getColor(R.color.color_999999));
			holder.lin.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.type_no_select));
		}
		//点击item
		holder.lin.setTag(position);
		holder.lin.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				int position=(int)v.getTag();
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
				JC_Details_Type_DataAdapter.this.notifyDataSetChanged();
			}
		});
		return view;
	}


	private class ViewHolder{
		private LinearLayout lin;
		private TextView tvName,tvDes;
	 }
}
