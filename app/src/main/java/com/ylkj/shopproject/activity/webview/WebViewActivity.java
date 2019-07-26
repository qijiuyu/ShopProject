package com.ylkj.shopproject.activity.webview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.ylkj.shopproject.R;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.http.HttpConstant;
import com.zxdc.utils.library.util.LogUtils;

public class WebViewActivity extends BaseActivity {

    private WebView webshow;
    private TextView tvTitle;
    private String url,title;
    private int type;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        initView();
    }

    @SuppressLint("JavascriptInterface")
    private void initView(){
        type=getIntent().getIntExtra("type",0);
        url=getIntent().getStringExtra("url");
        title=getIntent().getStringExtra("title");
        webshow =findViewById(R.id.webview);
        tvTitle=findViewById(R.id.tv_title);
        webshow.getSettings().setJavaScriptEnabled(true);
        webshow.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webshow.getSettings().setSupportMultipleWindows(true);
        webshow.getSettings().setBuiltInZoomControls(true);
        webshow.setWebViewClient(new WebViewClient());
        webshow.setWebChromeClient(new WebChromeClient());

        switch (type){
            case 1:
                 tvTitle.setText(title);
                 webshow.loadUrl(url);
                 break;
           //平台消息详情
            case 2:
                  tvTitle.setText("消息详情");
                  webshow.loadUrl(HttpConstant.IP+"api/app/html/messageinfo?id="+url);
                  break;
            //关于我们
            case 3:
                  tvTitle.setText("关于我们");
                  webshow.loadUrl(HttpConstant.IP+"api/app/html/newsinfo?type=0");
                  break;
            //融资租赁
            case 4:
                tvTitle.setText("融资租赁");
                webshow.loadUrl(HttpConstant.IP+"api/app/html/newsinfo?type=1");
                break;
            //注册协议
            case 5:
                tvTitle.setText("注册协议 ");
                webshow.loadUrl(HttpConstant.IP+"api/app/html/newsinfo?type=2");
                break;
        }


        findViewById(R.id.lin_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                WebViewActivity.this.finish();
            }
        });
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webshow.canGoBack()) {
            webshow.goBack();
        }
        return super.onKeyDown(keyCode, event);
    }
}
