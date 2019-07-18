package com.ylkj.shopproject.adapter.user;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.zxdc.utils.library.bean.OrderDetails;
import java.util.List;
/**
 * 订单详情中的商品列表s
 */
public class OrderDetailsAdapter extends BaseAdapter {

	private Context context;
	private List<OrderDetails.OrderList> list;
	public OrderDetailsAdapter(Context context, List<OrderDetails.OrderList> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_order_details, null);
			holder.imgIcon=view.findViewById(R.id.img_shopping);
			holder.tvTitle=view.findViewById(R.id.tv_title);
			holder.tvMoney=view.findViewById(R.id.tv_money);
			holder.tvOldMoney=view.findViewById(R.id.tv_old_money);
			holder.tvNum=view.findViewById(R.id.tv_num);
			holder.tvYfMoney=view.findViewById(R.id.tv_yf_money);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}
		OrderDetails.OrderList orderList=list.get(position);
        //显示商品图片
		String imgUrl=orderList.getProductimg();
		holder.imgIcon.setTag(R.id.imageid,imgUrl);
		if(holder.imgIcon.getTag(R.id.imageid)!=null && imgUrl==holder.imgIcon.getTag(R.id.imageid)){
			Glide.with(context).load(imgUrl).override(90,90).centerCrop().into(holder.imgIcon);
		}
		holder.tvTitle.setText(orderList.getProductname());
		holder.tvMoney.setText("¥"+orderList.getProductprice());
		holder.tvOldMoney.setText("¥"+orderList.getOldprice());
		holder.tvOldMoney.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
		holder.tvNum.setText("x"+orderList.getProductcount());
		holder.tvYfMoney.setText("运费：¥"+orderList.getFreight());
		return view;
	}


	private class ViewHolder{
		private TextView tvTitle,tvMoney,tvOldMoney,tvNum,tvYfMoney;
		private ImageView imgIcon;
	 }
}
