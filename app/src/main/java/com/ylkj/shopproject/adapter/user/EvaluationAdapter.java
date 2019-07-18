package com.ylkj.shopproject.adapter.user;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.user.evaluation.AddEvaluationActivity;
import com.ylkj.shopproject.activity.user.evaluation.EvaluationDetailsActivity;
import com.zxdc.utils.library.bean.CommOrder;
import com.zxdc.utils.library.view.OvalImage2Views;
import java.util.List;
/**
 * 申请售后
 */
public class EvaluationAdapter extends BaseAdapter {

	private Context context;
	private List<CommOrder.DataBean> list;
	//0：待评价   1：已评价
	private int type;
	public EvaluationAdapter(Context context, List<CommOrder.DataBean> list,int type) {
		super();
		this.context = context;
		this.type=type;
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
			view = LayoutInflater.from(context).inflate(R.layout.item_evaluation, null);
			holder.imgIcon=view.findViewById(R.id.img_icon);
			holder.tvTitle=view.findViewById(R.id.tv_title);
			holder.tvEvalution=view.findViewById(R.id.tv_evaluation);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}
		final CommOrder.DataBean dataBean=list.get(position);
		//显示商品图片
		String imgUrl=dataBean.getProimg();
		holder.imgIcon.setTag(R.id.imageid,imgUrl);
		if(holder.imgIcon.getTag(R.id.imageid)!=null && imgUrl==holder.imgIcon.getTag(R.id.imageid)){
			Glide.with(context).load(imgUrl).override(90,90).centerCrop().into(holder.imgIcon);
		}
		holder.tvTitle.setText(dataBean.getProname());

		if(type==0){
			holder.tvEvalution.setText("待评价");
		}else{
			holder.tvEvalution.setText("查看评价");
		}
		//去评价/查看评价详情
		holder.tvEvalution.setTag(dataBean);
		holder.tvEvalution.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				CommOrder.DataBean dataBean= (CommOrder.DataBean) v.getTag();
				Intent intent=new Intent();
				if(type==0){
					intent.setClass(context, AddEvaluationActivity.class);
				}else{
					intent.setClass(context, EvaluationDetailsActivity.class);
				}
				intent.putExtra("dataBean",dataBean);
				context.startActivity(intent);
			}
		});
		return view;
	}


	private class ViewHolder{
		private OvalImage2Views imgIcon;
		private TextView tvTitle,tvEvalution;
	 }
}
