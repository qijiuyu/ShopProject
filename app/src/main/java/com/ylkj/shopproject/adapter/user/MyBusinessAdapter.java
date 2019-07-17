package com.ylkj.shopproject.adapter.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.adapter.business.BusinessEvaluationAdapter;
import com.ylkj.shopproject.adapter.business.BusinessImgAdapter;
import com.zxdc.utils.library.bean.Business;
import com.zxdc.utils.library.view.MeasureListView;
import com.zxdc.utils.library.view.MyGridView;
import java.util.List;
/**
 * 我的交流
 */
public class MyBusinessAdapter extends BaseAdapter {

	private Context context;
	private List<Business.DataBean> list;
	//评论的列表adapter
	private BusinessEvaluationAdapter businessEvaluationAdapter;
	//发布过的图片adapter
	private BusinessImgAdapter businessImgAdapter;
	public MyBusinessAdapter(Context context, List<Business.DataBean> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_my_business, null);
			holder.tvYear=view.findViewById(R.id.tv_year);
			holder.tvMonth=view.findViewById(R.id.tv_month);
			holder.tvContent=view.findViewById(R.id.tv_content);
			holder.tvType=view.findViewById(R.id.tv_type);
			holder.tvLocation=view.findViewById(R.id.tv_location);
			holder.gridView=view.findViewById(R.id.gridview);
			holder.tvTime=view.findViewById(R.id.tv_time);
			holder.tvLike=view.findViewById(R.id.tv_like);
			holder.listView=view.findViewById(R.id.listView);
			holder.tvTop=view.findViewById(R.id.tv_top);
			holder.lin=view.findViewById(R.id.lin);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}
		final Business.DataBean dataBean=list.get(position);
		holder.tvYear.setText(dataBean.getCreatetime().substring(2,4));
		holder.tvMonth.setText(dataBean.getCreatetime().substring(5,7));
		holder.tvContent.setText(dataBean.getContent());
		holder.tvType.setText("#"+dataBean.getTypename()+"#");
		holder.tvLocation.setText(dataBean.getAddress());
		String createTime=dataBean.getCreatetime().substring(2,7)+" | "+dataBean.getCreatetime().substring(11,16);
		holder.tvTime.setText(dataBean.getDistance()+"km    "+createTime);

		//是否置顶
		if(dataBean.getIstop()==1){
			holder.tvTop.setVisibility(View.VISIBLE);
		}else{
			holder.tvTop.setVisibility(View.GONE);
		}

		//展示发布过的图片
		businessImgAdapter=new BusinessImgAdapter(context,dataBean.getImglist());
		holder.gridView.setAdapter(businessImgAdapter);
		holder.gridView.setClickable(false);
		holder.gridView.setPressed(false);

		//如果没有点赞和评论，那么该区域就隐藏
		if(dataBean.getPraiselist().length==0 && dataBean.getCommentlist().size()==0){
			holder.lin.setVisibility(View.GONE);
		}else{
			holder.lin.setVisibility(View.VISIBLE);
		}

		//展示点赞的用户
		StringBuffer stringBuffer=new StringBuffer();
		for (int i=0;i<dataBean.getPraiselist().length;i++){
			stringBuffer.append(dataBean.getPraiselist()[i]+",");
		}
		holder.tvLike.setText(stringBuffer.toString());

		//展示评论列表
		businessEvaluationAdapter=new BusinessEvaluationAdapter(context,dataBean.getCommentlist());
		holder.listView.setAdapter(businessEvaluationAdapter);

		return view;
	}


	private class ViewHolder{
		private TextView tvYear,tvMonth,tvContent,tvType,tvLocation,tvTime,tvLike,tvTop;
		private MyGridView gridView;
		private MeasureListView listView;
		private LinearLayout lin;
	 }
}
