package com.ylkj.shopproject.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

public class TileView extends android.support.v7.widget.AppCompatTextView {
    public TileView(Context context) {
        super(context);
    }

    public TileView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TileView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void onDraw(Canvas canvas) {
        //倾斜度45,上下左右居中
        canvas.rotate(10, getMeasuredWidth() / 2, getMeasuredHeight() / 2);
        super.onDraw(canvas);
    }
}
