package com.ylkj.shopproject.adapter.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.main.fragment.ScreeningFragment;
import com.zxdc.utils.library.bean.Secreening;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ScreeningTypeDataAdapter extends BaseAdapter {

	private Context context;
	private List<Secreening.searchValsList> list;
	//是否多选(0:否 1:是)
	private int Ischeckbox;
	//选中的名称
	private Map<String,String> map=new HashMap();
	private int index;
	public ScreeningTypeDataAdapter(Context context, List<Secreening.searchValsList> list, int Ischeckbox,int index) {
		super();
		this.context = context;
		this.list=list;
		this.Ischeckbox=Ischeckbox;
		this.index=index;
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
	public View getView(final int position, View view, ViewGroup parent) {
		if(view==null){
			holder = new ViewHolder(); 
			view = LayoutInflater.from(context).inflate(R.layout.item_pj_details_type_data, null);
			holder.tvName=view.findViewById(R.id.tv_name);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}

		final Secreening.searchValsList searchValsList=list.get(position);
		holder.tvName.setText(searchValsList.getValname());
		if(map.get(searchValsList.getValname())!=null){
			holder.tvName.setTextColor(context.getResources().getColor(R.color.color_CE5798));
			holder.tvName.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.yes_click_type));
		}else{
			holder.tvName.setTextColor(context.getResources().getColor(R.color.color_33333));
			holder.tvName.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.no_click_type));
		}

		holder.tvName.setTag(searchValsList.getValname());
		holder.tvName.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				final String name=(String)v.getTag();
				//单选
				if(Ischeckbox==0){
					map.clear();
					map.put(name,name);
				}
				//可以多选
				if(Ischeckbox==1){
					if(map.get(name)==null){
						map.put(name,name);
					}else{
						map.remove(name);
					}
				}
				ScreeningTypeDataAdapter.this.notifyDataSetChanged();

				if(map.size()==0){
					return;
				}
				//遍历map集合获取选中的名称
				Set<String> set = map.keySet();
				StringBuffer stringBuffer=new StringBuffer();
				for (String in : set){
					  stringBuffer.append(map.get(in)+",");
				}
				ScreeningFragment.nameMap.put(index,stringBuffer.substring(0,stringBuffer.toString().length()-1));
			}
		});
		return view;
	}


	private class ViewHolder{
		private TextView tvName;
	 }
}
