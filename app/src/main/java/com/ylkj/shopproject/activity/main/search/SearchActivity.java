package com.ylkj.shopproject.activity.main.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.ylkj.shopproject.R;
import com.ylkj.shopproject.adapter.main.SearchAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.util.SPUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.util.Util;
import java.util.ArrayList;
import java.util.List;
/**
 * 首页搜索
 */
public class SearchActivity extends BaseActivity implements TextView.OnEditorActionListener{
    private EditText etKey;
    private ListView listView;
    private String strKey;
    //关键字集合
    private List<String> keyList=new ArrayList<>();
    private SearchAdapter searchAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView(){
        etKey=findViewById(R.id.et_keys);
        listView=findViewById(R.id.listView);
        etKey.setOnEditorActionListener(this);
        //取消
        findViewById(R.id.tv_cancle).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SearchActivity.this.finish();
            }
        });
        //清空所有历史记录
        findViewById(R.id.tv_clear).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                keyList.clear();
                SPUtil.getInstance(SearchActivity.this).removeMessage(SPUtil.SEARCH_KEY);
                if(null!=searchAdapter){
                    searchAdapter.notifyDataSetChanged();
                }
            }
        });
    }


    /**
     * 监听点击键盘搜索键
     */
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH){
            strKey= Util.format(etKey.getText().toString().trim());
            if(TextUtils.isEmpty(strKey)){
                ToastUtil.showLong("请输入要搜索的关键字！");
                return false;
            }
            //关闭键盘
            lockKey(etKey);
            //保存搜索过的关键字
            addTabKey();
            //跳转搜索页开始搜索
            Intent intent=new Intent(this,SearchResultActivity.class);
            intent.putExtra("keys",strKey);
            startActivity(intent);
        }
        return false;
    }


    /**
     * 保存搜索过的关键字
     */
    private void addTabKey(){
        String keys= SPUtil.getInstance(this).getString(SPUtil.SEARCH_KEY);
        if(!TextUtils.isEmpty(keys)){
            keyList=SPUtil.gson.fromJson(keys,List.class);
        }
        //清除重复关键字
        for (int i=0,len=keyList.size();i<len;i++){
            if(keyList.get(i).equals(strKey)){
                keyList.remove(i);
                break;
            }
        }
        keyList.add(strKey);
        SPUtil.getInstance(this).addString(SPUtil.SEARCH_KEY,SPUtil.gson.toJson(keyList));
    }


    /**
     * 展示搜索过的关键字
     */
    private void showKyes(){
        String keys= SPUtil.getInstance(this).getString(SPUtil.SEARCH_KEY);
        if(!TextUtils.isEmpty(keys)) {
            keyList=SPUtil.gson.fromJson(keys,List.class);
            searchAdapter=new SearchAdapter(this, keyList, new SearchAdapter.onClick() {
                public void delete(int position) {
                    keyList.remove(position);
                    SPUtil.getInstance(SearchActivity.this).addString(SPUtil.SEARCH_KEY,SPUtil.gson.toJson(keyList));
                    searchAdapter.notifyDataSetChanged();
                }
            });
            listView.setAdapter(searchAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //跳转搜索页开始搜索
                    Intent intent=new Intent(SearchActivity.this,SearchResultActivity.class);
                    intent.putExtra("keys",keyList.get(position));
                    startActivity(intent);
                }
            });
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        showKyes();
    }
}
