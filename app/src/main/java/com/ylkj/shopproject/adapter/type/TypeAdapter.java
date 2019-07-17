package com.ylkj.shopproject.adapter.type;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.zxdc.utils.library.bean.Type;
import java.util.List;
/**
 * 分类
 */
public class TypeAdapter extends BaseAdapter {

	private Context context;
	private List<Type.Children> list;
	public TypeAdapter(Context context, List<Type.Children> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_type, null);
			holder.imgType=view.findViewById(R.id.img_type);
			holder.tvName=view.findViewById(R.id.tv_name);
			holder.tvDes=view.findViewById(R.id.tv_des);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}
		Type.Children children=list.get(position);
		holder.tvName.setText(children.getName());
		Glide.with(context).load(children.getImg()).override(110,70).centerCrop().into(holder.imgType);
		return view;
	}


	private class ViewHolder{
		private ImageView imgType;
		private TextView tvName,tvDes;
	 }
}
