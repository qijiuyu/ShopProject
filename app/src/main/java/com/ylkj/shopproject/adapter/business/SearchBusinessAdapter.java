package com.ylkj.shopproject.adapter.business;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.business.ReportActivity;
import com.zxdc.utils.library.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索生意圈的item
 */
public class SearchBusinessAdapter extends BaseAdapter {

	private Context context;
	private List<String> list=new ArrayList<>();
	//发布过的图片adapter
	private BusinessImgAdapter businessImgAdapter;
	public SearchBusinessAdapter(Context context, List<String> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_search_business, null);
			holder.gridView=view.findViewById(R.id.gridview);
			holder.imgReport=view.findViewById(R.id.img_report);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}

		businessImgAdapter=new BusinessImgAdapter(context,null);
		holder.gridView.setAdapter(businessImgAdapter);
		//举报
		holder.imgReport.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent(context, ReportActivity.class);
				context.startActivity(intent);
			}
		});
		return view;
	}


	private class ViewHolder{
		private MyGridView gridView;
		private ImageView imgReport;
	 }
}
