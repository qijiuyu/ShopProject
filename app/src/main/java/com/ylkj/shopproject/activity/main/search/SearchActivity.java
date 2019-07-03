package com.ylkj.shopproject.activity.main.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.ylkj.shopproject.adapter.main.SearchAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.SPUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

            setClass(SearchResultActivity.class);
        }
        return false;
    }


    /**
     * 保存搜索过的关键字
     */
    private void addTabKey(){
        String keys= SPUtil.getInstance(this).getString(SPUtil.SEARCH_KEY);
        Map<String,String> keyMap;
        if(!TextUtils.isEmpty(keys)){
            keyMap=SPUtil.gson.fromJson(keys,Map.class);
        }else{
            keyMap=new HashMap<>();
        }
        keyMap.put(strKey,strKey);
        SPUtil.getInstance(this).addString(SPUtil.SEARCH_KEY,SPUtil.gson.toJson(keyMap));
    }


    /**
     * 展示搜索过的关键字
     */
    private void showKyes(){
        String keys= SPUtil.getInstance(this).getString(SPUtil.SEARCH_KEY);
        if(!TextUtils.isEmpty(keys)) {
            Map<String, String> keyMap = SPUtil.gson.fromJson(keys, Map.class);
            Set<String> setKey = keyMap.keySet();
            for (String key : setKey) {
                 keyList.add(keyMap.get(key));
            }
            searchAdapter=new SearchAdapter(this, keyList, new SearchAdapter.onClick() {
                public void delete(int position) {
                    keyList.remove(position);
                    searchAdapter.notifyDataSetChanged();
                }
            });
            listView.setAdapter(searchAdapter);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        showKyes();
    }
}
