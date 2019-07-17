package com.ylkj.shopproject.adapter.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.zxdc.utils.library.bean.Coupon;
import com.zxdc.utils.library.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的优惠券
 */
public class MyYhqAdapter extends BaseAdapter {

	private Context context;
	private List<Coupon.DataBean> list;
	//未使用(1), 已使用(2), 已过期(3)
	private int status;
	public MyYhqAdapter(Context context, List<Coupon.DataBean> list,int status) {
		super();
		this.context = context;
		this.list=list;
		this.status=status;
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
			view = LayoutInflater.from(context).inflate(R.layout.item_my_yhq, null);
			holder.tvMoney=view.findViewById(R.id.tv_money);
			holder.tvDes=view.findViewById(R.id.tv_des);
			holder.tvName=view.findViewById(R.id.tv_name);
			holder.tvTime=view.findViewById(R.id.tv_time);
			holder.imgStatus=view.findViewById(R.id.img_status);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}

		final Coupon.DataBean dataBean=list.get(position);
		holder.tvMoney.setText(String.valueOf(dataBean.getFacevalue()));
		holder.tvDes.setText("满"+String.valueOf(dataBean.getFullreductionvalue())+"元可用");
		holder.tvName.setText(dataBean.getCouponname());
		holder.tvTime.setText(dataBean.getProvidetime()+"-"+dataBean.getOuttime());
		switch (status){
			case 1:
				 holder.imgStatus.setImageDrawable(null);
				 break;
			case 2:
				 holder.imgStatus.setImageDrawable(context.getResources().getDrawable(R.mipmap.yi_shi_yong));
				 break;
			case 3:
				 holder.imgStatus.setImageDrawable(context.getResources().getDrawable(R.mipmap.yi_guo_qi));
				 break;
		}
		return view;
	}


	private class ViewHolder{
		private TextView tvMoney,tvDes,tvName,tvTime;
		private ImageView imgStatus;
	 }
}
