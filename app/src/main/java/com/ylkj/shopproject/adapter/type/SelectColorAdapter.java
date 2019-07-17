package com.ylkj.shopproject.adapter.type;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.zxdc.utils.library.bean.JCGoodDetails;
import com.zxdc.utils.library.view.CircleImageView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
/**
 * 机床详情---颜色item
 */
public class SelectColorAdapter extends BaseAdapter {

	private Context context;
	private List<JCGoodDetails.machineValueList> list;
	//选中的颜色
	private int index=0;
	public SelectColorAdapter(Context context, List<JCGoodDetails.machineValueList> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_select_color, null);
			holder.lin=view.findViewById(R.id.lin_select);
			holder.imgColor=view.findViewById(R.id.img_color);
			holder.tvName=view.findViewById(R.id.tv_name);
			holder.tvDes=view.findViewById(R.id.tv_name);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}

		final JCGoodDetails.machineValueList machineValueList=list.get(position);
		holder.tvName.setText(machineValueList.getTitle());
		holder.tvDes.setText(machineValueList.getSubtitle());

		//显示颜色图片
		String imgUrl=machineValueList.getImgurl();
		holder.imgColor.setTag(R.id.imageid,imgUrl);
		if(holder.imgColor.getTag(R.id.imageid)!=null && imgUrl==holder.imgColor.getTag(R.id.imageid)){
			Glide.with(context).load(imgUrl).override(27,27).centerCrop().error(R.mipmap.select_btn).into(holder.imgColor);
		}

		//展示哪个被选中
		if(index==position){
			holder.lin.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.select_color));
		}else{
			holder.lin.setBackgroundDrawable(null);
		}
		holder.lin.setTag(position);
		holder.lin.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				index=(int)v.getTag();
				SelectColorAdapter.this.notifyDataSetChanged();

				//通知activity
				JCGoodDetails.machineValueList machineValueList=list.get(index);
				EventBus.getDefault().post(new EventBusType(EventStatus.SELECT_JC_COLOR,machineValueList));
			}
		});

		return view;
	}


	private class ViewHolder{
		private LinearLayout lin;
		private CircleImageView imgColor;
		private TextView tvName,tvDes;
	 }
}
