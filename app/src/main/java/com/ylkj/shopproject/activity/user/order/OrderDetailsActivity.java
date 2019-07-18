package com.ylkj.shopproject.activity.user.order;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.shopping.PayOrderActivity;
import com.ylkj.shopproject.activity.user.evaluation.AddEvaluationActivity;
import com.ylkj.shopproject.activity.user.persenter.OrderPersenter;
import com.ylkj.shopproject.adapter.user.OrderDetailsAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.MyOrder;
import com.zxdc.utils.library.bean.OrderDetails;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
/**
 * 订单详情
 */
public class OrderDetailsActivity extends BaseActivity implements View.OnClickListener{

    private ImageView imgStatus;
    private TextView tvName,tvMobile,tvAddress,tvLeave,tvOrderCode,tvOrderTime,tvPayType,tvFpType,tvCompany,tvFpContent,tvFpCode,tvShopMoney,tvYfMoney,tvYhMoney,tvTotalMoney,tvKdName,tvKdCode,tvDel,tvPlay;
    private ListView listView;
    private OrderDetailsAdapter orderDetailsAdapter;
    //订单详情对象
    private OrderDetails orderDetails;
    //订单对象
    private MyOrder.DataBean dataBean;
    //MVP
    private OrderPersenter orderPersenter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        //实例化MVP
        orderPersenter=new OrderPersenter(this);
        initView();
        //获取订单详情接口
        orderDetails();
    }

    /**
     * 初始化
     */
    private void initView(){
        dataBean= (MyOrder.DataBean) getIntent().getSerializableExtra("dataBean");
        imgStatus=findViewById(R.id.img_status);
        tvName=findViewById(R.id.tv_name);
        tvMobile=findViewById(R.id.tv_mobile);
        tvAddress=findViewById(R.id.tv_address);
        listView=findViewById(R.id.listview);
        tvLeave=findViewById(R.id.tv_leave);
        tvOrderCode=findViewById(R.id.tv_order_code);
        tvOrderTime=findViewById(R.id.tv_order_time);
        tvPayType=findViewById(R.id.tv_pay_type);
        tvFpType=findViewById(R.id.tv_fp_type);
        tvCompany=findViewById(R.id.tv_company);
        tvFpContent=findViewById(R.id.tv_fp_content);
        tvFpCode=findViewById(R.id.tv_fp_code);
        tvShopMoney=findViewById(R.id.tv_shop_money);
        tvYfMoney=findViewById(R.id.tv_yf_money);
        tvYhMoney=findViewById(R.id.tv_yh_money);
        tvTotalMoney=findViewById(R.id.tv_total_money);
        tvKdName=findViewById(R.id.tv_kd_name);
        tvKdCode=findViewById(R.id.tv_kd_code);
        tvDel=findViewById(R.id.tv_del);
        tvPlay=findViewById(R.id.tv_play);
        tvDel.setOnClickListener(this);
        tvPlay.setOnClickListener(this);
        findViewById(R.id.tv_copy).setOnClickListener(this);
        findViewById(R.id.lin_back).setOnClickListener(this);
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                //查询数据回执
                case HandlerConstant.GET_ORDER_DETAILS_SUCCESS:
                     orderDetails= (OrderDetails) msg.obj;
                     if(null==orderDetails){
                         break;
                     }
                     if(orderDetails.isSussess()){
                         showData();
                     }else{
                         ToastUtil.showLong(orderDetails.getDesc());
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
     * 展示界面数据
     */
    private void showData(){
        OrderDetails.DataBean dataBean=orderDetails.getData();
        if(null==dataBean){
            return;
        }
        ImageView imgBg=findViewById(R.id.img_bg);
        TextView tvStatus=findViewById(R.id.tv_status);
        switch (dataBean.getStatus()){
            //待付款
            case 0:
                 tvStatus.setText("待付款");
                 imgBg.setImageDrawable(getResources().getDrawable(R.mipmap.dfk_bg));
                 break;
            //待发货
            case 1:
                tvStatus.setText("待发货");
                imgBg.setImageDrawable(getResources().getDrawable(R.mipmap.dfh_bg));
                imgStatus.setImageDrawable(getResources().getDrawable(R.mipmap.dfh_icon));
                findViewById(R.id.rel_bottom).setVisibility(View.GONE);
                break;
            //待收货
            case 2:
                tvStatus.setText("待收货");
                imgBg.setImageDrawable(getResources().getDrawable(R.mipmap.dsh_bg));
                imgStatus.setImageDrawable(getResources().getDrawable(R.mipmap.dsh_status));
                tvDel.setVisibility(View.GONE);
                tvPlay.setText("确认收货");
                break;
            //已完成
            case 3:
                tvStatus.setText("已完成");
                imgBg.setImageDrawable(getResources().getDrawable(R.mipmap.ywc_bg));
                imgStatus.setImageDrawable(getResources().getDrawable(R.mipmap.ywc_icon));
                tvPlay.setText("去评价");
                break;
            //已取消
            case 5:
                tvStatus.setText("已取消");
                imgBg.setImageDrawable(getResources().getDrawable(R.mipmap.yqx_bg));
                imgStatus.setImageDrawable(getResources().getDrawable(R.mipmap.yqx_icon));
                tvPlay.setText("删除订单");
                break;
        }

        //展示快递界面数据
        if(dataBean.getStatus()==2 || dataBean.getStatus()==3){
            findViewById(R.id.rel_kd).setVisibility(View.VISIBLE);
            tvKdName.setText(dataBean.getLogisticname());
            tvKdCode.setText(dataBean.getLogisiticcode());
        }

        //展示收货人地址信息
        tvName.setText(dataBean.getConsignee());
        tvMobile.setText(dataBean.getTelPhone());
        tvAddress.setText(dataBean.getAddress());

        //订单详情中的商品列表
        orderDetailsAdapter=new OrderDetailsAdapter(this,dataBean.getDetaillist());
        listView.setAdapter(orderDetailsAdapter);

        tvLeave.setText(dataBean.getRemark());
        tvOrderCode.setText(dataBean.getOrdercode());
        tvOrderTime.setText(dataBean.getOrderDate());
        switch (dataBean.getPayType()){
            case 6:
                 tvPayType.setText("支付宝支付");
                 break;
            case 7:
                tvPayType.setText("微信支付");
                 break;
            case 15:
                tvPayType.setText("对公支付");
                 break;
        }

        //展示发票信息
        switch (dataBean.getInvoiceType()){
            case 0:
                 tvFpType.setText("不需要发票");
                 findViewById(R.id.rel_fp).setVisibility(View.GONE);
                 break;
            case 1:
                tvFpType.setText("普通发票");
                 break;
            case 2:
                tvFpType.setText("增值税发票");
                 break;
        }
        tvCompany.setText(dataBean.getInvoiceTitle());
        tvFpContent.setText(dataBean.getInvoiceContent());
        tvFpCode.setText(dataBean.getTaxnum());

        tvShopMoney.setText("¥"+dataBean.getPrice());
        tvYfMoney.setText("¥"+dataBean.getFreight());
        tvYhMoney.setText("¥"+dataBean.getDiscount());
        tvTotalMoney.setText("总金额：¥"+dataBean.getActualpay());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //赋值
            case R.id.tv_copy:
                  break;
            //删除订单
            case R.id.tv_del:
                 orderPersenter.delOrder(dataBean);
                  break;
            //删除/支付/评价订单
            case R.id.tv_play:
                switch (dataBean.getStatus()){
                    //待付款
                    case 0:
                       //去支付
                        setClass(PayOrderActivity.class);
                        break;
                    //待收货
                    case 2:
                        orderPersenter.confirmGood(dataBean);
                        break;
                    //已完成
                    case 3:
                        setClass(AddEvaluationActivity.class);
                        break;
                    //已取消
                    case 5:
                        orderPersenter.delOrder(dataBean);
                        break;
                }
                  break;
            case R.id.lin_back:
                 finish();
                 break;
        }
    }


    /**
     * 获取订单详情接口
     */
    private void orderDetails(){
        HttpMethod.orderDetails(dataBean.getOid(),handler);
    }
}
