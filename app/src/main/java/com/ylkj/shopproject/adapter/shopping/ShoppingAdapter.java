package com.ylkj.shopproject.adapter.shopping;

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
import com.zxdc.utils.library.bean.PJGoodList;
import com.zxdc.utils.library.bean.Shopping;
import com.zxdc.utils.library.util.Util;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车
 */
public class ShoppingAdapter extends BaseAdapter {

	private Context context;
	private List<Shopping.goodList> list;
	public ShoppingAdapter(Context context, List<Shopping.goodList> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_shopping, null);
			holder.imgSelect=view.findViewById(R.id.img_select);
			holder.imgIcon=view.findViewById(R.id.img_icon);
			holder.tvContent=view.findViewById(R.id.tv_content);
			holder.tvColor=view.findViewById(R.id.tv_color);
			holder.tvMoney=view.findViewById(R.id.tv_money);
			holder.tvNum=view.findViewById(R.id.tv_num);
			holder.imgRemove=view.findViewById(R.id.img_remove);
			holder.imgAdd=view.findViewById(R.id.img_add);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}

		final Shopping.goodList goodList=list.get(position);
		holder.tvContent.setText(goodList.getProname());
		holder.tvColor.setText(goodList.getSpecsvalue());
		holder.tvMoney.setText(Util.setDouble(goodList.getPrice(),2));
		holder.tvNum.setText(String.valueOf(goodList.getProcount()));

		//显示商品图片
		String imgUrl=goodList.getImg();
		holder.imgIcon.setTag(R.id.imageid,imgUrl);
		if(holder.imgIcon.getTag(R.id.imageid)!=null && imgUrl==holder.imgIcon.getTag(R.id.imageid)){
			Glide.with(context).load(imgUrl).override(90,90).centerCrop().into(holder.imgIcon);
		}

		if(goodList.getIsselect()==1){
			holder.imgSelect.setImageDrawable(context.getResources().getDrawable(R.mipmap.select_all));
		}else{
			holder.imgSelect.setImageDrawable(context.getResources().getDrawable(R.mipmap.jx_no_select));
		}

		/**
		 * 选择按钮点击事件
		 */
		holder.imgSelect.setTag(goodList.getCartid());
		holder.imgSelect.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				final int cartId=(int)v.getTag();
				EventBus.getDefault().post(new EventBusType(EventStatus.SELECT_CAR_GOODS,cartId));
			}
		});

		//减少数量
		holder.imgRemove.setTag(position);
		holder.imgRemove.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				final int position=(int)v.getTag();
				int num=list.get(position).getProcount();
				if(num==1){
					return;
				}
				list.get(position).setProcount(--num);
				EventBus.getDefault().post(new EventBusType(EventStatus.CHANGE_CAR_COUNT,list.get(position)));

			}
		});

		//增加数量
		holder.imgAdd.setTag(position);
		holder.imgAdd.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				final int position=(int)v.getTag();
				int num=list.get(position).getProcount();
				list.get(position).setProcount(++num);
				EventBus.getDefault().post(new EventBusType(EventStatus.CHANGE_CAR_COUNT,list.get(position)));
			}
		});
		return view;
	}

	private class ViewHolder{
		private ImageView imgSelect,imgIcon,imgRemove,imgAdd;
		private TextView tvContent,tvColor,tvMoney,tvNum;
	 }
}
