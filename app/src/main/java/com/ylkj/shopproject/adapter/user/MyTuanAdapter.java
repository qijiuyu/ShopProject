package com.ylkj.shopproject.adapter.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.zxdc.utils.library.bean.MyTuan;
import com.zxdc.utils.library.view.OvalImage2Views;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 我的团购
 */
public class MyTuanAdapter extends BaseAdapter {

	private Context context;
	private List<MyTuan.DataBean> list;
	private SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public MyTuanAdapter(Context context, List<MyTuan.DataBean> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_my_tuan, null);
			holder.imgIcon=view.findViewById(R.id.img_shopping);
			holder.tvPTNum=view.findViewById(R.id.tv_pt_num);
			holder.tvTitle=view.findViewById(R.id.tv_title);
			holder.tvTime=view.findViewById(R.id.tv_time);
			holder.tvMoney=view.findViewById(R.id.tv_money);
			holder.tvDMMoney=view.findViewById(R.id.tv_dm_money);
			holder.tvNum=view.findViewById(R.id.tv_num);
			holder.tvYFMoney=view.findViewById(R.id.tv_yf_money);
			holder.tvStatus=view.findViewById(R.id.tv_statu);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}
		final MyTuan.DataBean dataBean=list.get(position);
		//显示商品图片
		String imgUrl=dataBean.getProimg();
		holder.imgIcon.setTag(R.id.imageid,imgUrl);
		if(holder.imgIcon.getTag(R.id.imageid)!=null && imgUrl==holder.imgIcon.getTag(R.id.imageid)){
			Glide.with(context).load(imgUrl).override(90,90).centerCrop().into(holder.imgIcon);
		}
		holder.tvPTNum.setText(dataBean.getGcount()+"人团");
		holder.tvTitle.setText(dataBean.getProname());
		//计算剩余时间
		holder.tvTime.setText(showMyTime(dataBean.getEndtime()));
		holder.tvMoney.setText("¥"+dataBean.getPrice());
		holder.tvDMMoney.setText("单买价 ¥"+dataBean.getOldprice());
		holder.tvYFMoney.setText("运费： ¥"+dataBean.getFreight());
		switch (dataBean.getStatus()){
			case 0:
				 holder.tvStatus.setText("进行中");
				 break;
			case 1:
				holder.tvStatus.setText("拼团成功");
				 break;
			case 2:
				holder.tvStatus.setText("拼团失败");
				 break;
		}

		return view;
	}


	/**
	 * 计算团剩余时间
	 */
	private String showMyTime(String time){
		StringBuffer stringBuffer=new StringBuffer("剩余");
		try {
			Date date = format.parse(time);
			Long nowTime=System.currentTimeMillis()/1000;
			Long endTime=date.getTime()/1000;
			int myTime=((Long)(endTime-nowTime)).intValue();

			final int day=myTime/86400;
			if(day>0){
				myTime=myTime-(day*86400);
			}
			final int hoursInt = myTime / 3600;
			final int minutesInt = (myTime - hoursInt * 3600) / 60;
			final int secondsInt = myTime - hoursInt * 3600 - minutesInt * 60;
			if(day>0){
				stringBuffer.append(day+"天");
			}
			stringBuffer.append(hoursInt+":"+minutesInt+":"+secondsInt);
			return stringBuffer.toString();

		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}


	private class ViewHolder{
		private OvalImage2Views imgIcon;
		private TextView tvPTNum,tvTitle,tvTime,tvMoney,tvDMMoney,tvNum,tvYFMoney,tvStatus;
	 }
}
