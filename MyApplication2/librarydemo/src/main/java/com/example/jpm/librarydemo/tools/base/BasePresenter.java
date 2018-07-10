package com.example.jpm.librarydemo.tools.base;


public class BasePresenter<T> {
    private T mview;

    public void detach() {
        if (mview != null) {
            mview = null;
        }
    }

    public void attach(T view) {

        mview = view;
    }

}
