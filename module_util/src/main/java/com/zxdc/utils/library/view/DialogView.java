package com.zxdc.utils.library.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import com.zxdc.utils.library.R;

/**
 * Created by Administrator on 2017/3/24 0024.
 */

public class DialogView extends Dialog implements View.OnClickListener {

    private TextView tvContent,tvConfirm,tvCancle;
    private String confirm,cancle;
    private CharSequence content;
    private View.OnClickListener listener_yes;
    private View.OnClickListener listener_no;

    public DialogView(Context mContext, CharSequence  content, String confirm, String cancle, View.OnClickListener listenerYes, View.OnClickListener listenerNo){
        super(mContext);
        this.content=content;
        this.confirm=confirm;
        this.cancle=cancle;
        this.listener_yes=listenerYes;
        this.listener_no=listenerNo;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog);
        Window dialogWindow = getWindow();
        dialogWindow.setWindowAnimations(R.style.mystyle);  //添加动画
        // 不可以用“返回键”取消
        this.setCancelable(false);
        this.setCanceledOnTouchOutside(false) ;
        initView();
    }


    /**
     * 初始化控件
     */
    private void initView(){
        tvContent=(TextView)findViewById(R.id.tv_dialog_content);
        tvConfirm=(TextView)findViewById(R.id.tv_confirm);
        tvCancle=(TextView)findViewById(R.id.tv_cancle);
        tvContent.setText(content);
        tvConfirm.setText(confirm);
        tvCancle.setText(cancle);
        if(cancle==null){
            tvCancle.setVisibility(View.GONE);
        }
        if(listener_yes!=null){
            tvConfirm.setOnClickListener(listener_yes);
        }else{
            tvConfirm.setOnClickListener(this);
        }
        if(listener_no!=null){
            tvCancle.setOnClickListener(listener_no);
        }else{
            tvCancle.setOnClickListener(this);
        }
    }

    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_confirm) {
            dismiss();

        } else if (i == R.id.tv_cancle) {
            dismiss();

        } else {
        }
    }
}
