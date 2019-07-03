package com.ylkj.shopproject.adapter.shopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylkj.shopproject.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分类
 */
public class ShoppingAdapter extends BaseAdapter {

	private Context context;
	private List<String> list=new ArrayList<>();
	//是否全部显示出多线按钮
	private boolean isShow=false;
	//保存选中的下标
	private Map<Integer,Integer> imgMap=new HashMap<>();
	public ShoppingAdapter(Context context, List<String> list) {
		super();
		this.context = context;
	}

	@Override
	public int getCount() {
		return 5;
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
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}

		if(isShow){
			holder.imgSelect.setVisibility(View.VISIBLE);
		}else{
			holder.imgSelect.setVisibility(View.GONE);
		}

		if(imgMap.get(position)!=null){
			holder.imgSelect.setImageDrawable(context.getResources().getDrawable(R.mipmap.select_all));
		}else{
			holder.imgSelect.setImageDrawable(context.getResources().getDrawable(R.mipmap.jx_no_select));
		}

		/**
		 * 按钮点击事件
		 */
		holder.imgSelect.setTag(position);
		holder.imgSelect.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				final int index=(int)v.getTag();
				if(imgMap.get(index)==null){
					imgMap.put(index,index);
				}else{
					imgMap.remove(index);
				}
				ShoppingAdapter.this.notifyDataSetChanged();
			}
		});
		return view;
	}


	public void setIsShow(boolean isShow){
		this.isShow=isShow;
	}

	private class ViewHolder{
		private ImageView imgSelect,imgIcon;
		private TextView tvContent,tvColor,tvMoney;
	 }
}
