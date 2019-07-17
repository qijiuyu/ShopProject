package com.ylkj.shopproject.adapter.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.zxdc.utils.library.bean.PJType;
import com.zxdc.utils.library.view.MyGridView;
import com.zxdc.utils.library.view.OvalImage2Views;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * 配件商城分类下面的数据图片
 */
public class PeiJianDataImgAdapter extends BaseAdapter {

	private Context context;
	private List<PJType.TypeBean> list;
	public PeiJianDataImgAdapter(Context context, List<PJType.TypeBean> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_pj_data_img, null);
			holder.tvName=view.findViewById(R.id.tv_name);
			holder.imgIcon=view.findViewById(R.id.img_icon);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}

		PJType.TypeBean typeBean=list.get(position);
		holder.tvName.setText(typeBean.getName());
		//显示图片
		String imgUrl=typeBean.getImg();
		holder.imgIcon.setTag(R.id.imageid,imgUrl);
		if(holder.imgIcon.getTag(R.id.imageid)!=null && imgUrl==holder.imgIcon.getTag(R.id.imageid)){
			Glide.with(context).load(imgUrl).override(110,70).centerCrop().into(holder.imgIcon);
		}
		return view;
	}


	private class ViewHolder{
		private OvalImage2Views imgIcon;
		private TextView tvName;
	 }
}
