package com.ylkj.shopproject.activity.type;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.type.persenter.JCDetailsPersenter;
import com.ylkj.shopproject.activity.showimg.ShowImgActivity;
import com.ylkj.shopproject.adapter.type.JC_Details_Name_Adapter;
import com.ylkj.shopproject.adapter.type.JC_Details_Type_Adapter;
import com.ylkj.shopproject.adapter.type.SelectColorAdapter;
import com.ylkj.shopproject.eventbus.EventBusType;
import com.ylkj.shopproject.eventbus.EventStatus;
import com.ylkj.shopproject.view.HorizontalListView;
import com.youth.banner.Banner;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.JCGoodDetails;
import com.zxdc.utils.library.util.SPUtil;
import com.zxdc.utils.library.util.Util;
import com.zxdc.utils.library.view.MeasureListView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 机床详情页
 */
public class JCDetailsActivity extends BaseActivity implements View.OnClickListener{

    private Banner banner;
    private TextView tvDes,tvApp,tvColor,tvColorMoney,tvTotalMoney;
    private HorizontalListView listColor;
    private MeasureListView listType,listName;
    //选择颜色的adapter
    private SelectColorAdapter selectColorAdapter;
    //类型描述的adapter
    private JC_Details_Type_Adapter jc_details_type_adapter;
    //名称的adapter
    private JC_Details_Name_Adapter jc_details_name_adapter;
    //mvp实例
    private JCDetailsPersenter jcDetailsPersenter;
    //商品id
    private int spuid;
    //商品详情对象
    private JCGoodDetails jcGoodDetails;
    //展示类型数据的集合
    private List<JCGoodDetails.machineAttrsList> typeList=new ArrayList<>();
    //展示名称数据的集合
    private List<JCGoodDetails.machineAttrsList> nameList=new ArrayList<>();
    //选中颜色的费用
    private double colorMoney=0;
    //存储选中类型的费用
    public static Map<Integer,Double> typeMap=new HashMap<>();
    //存储选中名称的费用
    public static Map<Integer,Double> nameMap=new HashMap<>();
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jc_details);
        //注册eventbus
        EventBus.getDefault().register(this);
        //实例化MVP
        jcDetailsPersenter=new JCDetailsPersenter(this);
        initView();
        //查询机床商品详情
        jcDetailsPersenter.getJCDetails(spuid);
    }

    /**
     * 初始化
     */
    @SuppressLint("WrongViewCast")
    private void initView(){
        //获得商品id
        spuid=getIntent().getIntExtra("spuid",0);
        banner=findViewById(R.id.banner);
        tvDes=findViewById(R.id.tv_des);
        tvApp=findViewById(R.id.tv_app);
        tvColor=findViewById(R.id.tv_color);
        tvColorMoney=findViewById(R.id.tv_color_money);
        tvTotalMoney=findViewById(R.id.tv_total_money);
        listColor=findViewById(R.id.list_color);
        listName=findViewById(R.id.list_name);
        listType=findViewById(R.id.list_type);
        tvDes.setOnClickListener(this);
        tvApp.setOnClickListener(this);
        findViewById(R.id.img_kf).setOnClickListener(this);
        findViewById(R.id.img_collection).setOnClickListener(this);
        findViewById(R.id.lin_back).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //功能介绍
            case R.id.tv_des:
                 break;
            //平台消息
            case R.id.tv_app:
                  List<String> list=new ArrayList<>();
                  for (int i=0;i<jcDetailsPersenter.imgList.size();i++){
                        list.add(jcDetailsPersenter.imgList.get(i).getUrl());
                  }
                 Intent intent=new Intent(this, ShowImgActivity.class);
                 intent.putExtra("imgs", SPUtil.gson.toJson(list));
                 startActivity(intent);
                 break;
            //客服
            case R.id.img_kf:
                 break;
           //收藏
            case R.id.img_collection:
                 jcDetailsPersenter.isCollec(jcGoodDetails);
                 break;
            case R.id.lin_back:
                  finish();
                  break;
        }
    }


    /**
     * 展示界面数据
     */
    private void showUIData(){
        tvTotalMoney.setText("¥"+jcGoodDetails.getData().getPrice());
        //获取颜色列表对象
        JCGoodDetails.machineAttrsList colorList=null;
        for (int i=0;i<jcGoodDetails.getData().getMachineAttrs().size();i++){
              if(jcGoodDetails.getData().getMachineAttrs().get(i).getDirection().equals("2")){
                  colorList=jcGoodDetails.getData().getMachineAttrs().get(i);
                  break;
              }
        }

        //设置默认轮播图
        if(null!=colorList){
            jcDetailsPersenter.setBanner(banner,colorList.getMachineAttrValues().get(0).getSpuColorImgList());
        }

        //展示颜色数据
        if(colorList!=null){
            tvColor.setText(colorList.getName());
            colorMoney=colorList.getPrice();
            tvColorMoney.setText("¥"+ Util.setDouble(colorMoney,2));
            selectColorAdapter=new SelectColorAdapter(this,colorList.getMachineAttrValues());
            listColor.setAdapter(selectColorAdapter);
        }


        for (int i=0;i<jcGoodDetails.getData().getMachineAttrs().size();i++){
              //获取类型数据
              if(jcGoodDetails.getData().getMachineAttrs().get(i).getDirection().equals("0")){
                  typeList.add(jcGoodDetails.getData().getMachineAttrs().get(i));
              }
              //获取名称列表数据
             if(jcGoodDetails.getData().getMachineAttrs().get(i).getDirection().equals("1")){
                 nameList.add(jcGoodDetails.getData().getMachineAttrs().get(i));
             }
        }
        //展示类型数据
        jc_details_type_adapter=new JC_Details_Type_Adapter(this,typeList);
        listType.setAdapter(jc_details_type_adapter);

        //展示名称列表数据
        jc_details_name_adapter=new JC_Details_Name_Adapter(this,nameList);
        listName.setAdapter(jc_details_name_adapter);
    }


    /**
     * EventBus注解
     */
    @Subscribe
    public void onEvent(EventBusType eventBusType){
        switch (eventBusType.getStatus()){
            //获得商品详情
            case EventStatus.GET_JC_DETAILS:
                  jcGoodDetails= (JCGoodDetails) eventBusType.getObject();
                  showUIData();
                  break;
            //选择对应颜色
            case EventStatus.SELECT_JC_COLOR:
                  final JCGoodDetails.machineValueList machineValueList= (JCGoodDetails.machineValueList) eventBusType.getObject();
                  colorMoney=machineValueList.getPrice();
                  tvColorMoney.setText("¥"+ Util.setDouble(colorMoney,2));
                  //展示总的费用
                  getTotalMoney();
                  //设置轮播图
                  jcDetailsPersenter.setBanner(banner,machineValueList.getSpuColorImgList());
                  break;
            //选完颜色，类型，名称后，展示总的费用
            case EventStatus.JC_TOTAL_MONEY:
                  getTotalMoney();
                  break;
        }
    }


    /**
     * 展示总的费用
     */
    private void getTotalMoney(){
        double totalMoney=0;
        //加上颜色费用
        totalMoney=Util.sum(totalMoney,colorMoney);
        //加上类型费用
        Set<Integer> typeSet = typeMap.keySet();
        for (Integer in : typeSet){
            totalMoney=Util.sum(totalMoney,typeMap.get(in));
        }
        //加上名称费用
        Set<Integer> nameSet = nameMap.keySet();
        for (Integer in : nameSet){
            totalMoney=Util.sum(totalMoney,nameMap.get(in));
        }
        tvTotalMoney.setText("¥"+totalMoney);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
