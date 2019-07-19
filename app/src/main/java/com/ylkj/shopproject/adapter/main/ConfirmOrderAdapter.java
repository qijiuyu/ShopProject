package com.ylkj.shopproject.adapter.main;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.zxdc.utils.library.bean.Shopping;
import com.zxdc.utils.library.view.OvalImage2Views;
import java.util.List;
/**
 * 确认下单商品列表
 */
public class ConfirmOrderAdapter extends BaseAdapter {

	private Context context;
	private List<Shopping.goodList> list;
	public ConfirmOrderAdapter(Context context, List<Shopping.goodList> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_confirm_order_good, null);
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
		Shopping.goodList goodList=list.get(position);
		//显示商品图片
		String imgUrl=goodList.getImg();
		holder.imgIcon.setTag(R.id.imageid,imgUrl);
		if(holder.imgIcon.getTag(R.id.imageid)!=null && imgUrl==holder.imgIcon.getTag(R.id.imageid)){
			Glide.with(context).load(imgUrl).override(90,90).centerCrop().into(holder.imgIcon);
		}
		holder.tvTitle.setText(goodList.getProname());
		holder.tvMoney.setText("¥"+goodList.getPrice());
		holder.tvOldMoney.setText("¥"+goodList.getOldprice());
		holder.tvOldMoney.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
		holder.tvNum.setText("x"+goodList.getProcount());
		holder.tvYfMoney.setText("运费："+goodList.getFreigth());
		return view;
	}


	private class ViewHolder{
		private OvalImage2Views imgIcon;
		private TextView tvTitle,tvMoney,tvOldMoney,tvNum,tvYfMoney;
	 }
}
