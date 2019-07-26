package com.ylkj.shopproject.activity.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.main.persenter.PeiJianDetailsPersenter;
import com.ylkj.shopproject.adapter.main.PeiJianDetailsTypeAdapter;
import com.youth.banner.Banner;
import com.zxdc.utils.library.base.BaseFragment;
import com.zxdc.utils.library.bean.Coupon;
import com.zxdc.utils.library.bean.PJGoodDetails;
import com.zxdc.utils.library.view.MeasureListView;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;
/**
 * 配件商品详情
 */
public class PeiJianGoodFragment extends BaseFragment implements View.OnClickListener{

    private Banner banner;
    private TextView tvName,tvDes,tvNewMoney,tvOldMoney,tvYfMoney,tvNum;
    private MeasureListView listView;
    private PeiJianDetailsTypeAdapter peiJianDetailsTypeAdapter;
    //MVP实例
    private PeiJianDetailsPersenter peiJianDetailsPersenter;
    //商品详情对象
    private PJGoodDetails pjGoodDetails;
    //商品数量
    private int num=1;
    //商品id
    private int spuid;
    //选中优惠券的对象
    private Coupon.DataBean coupon;
    //选中的sku规格值
    private Map<Integer,String> selectSku=new HashMap<>();
    //选中的skuid
    private String skuid;
    //选中规格的单价
    private double skuPrice=0;
    //选中规格的库存
    private int skuCount;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册eventbus
        EventBus.getDefault().register(this);
        //实例化MVP
        peiJianDetailsPersenter=new PeiJianDetailsPersenter(mActivity);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_peijian_details, container, false);
        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
