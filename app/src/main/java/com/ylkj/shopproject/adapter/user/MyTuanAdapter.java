package com.ylkj.shopproject.adapter.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ylkj.shopproject.R;
import com.zxdc.utils.library.view.OvalImage2Views;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的团购
 */
public class MyTuanAdapter extends BaseAdapter {

	private Context context;
	private List<String> list=new ArrayList<>();
	public MyTuanAdapter(Context context, List<String> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_my_tuan, null);
			holder.imgIcon=view.findViewById(R.id.img_shopping);
			holder.tvPTNum=view.findViewById(R.id.tv_pt_num);
			holder.tvTitle=view.findViewById(R.id.tv_title);
			holder.tvTitle=view.findViewById(R.id.tv_time);
			holder.tvMoney=view.findViewById(R.id.tv_money);
			holder.tvDMMoney=view.findViewById(R.id.tv_dm_money);
			holder.tvNum=view.findViewById(R.id.tv_num);
			holder.tvYFMoney=view.findViewById(R.id.tv_yf_money);
			holder.tvStatus=view.findViewById(R.id.tv_statu);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}
		return view;
	}


	private class ViewHolder{
		private OvalImage2Views imgIcon;
		private TextView tvPTNum,tvTitle,tvTime,tvMoney,tvDMMoney,tvNum,tvYFMoney,tvStatus;
	 }
}
