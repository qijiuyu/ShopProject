package com.ylkj.shopproject.adapter.business;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.zxdc.utils.library.bean.Business;
import com.zxdc.utils.library.bean.BusinessDetails;
import com.zxdc.utils.library.view.CircleImageView;
import java.util.List;
/**
 * 生意圈详情中的评论列表
 */
public class BusinessCommAdapter extends BaseAdapter {

	private Context context;
	private List<BusinessDetails.Common> list;
	public BusinessCommAdapter(Context context, List<BusinessDetails.Common> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_business_details_comm, null);
			holder.imgUser=view.findViewById(R.id.img_user);
			holder.tvName=view.findViewById(R.id.tv_company);
			holder.tvTime=view.findViewById(R.id.tv_time);
			holder.tvContent=view.findViewById(R.id.tv_content);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}
		BusinessDetails.Common common=list.get(position);
		holder.tvName.setText(common.getNickname());
		holder.tvContent.setText(common.getContent());
		String createTime=common.getCreatetime().substring(2,7)+" | "+common.getCreatetime().substring(11,16);
		holder.tvTime.setText(createTime);

		//显示图片
		String imgUrl=common.getImgurl();
		holder.imgUser.setTag(R.id.imageid,imgUrl);
		if(holder.imgUser.getTag(R.id.imageid)!=null && imgUrl==holder.imgUser.getTag(R.id.imageid)){
			Glide.with(context).load(imgUrl).override(42,42).centerCrop().into(holder.imgUser);
		}
		return view;
	}


	private class ViewHolder{
		private CircleImageView imgUser;
		private TextView tvName,tvTime,tvContent;
	 }
}
