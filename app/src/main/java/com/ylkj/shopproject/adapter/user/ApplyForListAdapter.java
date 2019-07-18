package com.ylkj.shopproject.adapter.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.zxdc.utils.library.bean.After;
import com.zxdc.utils.library.view.OvalImage2Views;
import java.util.List;
/**
 * 申请售后
 */
public class ApplyForListAdapter extends BaseAdapter {

	private Context context;
	private List<After.DataBean> list;
	public ApplyForListAdapter(Context context, List<After.DataBean> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_apply_for_list, null);
			holder.imgIcon=view.findViewById(R.id.img_icon);
			holder.tvTime=view.findViewById(R.id.tv_time);
			holder.tvTitle=view.findViewById(R.id.tv_title);
			holder.tvRefund=view.findViewById(R.id.tv_refund);
			holder.tvAudit=view.findViewById(R.id.tv_audit);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}
		After.DataBean dataBean=list.get(position);
		//显示商品图片
		String imgUrl=dataBean.getProimg();
		holder.imgIcon.setTag(R.id.imageid,imgUrl);
		if(holder.imgIcon.getTag(R.id.imageid)!=null && imgUrl==holder.imgIcon.getTag(R.id.imageid)){
			Glide.with(context).load(imgUrl).override(90,90).centerCrop().into(holder.imgIcon);
		}
		holder.tvTime.setText("申请时间："+dataBean.getApplytime());
		holder.tvTitle.setText(dataBean.getProname());
		if(dataBean.getType()==4){
			holder.tvRefund.setText("退货");
		}else{
			holder.tvRefund.setText("换货");
		}
		switch (dataBean.getAfstatus()){
			case -1:
				 holder.tvAudit.setText("审核不通过");
				 break;
			case 0:
				holder.tvAudit.setText("待审核");
				break;
			case 1:
				holder.tvAudit.setText("审核通过");
				break;
			case 2:
				holder.tvAudit.setText("商家待收货");
				break;
			case 3:
				holder.tvAudit.setText("商家待发货");
				break;
			case 4:
				holder.tvAudit.setText("买家待收货");
				break;
			case 5:
				holder.tvAudit.setText("售后完成");
				break;
		}
		return view;
	}


	private class ViewHolder{
		private OvalImage2Views imgIcon;
		private TextView tvTime,tvTitle,tvRefund,tvAudit;
	 }
}
