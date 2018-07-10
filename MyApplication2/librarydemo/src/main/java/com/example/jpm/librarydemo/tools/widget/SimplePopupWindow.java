package com.example.jpm.librarydemo.tools.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.PopupWindow;
import android.widget.Toast;

/**
 * @author lx
 * created at 2018/4/17 11:55
 * 作用：简单的PopupWindow
 */
public class SimplePopupWindow {
    private Activity mActivity;
    private View mView;
    private boolean mIsShowInput;
    private int animationStyle;
    private int LOCATION = -1;


    public SimplePopupWindow(Activity activity) {
        mActivity = activity;
        animationStyle = -1;
    }

    public static SimplePopupWindow with(Activity activity) {
        return new SimplePopupWindow(activity);
    }

    //设置弹出位置，默认底部弹出
    public void setLocation(int Lacation) {
        LOCATION = Lacation;
    }


    //视图资源,必须设置
    public SimplePopupWindow setView(int ResId) {
        mView = LayoutInflater.from(mActivity).inflate(ResId, null);
        return this;
    }


    //弹出PopupWindow
    public void show(Callback callback, View view) {
        if (mView == null) {
            Toast.makeText(mActivity, "请设置View", Toast.LENGTH_SHORT).show();
            return;
        }

        PopupWindow window = new PopupWindow(mView, view.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT, true);

        if (animationStyle != -1)  //如果设置了动画，则启用动画
            window.setAnimationStyle(animationStyle);

        window.setBackgroundDrawable(new ColorDrawable(Color.GRAY));

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);  //不会被软键盘覆盖

        window.setOnDismissListener(new PopupWindow.OnDismissListener() { //弹窗消失时恢复背景色
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1f);
            }
        });

        callback.getView(mView, window);

        if (LOCATION != -1)
            window.showAtLocation(mActivity.getWindow().getDecorView().findViewById(android.R.id.content), LOCATION, 0, 0);
        else
            window.showAtLocation(mActivity.getWindow().getDecorView().findViewById(android.R.id.content), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

        showInPut();
    }

    //设置黑色半透明背景，建议设置0.4f,默认不设置
    public SimplePopupWindow setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        if (bgAlpha == 1) {
            mActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//不移除该Flag的话,在有视频的页面上的视频会出现黑屏的bug
        } else {
            mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//此行代码主要是解决在华为手机上半透明效果无效的bug
        }
        mActivity.getWindow().setAttributes(lp);
        return this;
    }

    //显示软键盘
    private void showInPut() {
        if (mIsShowInput) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }, 0);
        }
    }

    public void show(Callback callback, View view, float f) {
        if (mView == null) {
            Toast.makeText(mActivity, "请设置View", Toast.LENGTH_SHORT).show();
            return;
        }

        PopupWindow window = new PopupWindow(mView, (int) (view.getWidth() * f),
                ViewGroup.LayoutParams.WRAP_CONTENT, true);

        if (animationStyle != -1)  //如果设置了动画，则启用动画
            window.setAnimationStyle(animationStyle);

        window.setBackgroundDrawable(new ColorDrawable(Color.GRAY));

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);  //不会被软键盘覆盖

        window.setOnDismissListener(new PopupWindow.OnDismissListener() { //弹窗消失时恢复背景色
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1f);
            }
        });

        callback.getView(mView, window);

        if (LOCATION != -1)
            window.showAtLocation(mActivity.getWindow().getDecorView().findViewById(android.R.id.content), LOCATION, 0, 0);
        else
            window.showAtLocation(mActivity.getWindow().getDecorView().findViewById(android.R.id.content), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

        showInPut();
    }

    //设置是否自动弹出软键盘,默认为否
    public SimplePopupWindow setAutoPopupInput(boolean isShowInput) {
        mIsShowInput = isShowInput;
        return this;
    }

    //设置进出动画
    public SimplePopupWindow setAnimationStyle(int animationStyle) {
        this.animationStyle = animationStyle;
        return this;
    }


    //接口回调
    public interface Callback {
        void getView(View view, PopupWindow popupWindow);
    }
}
