package com.ylkj.shopproject.adapter.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.zxdc.utils.library.bean.ZzfuTypeList;
import com.zxdc.utils.library.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * 增值服务分类下的列表
 */
public class ZzfuTypeAdapter extends BaseAdapter {

	private Context context;
	private List<ZzfuTypeList.ListBean> list;
	public ZzfuTypeAdapter(Context context, List<ZzfuTypeList.ListBean> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_zzfw, null);
			holder.imgIcon=view.findViewById(R.id.img_icon);
			holder.tvTitle=view.findViewById(R.id.tv_title);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}

		final ZzfuTypeList.ListBean listBean=list.get(position);
		holder.tvTitle.setText(listBean.getTitle());
		String imgUrl=listBean.getImgurl();
		holder.imgIcon.setTag(R.id.imageid,imgUrl);
		if(holder.imgIcon.getTag(R.id.imageid)!=null && imgUrl==holder.imgIcon.getTag(R.id.imageid)){
			Glide.with(context).load(imgUrl).override(83,83).centerCrop().into(holder.imgIcon);
		}
		return view;
	}


	private class ViewHolder{
		private CircleImageView imgIcon;
		private TextView tvTitle;
	 }
}
