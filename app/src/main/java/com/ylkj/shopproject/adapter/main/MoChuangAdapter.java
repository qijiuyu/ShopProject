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
import com.zxdc.utils.library.bean.MoChuang;
import java.util.List;
/**
 * 磨床的adapter
 */
public class MoChuangAdapter extends BaseAdapter {

	private Context context;
	private List<MoChuang.DataBean> list;
	public MoChuangAdapter(Context context, List<MoChuang.DataBean> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_type_details, null);
			holder.imgType=view.findViewById(R.id.img_type);
			holder.tvName=view.findViewById(R.id.tv_name);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}

		MoChuang.DataBean dataBean=list.get(position);
		holder.tvName.setText(dataBean.getName());
		String imgUrl=dataBean.getImg();
		holder.imgType.setTag(R.id.imageid,imgUrl);
		if(holder.imgType.getTag(R.id.imageid)!=null && imgUrl==holder.imgType.getTag(R.id.imageid)){
			Glide.with(context).load(imgUrl).override(110,70).centerCrop().into(holder.imgType);
		}
		return view;
	}


	private class ViewHolder{
		private ImageView imgType;
		private TextView tvName;
	 }
}
