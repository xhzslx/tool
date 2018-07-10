package com.example.jpm.librarydemo.tools.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @author lx
 * created at 2018/4/17 11:25
 * 作用：GridView边框
 */

public class MyGridView extends GridView {
    public MyGridView(Context context) {
        super(context);
    }


    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public MyGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightSpec;
        if (getLayoutParams().height == LayoutParams.WRAP_CONTENT) {
            heightSpec = MeasureSpec.makeMeasureSpec(
                    Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        } else {
            // Any other height should be respected as is.
            heightSpec = heightMeasureSpec;
        }
        super.onMeasure(widthMeasureSpec, heightSpec);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        int childCount = getChildCount();//子view的总数
        Paint localPaint;//画笔
        localPaint = new Paint();
        localPaint.setStyle(Paint.Style.STROKE);
        localPaint.setColor(Color.GRAY);
//		 设置画笔的颜色
        for (int i = 0; i < childCount; i++) {
            //遍历子view
            if (i % 3 == 2) {
                if (i != childCount) {
                    canvas.drawLine(getChildAt(i - 2).getLeft(), getChildAt(i - 2).getBottom(),
                            getChildAt(i).getRight() - 20, getChildAt(i).getBottom(), localPaint);
                }

            }


        }
    }
}
