package com.ylkj.shopproject.adapter.user;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.showimg.ShowImgActivity;
import com.zxdc.utils.library.util.SPUtil;
import com.zxdc.utils.library.view.OvalImage2Views;

import java.util.ArrayList;
import java.util.List;

public class EvaluationImgAdapter extends BaseAdapter {

	private Context context;
	private String[] imgList;
	public EvaluationImgAdapter(Context context, String[] imgList) {
		super();
		this.context = context;
		this.imgList=imgList;
	}

	@Override
	public int getCount() {
		return imgList==null ? 0 : imgList.length;
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
			view = LayoutInflater.from(context).inflate(R.layout.item_business_gridview, null);
			holder.imgIcon=view.findViewById(R.id.img_icon);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}
		Glide.with(context).load(imgList[position]).override(90,90).centerCrop().into(holder.imgIcon);
		holder.imgIcon.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				List<String> imgs=new ArrayList<>();
				for (int i=0;i<imgList.length;i++){
					imgs.add(imgList[i]);
				}
				Intent intent=new Intent(context,ShowImgActivity.class);
				intent.putExtra("imgs", SPUtil.gson.toJson(imgList));
				context.startActivity(intent);
			}
		});
		return view;
	}


	private class ViewHolder{
		private OvalImage2Views imgIcon;
	 }
}
