package com.ylkj.shopproject.adapter.type;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ylkj.shopproject.R;
import com.zxdc.utils.library.bean.JCGoodDetails;
import com.zxdc.utils.library.util.Util;
import com.zxdc.utils.library.view.MyGridView;
import java.util.List;
/**
 * 分类
 */
public class JC_Details_Type_Adapter extends BaseAdapter {

	private Context context;
	private List<JCGoodDetails.machineAttrsList> list;
	//类型描述的数据adapter
	private JC_Details_Type_DataAdapter jc_details_type_dataAdapter;
	public JC_Details_Type_Adapter(Context context, List<JCGoodDetails.machineAttrsList> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_jcdetails_type, null);
			holder.tvType=view.findViewById(R.id.tv_type);
			holder.tvMoney=view.findViewById(R.id.tv_type_money);
			holder.gridView=view.findViewById(R.id.gv_type);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}

		final JCGoodDetails.machineAttrsList machineAttrsList=list.get(position);
		holder.tvType.setText(machineAttrsList.getName());
		holder.tvMoney.setText("¥"+ Util.setDouble(machineAttrsList.getPrice(),2));

		//显示规格值
		jc_details_type_dataAdapter=new JC_Details_Type_DataAdapter(context,machineAttrsList.getMachineAttrValues(),machineAttrsList.getIsmany());
		holder.gridView.setAdapter(jc_details_type_dataAdapter);
		return view;
	}


	private class ViewHolder{
		private TextView tvType,tvMoney;
		private MyGridView gridView;
	 }
}
