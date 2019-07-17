package com.ylkj.shopproject.adapter.main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.main.pjsc.PeiJianListActivity;
import com.zxdc.utils.library.bean.PJType;
import com.zxdc.utils.library.view.MyGridView;
import java.util.List;
public class PeiJianDataAdapter extends BaseAdapter {

	private Context context;
	private List<PJType.Children> list;
	private PeiJianDataImgAdapter peiJianDataImgAdapter;
	public PeiJianDataAdapter(Context context, List<PJType.Children> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_pj_data, null);
			holder.tvName=view.findViewById(R.id.tv_name);
			holder.gridView=view.findViewById(R.id.gv_img);
			holder.lin=view.findViewById(R.id.lin);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}

		final PJType.Children children=list.get(position);
		holder.tvName.setText(children.getName());
		peiJianDataImgAdapter=new PeiJianDataImgAdapter(context,children.getChildren());
		holder.gridView.setAdapter(peiJianDataImgAdapter);

		//点击进入列表
		holder.lin.setTag(children);
		holder.lin.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				PJType.Children children= (PJType.Children) v.getTag();
				Intent intent=new Intent(context,PeiJianListActivity.class);
				intent.putExtra("typeId",children.getPid());
				context.startActivity(intent);
			}
		});
		return view;
	}


	private class ViewHolder{
		private LinearLayout lin;
		private TextView tvName;
		private MyGridView gridView;
	 }
}
