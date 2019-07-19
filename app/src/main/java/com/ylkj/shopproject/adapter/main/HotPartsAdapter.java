package com.ylkj.shopproject.adapter.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.zxdc.utils.library.bean.MainHot;
import com.zxdc.utils.library.view.OvalImageViews;

import java.util.List;

/**
 * 首页的热门配件
 */
public class HotPartsAdapter extends BaseAdapter {

	private Context context;
	private List<MainHot.DataBean> list;
	public HotPartsAdapter(Context context,List<MainHot.DataBean> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_rmpj_grid, null);
			holder.imgIcon=view.findViewById(R.id.img_icon);
			holder.tvName=view.findViewById(R.id.tv_name);
			holder.tvMoney=view.findViewById(R.id.tv_money);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}
		MainHot.DataBean dataBean=list.get(position);
		String imgUrl=dataBean.getImgurl();
		holder.imgIcon.setTag(R.id.imageid,imgUrl);
		if(holder.imgIcon.getTag(R.id.imageid)!=null && imgUrl==holder.imgIcon.getTag(R.id.imageid)){
			Glide.with(context).load(imgUrl).override(140,140).centerCrop().into(holder.imgIcon);
		}
		holder.tvName.setTag(dataBean.getName());
		holder.tvMoney.setText(String.valueOf(dataBean.getPrice()));
		return view;
	}


	private class ViewHolder{
		private OvalImageViews imgIcon;
		private TextView tvName,tvMoney;
	 }
}
