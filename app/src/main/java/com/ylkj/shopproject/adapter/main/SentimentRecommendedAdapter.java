package com.ylkj.shopproject.adapter.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.zxdc.utils.library.view.MyGridView;
import com.zxdc.utils.library.view.OvalImageViews;

/**
 * 首页的人气推荐
 */
public class SentimentRecommendedAdapter extends BaseAdapter {

	private Context context;
	private SentimentItemAdapter sentimentItemAdapter;
	public SentimentRecommendedAdapter(Context context) {
		super();
		this.context = context;
	}

	@Override
	public int getCount() {
		return 3;
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
			view = LayoutInflater.from(context).inflate(R.layout.item_rqtj_list, null);
			holder.myGridView=view.findViewById(R.id.gv_rptj);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}
//		final String imgHead=comment.getAddHead();
//		holder.imgHead.setTag(R.id.imgHead,imgHead);
//		if(holder.imgHead.getTag(R.id.imgHead)!=null && imgHead==holder.imgHead.getTag(R.id.imgHead)){
//			Glide.with(context).load(imgHead).override(33,33).centerCrop().error(R.mipmap.default_head).into(holder.imgHead);
//		}
		sentimentItemAdapter=new SentimentItemAdapter(context);
		holder.myGridView.setAdapter(sentimentItemAdapter);
		return view;
	}


	private class ViewHolder{
		private MyGridView myGridView;
	 }
}
