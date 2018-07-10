package com.example.jpm.librarydemo.tools.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Calendar;

@SuppressLint("AppCompatCustomView")
public class CommitTextView extends TextView {
    public static final int MIN_CLICK_DELAY_TIME = 500;//这里设置不能超过多长时间
    private long lastClickTime = 0;
    public CommitTextView(Context context) {
        super(context);
    }

    public CommitTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CommitTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CommitTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            super.setOnClickListener(l);
        }
    }
}
