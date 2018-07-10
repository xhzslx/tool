package com.example.jpm.librarydemo.tools.util;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.example.jpm.librarydemo.tools.base.ResUtil;
import com.example.jpm.librarydemo.tools.widget.DivItemDecoration;
import com.example.jpm.librarydemo.tools.widget.RecyclerViewEmptySupport;
import com.zhy.adapter.recyclerview.CommonAdapter;


public class RecyclerViewUtil {
    private static LinearLayoutManager layoutManager;

    public static <T> void setRecyclerViewEmpty(Activity context, RecyclerViewEmptySupport recyclerView,
                                                int divheight, CommonAdapter<T> adapter,
                                                String orientation, boolean hasHeand, View view) {
        layoutManager = new LinearLayoutManager(context);
        if (!TextUtils.isEmpty(orientation)) {
            layoutManager.setOrientation(Integer.parseInt(orientation));
        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DivItemDecoration(divheight, hasHeand));
        recyclerView.setEmptyView(view);
        recyclerView.setAdapter(adapter);
    }

}
