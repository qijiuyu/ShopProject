package com.ylkj.shopproject.adapter.user;

import android.content.Context;
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
 * 我的订单
 */
public class OrderAdapter extends BaseAdapter {

	private Context context;
	private List<String> list=new ArrayList<>();
	public OrderAdapter(Context context, List<String> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_order, null);
			holder.imgIcon=view.findViewById(R.id.img_icon);
			holder.tvTitle=view.findViewById(R.id.tv_title);
			holder.tvColor=view.findViewById(R.id.tv_color);
			holder.tvNum=view.findViewById(R.id.tv_num);
			holder.tvMoney=view.findViewById(R.id.tv_money);
			holder.tvStatus=view.findViewById(R.id.tv_status);
			holder.tvDelete=view.findViewById(R.id.tv_delete);
			holder.tvCancle=view.findViewById(R.id.tv_cancle);
			holder.tvFk=view.findViewById(R.id.tv_fk);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}
		return view;
	}


	private class ViewHolder{
		private OvalImage2Views imgIcon;
		private TextView tvTitle,tvColor,tvNum,tvMoney,tvStatus,tvDelete,tvCancle,tvFk;
	 }
}
