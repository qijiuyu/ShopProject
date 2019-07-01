package com.ylkj.shopproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class CommentAdapter extends BaseAdapter {

	private Context context;
	public CommentAdapter(Context context) {
		super();
		this.context = context;
	}

	@Override
	public int getCount() {
		return 0;
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
//			view = LayoutInflater.from(context).inflate(R.layout.comment_item, null);
//			holder.tvMoney=(TextView)view.findViewById(R.id.tv_ci_money);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}
//		final String imgHead=comment.getAddHead();
//		holder.imgHead.setTag(R.id.imgHead,imgHead);
//		if(holder.imgHead.getTag(R.id.imgHead)!=null && imgHead==holder.imgHead.getTag(R.id.imgHead)){
//			Glide.with(context).load(imgHead).override(33,33).centerCrop().error(R.mipmap.default_head).into(holder.imgHead);
//		}
		return view;
	}


	private class ViewHolder{
		private ImageView imgHead,imgGood;
		private TextView tvNickName,tvDes,tvTime,tvEvaluate,tvMoney;
		private ImageView imgX1,imgX2,imgX3,imgX4,imgX5;
	 }
}
