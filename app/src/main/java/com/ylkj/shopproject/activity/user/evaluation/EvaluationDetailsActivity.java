package com.ylkj.shopproject.activity.user.evaluation;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.adapter.selectphoto.GridImageAdapter;
import com.ylkj.shopproject.adapter.user.EvaluationImgAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.CommOrder;
import com.zxdc.utils.library.bean.OrderComm;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyGridView;
import com.zxdc.utils.library.view.OvalImage2Views;
import java.util.ArrayList;
import java.util.List;
/**
 * 评价详情界面
 */
public class EvaluationDetailsActivity extends BaseActivity {

    private OvalImage2Views imgIcon;
    private TextView tvTitle,tvContent;
    private ImageView img1,img2,img3,img4,img5;
    private MyGridView gridView;
    private GridImageAdapter adapter;
    private List<ImageView> listImg=new ArrayList<>();
    private CommOrder.DataBean dataBean;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation_details);
        initView();
        //查看评价接口
        getOrderComm();
    }


    /**
     * 初始化
     */
    private void initView(){
        dataBean= (CommOrder.DataBean) getIntent().getSerializableExtra("dataBean");
        imgIcon=findViewById(R.id.img_icon);
        tvTitle=findViewById(R.id.tv_title);
        img1=findViewById(R.id.img_x1);
        img2=findViewById(R.id.img_x2);
        img3=findViewById(R.id.img_x3);
        img4=findViewById(R.id.img_x4);
        img5=findViewById(R.id.img_x5);
        tvContent=findViewById(R.id.tv_content);
        gridView=findViewById(R.id.gridview);
        listImg.add(img1);listImg.add(img2);listImg.add(img3);listImg.add(img4);listImg.add(img5);
        findViewById(R.id.lin_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EvaluationDetailsActivity.this.finish();
            }
        });
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                case HandlerConstant.GET_ORDER_COMM_SUCCESS:
                     final OrderComm orderComm= (OrderComm) msg.obj;
                     if(null==orderComm){
                         break;
                     }
                     if(orderComm.isSussess()){
                         showData(orderComm.getData());
                     }else{
                         ToastUtil.showLong(orderComm.getDesc());
                     }
                     break;
                case HandlerConstant.REQUST_ERROR:
                    ToastUtil.showLong(getString(R.string.net_error));
                    break;
            }
            return false;
        }
    });


    /**
     * 展示评价的内容
     * @param dataBean
     */
    private void showData(OrderComm.DataBean dataBean){
        Glide.with(this).load(dataBean.getSupimgurl()).centerCrop().error(R.mipmap.default_icon).into(imgIcon);
        tvTitle.setText(dataBean.getName());
        for (int i=0;i<listImg.size();i++){
             if(i<dataBean.getStarnum()){
                 listImg.get(i).setImageDrawable(getResources().getDrawable(R.mipmap.yes_xing));
             }else{
                 listImg.get(i).setImageDrawable(getResources().getDrawable(R.mipmap.no_xing));
             }
        }
        tvContent.setText(dataBean.getDetail());
        if(!TextUtils.isEmpty(dataBean.getImgurl())){
            String[] imgs=dataBean.getImgurl().split(",");
            EvaluationImgAdapter evaluationImgAdapter=new EvaluationImgAdapter(this,imgs);
            gridView.setAdapter(evaluationImgAdapter);
        }
    }


    /**
     * 查看评价接口
     */
    private void getOrderComm(){
        if(null==dataBean){
            return;
        }
        DialogUtil.showProgress(this,"数据加载中");
        HttpMethod.getOrderComm(dataBean.getDetailid(),handler);
    }
}
