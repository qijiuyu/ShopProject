package com.ylkj.shopproject.adapter.business;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.business.ReportActivity;
import com.zxdc.utils.library.bean.Business;
import com.zxdc.utils.library.view.CircleImageView;
import com.zxdc.utils.library.view.MyGridView;
import java.util.List;
/**
 * 搜索生意圈的item
 */
public class SearchBusinessAdapter extends BaseAdapter {

	private Context context;
	private List<Business.DataBean> list;
	//发布过的图片adapter
	private BusinessImgAdapter businessImgAdapter;
	public SearchBusinessAdapter(Context context, List<Business.DataBean> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_search_business, null);
			holder.imgUser=view.findViewById(R.id.img_user);
			holder.tvCompany=view.findViewById(R.id.tv_company);
			holder.imgPhone=view.findViewById(R.id.img_phone);
			holder.imgReport=view.findViewById(R.id.img_report);
			holder.tvContent=view.findViewById(R.id.tv_content);
			holder.tvType=view.findViewById(R.id.tv_type);
			holder.tvLocation=view.findViewById(R.id.tv_location);
			holder.gridView=view.findViewById(R.id.gridview);
			holder.tvTime=view.findViewById(R.id.tv_time);
			holder.tvLikeNum=view.findViewById(R.id.tv_like_num);
			holder.tvCommNum=view.findViewById(R.id.tv_comm_num);
			holder.tvTop=view.findViewById(R.id.tv_top);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}

		final Business.DataBean dataBean=list.get(position);
		//用户头像
		String imgUrl=dataBean.getUserimg();
		holder.imgUser.setTag(R.id.imageid,imgUrl);
		if(holder.imgUser.getTag(R.id.imageid)!=null && imgUrl==holder.imgUser.getTag(R.id.imageid)){
			Glide.with(context).load(imgUrl).override(42,42).centerCrop().into(holder.imgUser);
		}
		//公司名称
		holder.tvCompany.setText(dataBean.getCompanyname());
		holder.tvContent.setText(dataBean.getContent());
		holder.tvType.setText("#"+dataBean.getTypename()+"#");
		holder.tvLocation.setText(dataBean.getAddress());
		String createTime=dataBean.getCreatetime().substring(2,7)+" | "+dataBean.getCreatetime().substring(11,16);
		holder.tvTime.setText(dataBean.getDistance()+"km    "+createTime);

		//评论数和点赞数
		holder.tvCommNum.setText(String.valueOf(dataBean.getCommentcount()));
		holder.tvLikeNum.setText(String.valueOf(dataBean.getPraiselist().length));

		//是否置顶
		if(dataBean.getIstop()==1){
			holder.tvTop.setVisibility(View.VISIBLE);
		}else{
			holder.tvTop.setVisibility(View.GONE);
		}

		//展示发布过的图片
		businessImgAdapter=new BusinessImgAdapter(context,dataBean.getImglist());
		holder.gridView.setAdapter(businessImgAdapter);
		holder.gridView.setClickable(false);
		holder.gridView.setPressed(false);

		//打电话
		holder.imgPhone.setTag(dataBean.getPhone());
		holder.imgPhone.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String phone=(String)v.getTag();
				callPhone(phone);
			}
		});

		//举报
		holder.imgReport.setTag(dataBean.getId());
		holder.imgReport.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				int id=(int)v.getTag();
				Intent intent=new Intent(context, ReportActivity.class);
				intent.putExtra("id",id);
				context.startActivity(intent);
			}
		});
		return view;
	}


	/**
	 * 打电话
	 */
	public void callPhone(String phoneNum){
		Intent intent = new Intent(Intent.ACTION_CALL);
		Uri data = Uri.parse("tel:" + phoneNum);
		intent.setData(data);
		context.startActivity(intent);
	}


	private class ViewHolder{
		private CircleImageView imgUser;
		private TextView tvCompany,tvContent,tvType,tvLocation,tvTime,tvLikeNum,tvTop,tvCommNum;
		private ImageView imgPhone,imgReport;
		private MyGridView gridView;
	 }
}
