package com.ylkj.shopproject.adapter.user;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.business.ReportActivity;
import com.ylkj.shopproject.adapter.business.BusinessEvaluationAdapter;
import com.ylkj.shopproject.adapter.business.BusinessImgAdapter;
import com.zxdc.utils.library.view.CircleImageView;
import com.zxdc.utils.library.view.MeasureListView;
import com.zxdc.utils.library.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的交流
 */
public class MyBusinessAdapter extends BaseAdapter {

	private Context context;
	private List<String> list=new ArrayList<>();
	//评论的列表adapter
	private BusinessEvaluationAdapter businessEvaluationAdapter;
	//发布过的图片adapter
	private BusinessImgAdapter businessImgAdapter;
	public MyBusinessAdapter(Context context, List<String> list) {
		super();
		this.context = context;
	}

	@Override
	public int getCount() {
		return 10;
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
			view = LayoutInflater.from(context).inflate(R.layout.item_my_business, null);
			holder.tvContent=view.findViewById(R.id.tv_content);
			holder.tvType=view.findViewById(R.id.tv_type);
			holder.tvLocation=view.findViewById(R.id.tv_location);
			holder.gridView=view.findViewById(R.id.gridview);
			holder.tvTime=view.findViewById(R.id.tv_time);
			holder.tvLike=view.findViewById(R.id.tv_like);
			holder.listView=view.findViewById(R.id.listView);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}

		businessImgAdapter=new BusinessImgAdapter(context,null);
		holder.gridView.setAdapter(businessImgAdapter);

		businessEvaluationAdapter=new BusinessEvaluationAdapter(context,null);
		holder.listView.setAdapter(businessEvaluationAdapter);

		return view;
	}


	private class ViewHolder{
		private TextView tvContent,tvType,tvLocation,tvTime,tvLike;
		private MyGridView gridView;
		private MeasureListView listView;
	 }
}
