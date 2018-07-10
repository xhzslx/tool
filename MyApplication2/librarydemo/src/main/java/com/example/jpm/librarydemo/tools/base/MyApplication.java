package com.example.jpm.librarydemo.tools.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.LinkedList;
import java.util.List;

/**
 * @author lx
 * created at 2018/4/17 11:19
 * 作用：应用类
 */
public class MyApplication extends Application {

    static List<Activity> mlist = new LinkedList<Activity>();
    static MyApplication instance;
    private static Context mContext;

    public MyApplication() {

    }

    public static Context getContext() {
        return mContext;
    }

    // 创建Application 单个实例
    public synchronized static MyApplication getInstance() {
        if (null == instance) {
            instance = new MyApplication();
        }
        return instance;
    }

    //遍历退出所有的activity
    public static void exit() {
        try {
            for (Activity activity : mlist) {
                if (activity != null) {
                    activity.finish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    public static void delet() {
        for (Activity activity : mlist) {
            if (activity != null) {
                activity.finish();
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        //LeakCanary.install(this);
//        QPManager.getInstance(getApplicationContext()).initRecord();
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @SuppressLint("ResourceType")
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(ResUtil.getColorId(context, "refresh_color"),
                        ResUtil.getColorId(context, "white"));
                layout.setDragRate(1.2f);
                layout.setHeaderHeight(60);
                return new ClassicsHeader(mContext).setTimeFormat(new DynamicTimeFormat("更新于 %s"));
            }
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                return new ClassicsFooter(mContext);
            }
        });
    }

    //当内存低的时候 自动回收内存
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

    @Override
    public void onTrimMemory(int level) {
        // TODO Auto-generated method stub
        super.onTrimMemory(level);
    }

    //把所有的Activity都加入到容器列表中
    public void addActivity(Activity activity) {
        mlist.add(activity);
    }

}
