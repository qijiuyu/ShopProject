package com.ylkj.shopproject.adapter.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.zxdc.utils.library.bean.Coupon;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.Util;
import org.greenrobot.eventbus.EventBus;
import java.util.List;
/**
 * 领取优惠券
 */
public class GetYhqAdapter extends BaseAdapter {

	private Context context;
	private List<Coupon.DataBean> list;
	public GetYhqAdapter(Context context, List<Coupon.DataBean> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_get_yhq, null);
			holder.tvMoney=view.findViewById(R.id.tv_money);
			holder.tvDes=view.findViewById(R.id.tv_des);
			holder.tvName=view.findViewById(R.id.tv_name);
			holder.tvTime=view.findViewById(R.id.tv_time);
			holder.tvGet=view.findViewById(R.id.tv_get);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}
		final Coupon.DataBean dataBean=list.get(position);
		holder.tvMoney.setText(String.valueOf(dataBean.getFacevalue()));
		holder.tvDes.setText("满"+dataBean.getFullreductionvalue()+"元可用");
		holder.tvName.setText(dataBean.getCouponname());
		holder.tvTime.setText(dataBean.getProvidetime()+"-"+dataBean.getOuttime());

		//领取优惠券
		holder.tvGet.setTag(dataBean.getId());
		holder.tvGet.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				int id=(int)v.getTag();
				LogUtils.e(id+"+++++++++++++");
				EventBus.getDefault().post(new EventBusType(EventStatus.TAKE_COUPON,id));
			}
		});
		return view;
	}


	private class ViewHolder{
		private TextView tvMoney,tvDes,tvName,tvTime,tvGet;
	 }
}
