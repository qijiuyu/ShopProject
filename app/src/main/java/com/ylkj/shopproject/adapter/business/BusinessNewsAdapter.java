package com.ylkj.shopproject.adapter.business;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.zxdc.utils.library.bean.News;

import java.util.List;

/**
 * 生意圈消息
 */
public class BusinessNewsAdapter extends BaseAdapter {

	private Context context;
	private List<News.DataBean> list;
	public BusinessNewsAdapter(Context context, List<News.DataBean> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_business_news, null);
			holder.tvTime=view.findViewById(R.id.tv_time);
			holder.tvContent=view.findViewById(R.id.tv_content);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}
		News.DataBean dataBean=list.get(position);
		holder.tvTime.setText(dataBean.getCreatetime());
		holder.tvContent.setText(dataBean.getContent());
		return view;
	}


	private class ViewHolder{
		private TextView tvTime,tvContent;
	 }
}
