package com.ylkj.shopproject.adapter.user;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.shopping.PayOrderActivity;
import com.ylkj.shopproject.activity.user.evaluation.EvaluationActivity;
import com.ylkj.shopproject.activity.user.persenter.OrderPersenter;
import com.ylkj.shopproject.view.HorizontalListView;
import com.zxdc.utils.library.bean.MyOrder;
import com.zxdc.utils.library.util.Util;
import com.zxdc.utils.library.view.OvalImage2Views;
import java.util.List;
/**
 * 我的订单
 */
public class OrderAdapter extends BaseAdapter {

	private Context context;
	private List<MyOrder.DataBean> list;
	private MyOrderImgAdapter myOrderImgAdapter;
	private OrderPersenter orderPersenter;
	public OrderAdapter(Context context, List<MyOrder.DataBean> list,OrderPersenter orderPersenter) {
		super();
		this.context = context;
		this.list=list;
		this.orderPersenter=orderPersenter;
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
			view = LayoutInflater.from(context).inflate(R.layout.item_order, null);
			holder.relOne=view.findViewById(R.id.rel_one);
			holder.imgIcon=view.findViewById(R.id.img_icon);
			holder.tvTitle=view.findViewById(R.id.tv_title);
			holder.tvColor=view.findViewById(R.id.tv_color);
			holder.tvNum=view.findViewById(R.id.tv_num);
			holder.tvMoney=view.findViewById(R.id.tv_money);
			holder.tvStatus=view.findViewById(R.id.tv_status);
			holder.tvDelete=view.findViewById(R.id.tv_delete);
			holder.tvCancle=view.findViewById(R.id.tv_cancle);
			holder.tvFk=view.findViewById(R.id.tv_fk);
			holder.listView=view.findViewById(R.id.listView);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}

		final MyOrder.DataBean dataBean=list.get(position);
		//只有一件商品
		if(dataBean.getPros().size()==1){
			holder.relOne.setVisibility(View.VISIBLE);
			holder.listView.setVisibility(View.GONE);
			MyOrder.OrderList orderList=dataBean.getPros().get(0);
			//显示商品图片
			String imgUrl=orderList.getProimg();
			holder.imgIcon.setTag(R.id.imageid,imgUrl);
			if(holder.imgIcon.getTag(R.id.imageid)!=null && imgUrl==holder.imgIcon.getTag(R.id.imageid)){
				Glide.with(context).load(imgUrl).override(90,90).centerCrop().into(holder.imgIcon);
			}
			//显示商品内容
			holder.tvTitle.setText(orderList.getProname());
			//显示规格名称
			holder.tvColor.setText(orderList.getSpecsvalue());
		}

		//有多件商品
		if(dataBean.getPros().size()>1){
			holder.relOne.setVisibility(View.GONE);
			holder.listView.setVisibility(View.VISIBLE);
			myOrderImgAdapter=new MyOrderImgAdapter(context,dataBean.getPros());
			holder.listView.setAdapter(myOrderImgAdapter);
		}


		holder.tvNum.setText("共"+dataBean.getPros().size()+"件商品   订单金额");
		holder.tvMoney.setText("￥"+ Util.setDouble(dataBean.getPayprice(),2));
		switch (dataBean.getStatus()){
			case 0:
				 holder.tvStatus.setText("待付款");
				 holder.tvDelete.setVisibility(View.VISIBLE);
				 holder.tvFk.setText("付款");
				 holder.tvFk.setVisibility(View.VISIBLE);
				 holder.tvCancle.setVisibility(View.VISIBLE);
				 break;
			case 1:
				holder.tvStatus.setText("待发货");
				holder.tvDelete.setVisibility(View.GONE);
				holder.tvFk.setVisibility(View.GONE);
				holder.tvCancle.setVisibility(View.GONE);
				break;
			case 2:
				holder.tvStatus.setText("待收货");
				holder.tvDelete.setVisibility(View.GONE);
				holder.tvFk.setText("确认收货");
				holder.tvFk.setVisibility(View.VISIBLE);
				holder.tvCancle.setVisibility(View.GONE);
				break;
			case 3:
				holder.tvStatus.setText("已完成");
				holder.tvDelete.setVisibility(View.VISIBLE);
				holder.tvFk.setText("去评价");
				holder.tvFk.setVisibility(View.VISIBLE);
				holder.tvCancle.setVisibility(View.GONE);
				break;
			case 5:
				holder.tvStatus.setText("已取消");
				holder.tvDelete.setVisibility(View.VISIBLE);
				holder.tvFk.setVisibility(View.GONE);
				holder.tvCancle.setVisibility(View.GONE);
				break;
		}

		/**
		 * 取消订单
		 */
		holder.tvCancle.setTag(dataBean);
		holder.tvCancle.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				final MyOrder.DataBean dataBean= (MyOrder.DataBean) v.getTag();
				orderPersenter.cancleOrder(dataBean);
			}
		});

        /**
         * 删除订单
         */
        holder.tvDelete.setTag(dataBean);
        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final MyOrder.DataBean dataBean= (MyOrder.DataBean) v.getTag();
				orderPersenter.delOrder(dataBean);
            }
        });

        /**
         * 付款/确认收货/去评价
         */
        holder.tvFk.setTag(dataBean);
        holder.tvFk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final MyOrder.DataBean dataBean= (MyOrder.DataBean) v.getTag();
				Intent intent=new Intent();
                switch (dataBean.getStatus()){
                    //去付款
                    case 0:
						 intent.setClass(context,PayOrderActivity.class);
						 intent.putExtra("money",dataBean.getPayprice());
						 intent.putExtra("orderCode",dataBean.getCode());
						 context.startActivity(intent);
                         break;
                    //确认收货
                    case 2:
						 orderPersenter.confirmGood(dataBean);
                         break;
                    //去评价
                    case 3:
						 intent.setClass(context,EvaluationActivity.class);
						 context.startActivity(intent);
                         break;
                }
            }
        });
		return view;
	}


	private class ViewHolder{
		private RelativeLayout relOne;
		private OvalImage2Views imgIcon;
		private TextView tvTitle,tvColor,tvNum,tvMoney,tvStatus,tvDelete,tvCancle,tvFk;
		private HorizontalListView listView;
	 }
}
