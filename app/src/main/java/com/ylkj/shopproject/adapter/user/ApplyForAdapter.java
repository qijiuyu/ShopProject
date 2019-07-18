package com.ylkj.shopproject.adapter.user;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.user.after.ApplyForActivity;
import com.zxdc.utils.library.bean.After;
import com.zxdc.utils.library.view.OvalImage2Views;
import java.util.List;
/**
 * 申请售后
 */
public class ApplyForAdapter extends BaseAdapter {

	private Context context;
	private List<After.DataBean> list;
	public ApplyForAdapter(Context context, List<After.DataBean> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_apply_for, null);
			holder.imgIcon=view.findViewById(R.id.img_icon);
			holder.tvTitle=view.findViewById(R.id.tv_title);
			holder.tvAfter=view.findViewById(R.id.tv_after);
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
		holder.tvTitle.setText(dataBean.getProname());

		//申请售后
		holder.tvAfter.setTag(dataBean);
		holder.tvAfter.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				After.DataBean dataBean= (After.DataBean) v.getTag();
				Intent intent=new Intent(context, ApplyForActivity.class);
				intent.putExtra("dataBean",dataBean);
				context.startActivity(intent);
			}
		});
		return view;
	}


	private class ViewHolder{
		private OvalImage2Views imgIcon;
		private TextView tvTitle,tvAfter;
	 }
}
