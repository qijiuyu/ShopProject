package com.ylkj.shopproject.adapter.main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.main.pjsc.PeiJianDetailsActivity;
import com.ylkj.shopproject.activity.type.JCDetailsActivity;
import com.zxdc.utils.library.bean.MainRQ;
import com.zxdc.utils.library.view.MyGridView;
import java.util.List;
/**
 * 首页的人气推荐
 */
public class SentimentRecommendedAdapter extends BaseAdapter {

	private Context context;
	private SentimentItemAdapter sentimentItemAdapter;
	private List<MainRQ.DataBean> list;
	public SentimentRecommendedAdapter(Context context,List<MainRQ.DataBean> list) {
		super();
		this.context = context;
		this.list=list;
	}

	@Override
	public int getCount() {
		return list.size();
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
			holder.imgTitle=view.findViewById(R.id.img_title);
			holder.myGridView=view.findViewById(R.id.gv_rptj);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}

		MainRQ.DataBean dataBean=list.get(position);
        String imgUrl=dataBean.getTopicimg();
		holder.imgTitle.setTag(R.id.imageid,imgUrl);
		if(holder.imgTitle.getTag(R.id.imageid)!=null && imgUrl==holder.imgTitle.getTag(R.id.imageid)){
			Glide.with(context).load(imgUrl).override(326,81).centerCrop().into(holder.imgTitle);
		}
		//展示商品列表数据
		sentimentItemAdapter=new SentimentItemAdapter(context,dataBean.getSkulist());
		holder.myGridView.setAdapter(sentimentItemAdapter);
		/**
		 * 跳转到机床或者配件详情
		 */
		holder.myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				TextView tvName=view.findViewById(R.id.tv_name);
				MainRQ.DataList dataList= (MainRQ.DataList) tvName.getTag();
				Intent intent=new Intent();
				if(dataList.getType()==1){
					intent.setClass(context, JCDetailsActivity.class);
				}else{
					intent.setClass(context, PeiJianDetailsActivity.class);
				}
				intent.putExtra("spuid",dataList.getSpuid());
				context.startActivity(intent);
			}
		});
		return view;
	}


	private class ViewHolder{
		private ImageView imgTitle;
		private MyGridView myGridView;
	 }
}
