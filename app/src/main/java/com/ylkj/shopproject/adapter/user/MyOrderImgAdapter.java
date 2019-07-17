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
import com.zxdc.utils.library.bean.MyOrder;
import com.zxdc.utils.library.util.SPUtil;
import com.zxdc.utils.library.view.OvalImage2Views;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的订单中多个商品的图片
 */
public class MyOrderImgAdapter extends BaseAdapter {

	private Context context;
	private List<MyOrder.OrderList> list;
	public MyOrderImgAdapter(Context context, List<MyOrder.OrderList> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_my_order_img, null);
			holder.imgIcon=view.findViewById(R.id.img_icon);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}
		final MyOrder.OrderList orderList=list.get(position);
		Glide.with(context).load(orderList.getProimg()).override(90,90).centerCrop().into(holder.imgIcon);
		holder.imgIcon.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				List<String> imgs=new ArrayList<>();
				for (int i=0;i<list.size();i++){
					imgs.add(list.get(i).getProimg());
				}
				Intent intent=new Intent(context,ShowImgActivity.class);
				intent.putExtra("imgs", SPUtil.gson.toJson(imgs));
				context.startActivity(intent);
			}
		});
		return view;
	}


	private class ViewHolder{
		private OvalImage2Views imgIcon;
	 }
}
