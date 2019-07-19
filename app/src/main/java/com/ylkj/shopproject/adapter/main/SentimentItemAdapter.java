package com.ylkj.shopproject.adapter.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.zxdc.utils.library.bean.MainRQ;
import java.util.List;
/**
 * 首页的人气推荐
 */
public class SentimentItemAdapter extends BaseAdapter {

	private Context context;
	private List<MainRQ.DataList> list;
	public SentimentItemAdapter(Context context,List<MainRQ.DataList> list) {
		super();
		this.context = context;
		this.list=list;
	}

	@Override
	public int getCount() {
		if(list.size()<3){
			return list.size();
		}else{
			return 3;
		}
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
			view = LayoutInflater.from(context).inflate(R.layout.item_rqtj_grid, null);
			holder.imgIcon=view.findViewById(R.id.img_icon);
			holder.tvName=view.findViewById(R.id.tv_name);
			holder.tvMoney=view.findViewById(R.id.tv_money);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}
		MainRQ.DataList dataList=list.get(position);
		String imgUrl=dataList.getImgurl();
		holder.imgIcon.setTag(R.id.imageid,imgUrl);
		if(holder.imgIcon.getTag(R.id.imageid)!=null && imgUrl==holder.imgIcon.getTag(R.id.imageid)){
			Glide.with(context).load(imgUrl).override(79,59).centerCrop().into(holder.imgIcon);
		}
		holder.tvName.setTag(dataList);
		holder.tvName.setText(dataList.getName());
		holder.tvMoney.setText(String.valueOf(dataList.getPrice()));
		return view;
	}


	private class ViewHolder{
		private ImageView imgIcon;
		private TextView tvName,tvMoney;
	 }
}
