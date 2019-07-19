package com.ylkj.shopproject.adapter.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ylkj.shopproject.R;
import com.zxdc.utils.library.bean.Coupon;
import java.util.List;
/**
 * 下单界面中选择优惠券
 */
public class SelectYhqAdapter extends BaseAdapter {

	private Context context;
	private List<Coupon.DataBean> list;
	//1:可使用   2：不可使用
	private int status;
	//选中的下标
	private int index;
	public SelectYhqAdapter(Context context, List<Coupon.DataBean> list, int status) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_select_yhq, null);
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
		if(index==position && status==1){
			holder.imgStatus.setVisibility(View.VISIBLE);
		}else{
			holder.imgStatus.setVisibility(View.GONE);
		}
		return view;
	}

	public void setIndex(int index){
		this.index=index;
	}

	private class ViewHolder{
		private TextView tvMoney,tvDes,tvName,tvTime;
		private ImageView imgStatus;
	 }
}
