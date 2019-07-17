package com.ylkj.shopproject.activity.type;

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
import com.ylkj.shopproject.adapter.type.TypeAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.Type;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.io.Serializable;
import java.util.List;

/**
 * 底部菜单:分类
 */
public class TypeActivity extends BaseActivity {

    private ListView listView;
    private TypeAdapter typeAdapter;
    private List<Type.Children> list;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);
        initView();
        getType();
    }


    /**
     * 初始化
     */
    private void initView(){
        listView=findViewById(R.id.listView);
        //进入搜索界面
        findViewById(R.id.lin_search).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setClass(SearchActivity.class);
            }
        });
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                //查询分类回执
                case HandlerConstant.GET_TYPE_SUCCESS:
                    final Type type= (Type) msg.obj;
                    if(null==type){
                        break;
                    }
                    if(type.isSussess()){
                        list=type.getData();
                        typeAdapter=new TypeAdapter(TypeActivity.this,list);
                        listView.setAdapter(typeAdapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent=new Intent(TypeActivity.this,TypeDetailsActivity.class);
                                intent.putExtra("children",(Serializable)list.get(position).getChildren());
                                startActivity(intent);
                            }
                        });
                    }else{
                        ToastUtil.showLong(type.getDesc());
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
     * 查询分类
     * 分类类型(1:机床分类 2:配件分类 3:增值服务分类 4:生意圈分类 5:机床商品分类)
     */
    private void getType(){
        HttpMethod.getType("1",handler);
    }
}
