package com.ylkj.shopproject.adapter.main;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.main.pjsc.PeiJianDetailsActivity;
import com.zxdc.utils.library.bean.PJGoodDetails;
import com.zxdc.utils.library.view.MyGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配件商品详情----商品类型中规格的item
 */
public class PJDetailsTypeDataAdapter extends BaseAdapter {

	private Context context;
	private List<PJGoodDetails.proSpecsVal> list;
	private int index=-1;
	//类型id
	private int specsid;
	public PJDetailsTypeDataAdapter(Context context, List<PJGoodDetails.proSpecsVal> list,int specsid) {
		super();
		this.context = context;
		this.list=list;
		this.specsid=specsid;
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
			view = LayoutInflater.from(context).inflate(R.layout.item_pj_details_type_data, null);
			holder.tvName=view.findViewById(R.id.tv_name);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}

		final PJGoodDetails.proSpecsVal proSpecsVal=list.get(position);
		holder.tvName.setText(proSpecsVal.getValuename());
		if(position==index){
			holder.tvName.setTextColor(context.getResources().getColor(R.color.color_CE5798));
			holder.tvName.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.yes_click_type));
		}else{
			holder.tvName.setTextColor(context.getResources().getColor(R.color.color_33333));
			holder.tvName.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.no_click_type));
		}
		holder.tvName.setTag(position);
		holder.tvName.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				index=(int)v.getTag();
				final PJGoodDetails.proSpecsVal proSpecsVal=list.get(index);

				if(!TextUtils.isEmpty(PeiJianDetailsActivity.skuid)){
					//如果根类型相同
					if(PeiJianDetailsActivity.specsid==specsid){

					}

					String[] skuids=PeiJianDetailsActivity.skuid.split(",");
					for (int i=0;i<skuids.length;i++){
						  if(skuids[i].equals(proSpecsVal.getSkuid())){
						  	  if(TextUtils.isEmpty(PeiJianDetailsActivity.skuid2)){
								  PeiJianDetailsActivity.skuid2=proSpecsVal.getSkuid();
							  }else{
						  	  	  if(!PeiJianDetailsActivity.skuid2.equals(proSpecsVal.getSkuid())){
						  	  	  	break;
								  }
							  }
							  PJDetailsTypeDataAdapter.this.notifyDataSetChanged();
						  	  break;
						  }
					}
				}else {
					PeiJianDetailsActivity.skuid=proSpecsVal.getSkuid();
					PeiJianDetailsActivity.specsid=specsid;
					PJDetailsTypeDataAdapter.this.notifyDataSetChanged();
				}
			}
		});
		return view;
	}


	private class ViewHolder{
		private TextView tvName;
	 }
}
