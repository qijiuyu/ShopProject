package com.ylkj.shopproject.adapter.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.zxdc.utils.library.bean.AddrBean;
import com.zxdc.utils.library.bean.Address;
import org.greenrobot.eventbus.EventBus;
import java.util.List;
/**
 * 收货地址item
 */
public class AddressListAdapter extends BaseAdapter {

	private Context context;
	private List<AddrBean> list;
	public AddressListAdapter(Context context, List<AddrBean> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_address, null);
			holder.tvName=view.findViewById(R.id.tv_name);
			holder.tvMobile=view.findViewById(R.id.tv_mobile);
			holder.tvType=view.findViewById(R.id.tv_type);
			holder.tvAddress=view.findViewById(R.id.tv_address);
			holder.imgSelect=view.findViewById(R.id.img_select);
			holder.tvUpdate=view.findViewById(R.id.tv_update);
			holder.tvDelete=view.findViewById(R.id.tv_delete);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}
		final AddrBean addressBean=list.get(position);
		holder.tvName.setText(addressBean.getName());
		holder.tvMobile.setText(addressBean.getMobile());
		holder.tvAddress.setText(addressBean.getAddress());
		if(addressBean.getIsdefault()==1){
			holder.tvType.setVisibility(View.VISIBLE);
			holder.imgSelect.setImageDrawable(context.getResources().getDrawable(R.mipmap.select_all));
		}else{
			holder.tvType.setVisibility(View.GONE);
			holder.imgSelect.setImageDrawable(context.getResources().getDrawable(R.mipmap.select_btn));
		}

		//设置默认
		holder.imgSelect.setTag(position);
		holder.imgSelect.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				final int position=(int)v.getTag();
				EventBus.getDefault().post(new EventBusType(EventStatus.SET_ADDR_DEFAULT,position));

			}
		});

		//删除地址
		holder.tvDelete.setTag(position);
		holder.tvDelete.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				final int position=(int)v.getTag();
				EventBus.getDefault().post(new EventBusType(EventStatus.DEL_ADDR,position));

			}
		});

        //修改地址
        holder.tvUpdate.setTag(position);
        holder.tvUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final int position=(int)v.getTag();
                EventBus.getDefault().post(new EventBusType(EventStatus.UPD_ADDR,position));

            }
        });
		return view;
	}


	private class ViewHolder{
		private TextView tvName,tvMobile,tvType,tvAddress,tvUpdate,tvDelete;
		private ImageView imgSelect;
	 }
}
