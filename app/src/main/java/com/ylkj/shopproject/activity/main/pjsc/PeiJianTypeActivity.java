package com.ylkj.shopproject.activity.main.pjsc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.activity.main.search.SearchActivity;
import com.ylkj.shopproject.adapter.main.PeiJianDataAdapter;
import com.ylkj.shopproject.adapter.main.PeiJianTypeAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.PJType;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.ToastUtil;
import java.util.List;
/**
 * 配件商城分类
 */
public class PeiJianTypeActivity extends BaseActivity implements View.OnClickListener{

    private ListView listType,listData;
    //左边分类的adapter
    private PeiJianTypeAdapter peiJianTypeAdapter;
    //右边分类下的数据的adapter
    private PeiJianDataAdapter peiJianDataAdapter;
    //左边分类集合
    private List<PJType.dataBean> typeList;
    //右边数据集合
    private List<PJType.Children> dataList;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peijian_type);
        initView();
        //查询分类
        getType();
    }

    /**
     * 初始化
     */
    private void initView(){
        listType=findViewById(R.id.list_type);
        listData=findViewById(R.id.list_data);
        findViewById(R.id.lin_back).setOnClickListener(this);
        findViewById(R.id.lin_search).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //进入搜索界面
            case R.id.lin_search:
                setClass(SearchActivity.class);
                break;
            case R.id.lin_back:
                finish();
                break;
        }
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                //查询分类回执
                case HandlerConstant.GET_TYPE_SUCCESS:
                    final PJType pjType= (PJType) msg.obj;
                    if(null==pjType){
                        break;
                    }
                    if(pjType.isSussess()){
                       showData(pjType);
                    }else{
                        ToastUtil.showLong(pjType.getDesc());
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
     * 展示左右两侧的数据
     * @param pjType
     */
    private void showData(PJType pjType){
        //展示左边分类列表
        typeList=pjType.getData();
        peiJianTypeAdapter=new PeiJianTypeAdapter(PeiJianTypeActivity.this,typeList);
        listType.setAdapter(peiJianTypeAdapter);

        //显示右边对应分类的数据
        dataList=typeList.get(0).getChildren();
        peiJianDataAdapter=new PeiJianDataAdapter(PeiJianTypeActivity.this,dataList);
        listData.setAdapter(peiJianDataAdapter);
        listData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PJType.Children children=dataList.get(position);
                Intent intent=new Intent(PeiJianTypeActivity.this,PeiJianListActivity.class);
                intent.putExtra("typeId",children.getPid());
                startActivity(intent);
            }
        });

        //点击分类显示对应数据
        listType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //刷新左侧数据选中颜色
                peiJianTypeAdapter.setIndex(position);
                peiJianTypeAdapter.notifyDataSetChanged();
                //刷新右侧数据
                dataList=typeList.get(position).getChildren();
                peiJianDataAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 查询分类
     */
    private void getType(){
        HttpMethod.getpJType("2",handler);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeHandler(handler);
    }
}
