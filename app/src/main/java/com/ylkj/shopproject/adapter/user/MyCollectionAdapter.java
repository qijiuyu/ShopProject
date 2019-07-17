package com.ylkj.shopproject.adapter.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.zxdc.utils.library.bean.Collection;
import com.zxdc.utils.library.util.Util;
import com.zxdc.utils.library.view.OvalImage2Views;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的团购
 */
public class MyCollectionAdapter extends BaseAdapter {

	private Context context;
	private List<Collection.DataList> list;
	public MyCollectionAdapter(Context context, List<Collection.DataList> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_my_collection, null);
			holder.imgIcon=view.findViewById(R.id.img_icon);
			holder.tvTitle=view.findViewById(R.id.tv_title);
			holder.tvMoney=view.findViewById(R.id.tv_money);
			holder.tvOldMoney=view.findViewById(R.id.tv_old_money);
			holder.tvCancle=view.findViewById(R.id.tv_cancle);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}
		Collection.DataList dataList=list.get(position);
		holder.tvTitle.setText(dataList.getName());
		holder.tvMoney.setText(Util.setDouble(dataList.getPrice(),2));
		holder.tvOldMoney.setText(Util.setDouble(dataList.getOldprice(),2));
		String imgUrl=dataList.getImgurl();
		holder.imgIcon.setTag(R.id.imageid,imgUrl);
		if(holder.imgIcon.getTag(R.id.imageid)!=null && imgUrl==holder.imgIcon.getTag(R.id.imageid)){
			Glide.with(context).load(imgUrl).override(90,90).centerCrop().into(holder.imgIcon);
		}

		//取消收藏
		holder.tvCancle.setTag(dataList.getSpuid());
		holder.tvCancle.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				final int spuid=(int)v.getTag();
				EventBus.getDefault().post(new EventBusType(EventStatus.CANCLE_COLLECTION,spuid));
			}
		});
		return view;
	}


	private class ViewHolder{
		private OvalImage2Views imgIcon;
		private TextView tvTitle,tvMoney,tvOldMoney,tvCancle;
	 }
}
