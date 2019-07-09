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
import java.util.List;

/**
 * 分类
 */
public class JC_Details_Type_Adapter extends BaseAdapter {

	private Context context;
	private List<String> list=new ArrayList<>();
	//类型描述的数据adapter
	private JC_Details_Type_DataAdapter jc_details_type_dataAdapter;
	public JC_Details_Type_Adapter(Context context, List<String> list) {
		super();
		this.context = context;
	}

	@Override
	public int getCount() {
		return 2;
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
			view = LayoutInflater.from(context).inflate(R.layout.item_jcdetails_type, null);
			holder.tvMoney=view.findViewById(R.id.tv_type_money);
			holder.gridView=view.findViewById(R.id.gv_type);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}

		jc_details_type_dataAdapter=new JC_Details_Type_DataAdapter(context,null);
		holder.gridView.setAdapter(jc_details_type_dataAdapter);
		return view;
	}


	private class ViewHolder{
		private TextView tvMoney;
		private MyGridView gridView;
	 }
}
