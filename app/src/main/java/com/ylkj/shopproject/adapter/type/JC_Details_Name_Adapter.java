package com.ylkj.shopproject.adapter.type;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.zxdc.utils.library.view.MyGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 详情下的名称
 */
public class JC_Details_Name_Adapter extends BaseAdapter {

	private Context context;
	private List<String> list=new ArrayList<>();
	//选中的名称
	private Map<Integer,Integer> map=new HashMap();
	public JC_Details_Name_Adapter(Context context, List<String> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_jc_details_name, null);
			holder.imgSelect=view.findViewById(R.id.img_select);
			holder.tvName=view.findViewById(R.id.tv_name);
			holder.tvMoney=view.findViewById(R.id.tv_type_money);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}

		if(map.get(position)!=null){
			holder.imgSelect.setImageDrawable(context.getResources().getDrawable(R.mipmap.pt_success));
		}else{
			holder.imgSelect.setImageDrawable(context.getResources().getDrawable(R.mipmap.select_btn));
		}

		holder.imgSelect.setTag(position);
		holder.imgSelect.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				final int index=(int)v.getTag();
				if(map.get(index)==null){
					map.put(index,index);
				}else{
					map.remove(index);
				}
				JC_Details_Name_Adapter.this.notifyDataSetChanged();
			}
		});
		return view;
	}


	private class ViewHolder{
		private ImageView imgSelect;
		private TextView tvName,tvMoney;
	 }
}
