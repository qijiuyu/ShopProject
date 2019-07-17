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
 * 二级分类
 */
public class TypeDetailsAdapter extends BaseAdapter {

	private Context context;
	private List<Type.TypeBean> list;
	public TypeDetailsAdapter(Context context, List<Type.TypeBean> list) {
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

		Type.TypeBean typeBean=list.get(position);
		holder.tvName.setText(typeBean.getName());
		String imgUrl=typeBean.getImg();
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
