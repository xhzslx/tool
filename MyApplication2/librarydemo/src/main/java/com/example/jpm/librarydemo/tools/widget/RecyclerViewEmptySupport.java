package com.example.jpm.librarydemo.tools.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.example.jpm.librarydemo.tools.util.LoggUtil;


/**
 * @author lx
 * created at 2018/4/17 11:54
 * 作用：有空布局的recyclerview
 */
public class RecyclerViewEmptySupport extends RecyclerView {

    private View emptyView;
    final private AdapterDataObserver observer = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            checkIfEmpty();
        }
    };

    public RecyclerViewEmptySupport(Context context) {
        super(context);
    }

    public RecyclerViewEmptySupport(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerViewEmptySupport(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        final Adapter oldAdapter = getAdapter();
        if (oldAdapter != null) {
            oldAdapter.unregisterAdapterDataObserver(observer);
        }
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(observer);
        }

        checkIfEmpty();
    }

    void checkIfEmpty() {
        if (emptyView != null && getAdapter() != null) {
            final boolean emptyViewVisible = getAdapter().getItemCount() == 0;
            emptyView.setVisibility(emptyViewVisible ? VISIBLE : GONE);
            setVisibility(emptyViewVisible ? GONE : VISIBLE);
        }
    }

    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
        LoggUtil.i("luoxiao",111+"");
        checkIfEmpty();
    }
    public void setEmptyViewhead(View emptyView) {
        this.emptyView = emptyView;
        if (emptyView != null && getAdapter() != null) {
            final boolean emptyViewVisible = getAdapter().getItemCount() == 1;
            LoggUtil.i("luoxiao12",getAdapter().getItemCount()+"");
            emptyView.setVisibility(emptyViewVisible ? VISIBLE : GONE);
            setVisibility(emptyViewVisible ? GONE : VISIBLE);
        }else{
            LoggUtil.i("luoxiao123",emptyView.getVisibility()+"");
            emptyView.setVisibility(VISIBLE);
            LoggUtil.i("luoxiao123",emptyView.getVisibility()+"");
            setVisibility(GONE);
        }
    }
}
