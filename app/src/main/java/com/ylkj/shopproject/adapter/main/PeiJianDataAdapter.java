package com.ylkj.shopproject.adapter.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.zxdc.utils.library.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单消息
 */
public class PeiJianDataAdapter extends BaseAdapter {

	private Context context;
	private List<String> list=new ArrayList<>();
	private PeiJianDataImgAdapter peiJianDataImgAdapter;
	public PeiJianDataAdapter(Context context, List<String> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_pj_data, null);
			holder.tvName=view.findViewById(R.id.tv_name);
			holder.gridView=view.findViewById(R.id.gv_img);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}
		peiJianDataImgAdapter=new PeiJianDataImgAdapter(context,null);
		holder.gridView.setAdapter(peiJianDataImgAdapter);
		return view;
	}


	private class ViewHolder{
		private TextView tvName;
		private MyGridView gridView;
	 }
}
