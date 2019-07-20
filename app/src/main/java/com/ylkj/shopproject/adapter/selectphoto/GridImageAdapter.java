package com.ylkj.shopproject.adapter.selectphoto;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.selectphoto.bean.ImageItem;
import com.ylkj.shopproject.application.APPAplication;
import com.zxdc.utils.library.http.HttpConstant;
import com.zxdc.utils.library.view.ClickImageView;
import java.util.List;
public class GridImageAdapter extends BaseAdapter {

    private List<ImageItem> list;
    private LayoutInflater inflater;
    private Context context;
    public GridImageAdapter(Context context, List<ImageItem> list){
        this.context=context;
        this.list=list;
        this.inflater = LayoutInflater.from(context);
    }
    public int getCount() {
        return list.size()+1;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = this.inflater.inflate(R.layout.selectphoto_item, null);
            holder.grida_image=convertView.findViewById(R.id.item_image);
            holder.imgDelete=convertView.findViewById(R.id.img_delete);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        if(position==list.size()){
            holder.imgDelete.setVisibility(View.GONE);
            holder.grida_image.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.add_fault_img));
        }else{
            holder.imgDelete.setVisibility(View.VISIBLE);
            holder.imgDelete.setTag(position);
            String imgPath=list.get(position).getImagePath();
            if(!TextUtils.isEmpty(imgPath)){
                if(imgPath.indexOf(HttpConstant.IP)==-1){
                    Glide.with(APPAplication.application).load("file://" +imgPath).diskCacheStrategy(DiskCacheStrategy.NONE).into(holder.grida_image);
                }else{
                    Glide.with(APPAplication.application).load(imgPath).diskCacheStrategy(DiskCacheStrategy.NONE).into(holder.grida_image);
                }
            }
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(v.getTag()!=null){
                        final int position= (int) v.getTag();
                        list.remove(position);
                        GridImageAdapter.this.notifyDataSetChanged();
                    }
                }
            });
        }
        return convertView;
    }

    private class ViewHolder {
        ImageView grida_image;
        ClickImageView imgDelete;
    }
}

