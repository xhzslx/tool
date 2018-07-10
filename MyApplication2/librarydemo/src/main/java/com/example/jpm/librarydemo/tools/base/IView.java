package com.example.jpm.librarydemo.tools.base;

public interface IView<T> {
    void setSuccessData2View(T t, int code);

    void setErrorData2View(int code);
}
