package com.ylkj.shopproject.adapter.user;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.zxdc.utils.library.bean.MyTuanDetails;
import com.zxdc.utils.library.view.CircleImageView;
import java.util.List;

public class PinTuanStatusAdapter extends RecyclerView.Adapter<PinTuanStatusAdapter.MyHolder> {

	private Context context;
	private List<MyTuanDetails.UserList> list;
	private OnItemClickListener onItemClickListener;
	//几人成团
	private int gcount;
	public PinTuanStatusAdapter(Context context, List<MyTuanDetails.UserList> list,int gcount, OnItemClickListener onItemClickListener) {
		this.context = context;
		this.list = list;
		this.gcount=gcount;
		this.onItemClickListener=onItemClickListener;
	}

	public interface OnItemClickListener{
		void onItemClick(int position);
	}

	public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		View inflate = LayoutInflater.from(context).inflate(R.layout.item_pintuan_status_img, viewGroup,false);
		MyHolder holder = new MyHolder(inflate);
		inflate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				onItemClickListener.onItemClick((Integer) v.getTag());
			}
		});
		return holder;
	}

	public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
		MyHolder holder = (MyHolder) myHolder;
		holder.itemView.setTag(i);
//		myHolder.tvName.setText("非常好的机床");
		MyTuanDetails.UserList userList=list.get(i);
		Glide.with(context).load(userList.getImgurl()).override(35,35).centerCrop().into(holder.img);
		if(userList.getIslaunch()==1){
			holder.tvLaunch.setVisibility(View.VISIBLE);
		}else{
			holder.tvLaunch.setVisibility(View.GONE);
		}
	}

	@Override
	public int getItemCount() {
		return gcount;
	}

	public class MyHolder extends RecyclerView.ViewHolder {
		CircleImageView img;
		TextView tvLaunch;
		public MyHolder(@NonNull View itemView) {
			super(itemView);
			img=itemView.findViewById(R.id.img_icon);
			tvLaunch=itemView.findViewById(R.id.tv_launch);
		}
	}
}
