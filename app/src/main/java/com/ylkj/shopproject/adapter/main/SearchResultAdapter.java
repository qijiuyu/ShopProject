package com.ylkj.shopproject.adapter.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.zxdc.utils.library.view.OvalImage2Views;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单消息
 */
public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.MyHolder> {

	private Context context;
	private List<String> data;
    private OnItemClickListener onItemClickListener;
	public SearchResultAdapter(Context context, List<String> data,OnItemClickListener onItemClickListener) {
		this.context = context;
		this.data = data;
		this.onItemClickListener=onItemClickListener;
	}

	public interface OnItemClickListener{
		void onItemClick(int position);
	}

	public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		View inflate = LayoutInflater.from(context).inflate(R.layout.item_search_result, viewGroup,false);
		MyHolder holder = new MyHolder(inflate);
		inflate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				onItemClickListener.onItemClick((Integer) v.getTag());
			}
		});
		return holder;
	}

	@Override
	public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
		MyHolder holder = (MyHolder) myHolder;
		holder.itemView.setTag(i);
		myHolder.tvName.setText("非常好的机床");
	}

	@Override
	public int getItemCount() {
		return 9;
	}

	public class MyHolder extends RecyclerView.ViewHolder {
		OvalImage2Views img;
		TextView tvName,tvMoney;
		public MyHolder(@NonNull View itemView) {
			super(itemView);
			img=itemView.findViewById(R.id.img_icon);
			tvName = itemView.findViewById(R.id.tv_name);
			tvMoney=itemView.findViewById(R.id.tv_money);
		}
	}
}
