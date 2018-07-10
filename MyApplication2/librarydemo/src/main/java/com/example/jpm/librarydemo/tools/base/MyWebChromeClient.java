package com.example.jpm.librarydemo.tools.base;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

/**
 * @author lx
 * created at 2018/4/17 11:52
 * 作用：网页浏览手机文件
 */
public class MyWebChromeClient extends WebChromeClient {
    public static final int REQUEST_FILE_PICKER = 1;
    public ValueCallback<Uri> mFilePathCallback;
    public ValueCallback<Uri[]> mFilePathCallbacks;
    Activity mContext;
    private ProgressBar progressBar;

    public MyWebChromeClient(Activity mContext, ProgressBar progressBar) {
        super();
        this.mContext = mContext;
        this.progressBar = progressBar;
    }

    // Android < 3.0 调用这个方法
    public void openFileChooser(ValueCallback<Uri> filePathCallback) {
        openFileChooser(filePathCallback, "");
    }

    // 3.0 + 调用这个方法
    public void openFileChooser(ValueCallback filePathCallback,
                                String acceptType) {
        mFilePathCallback = filePathCallback;
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        mContext.startActivityForResult(Intent.createChooser(intent, "File Chooser"),
                REQUEST_FILE_PICKER);
    }

    //  / js上传文件的<input type="file" name="fileField" id="fileField" />事件捕获
    // Android > 4.address.address 调用这个方法
    public void openFileChooser(ValueCallback<Uri> filePathCallback,
                                String acceptType, String capture) {
        openFileChooser(filePathCallback, acceptType);
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        if (newProgress == 100) {
            progressBar.setVisibility(View.GONE);//加载完网页进度条消失
        } else {
            progressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
            progressBar.setProgress(newProgress);//设置进度值
        }

    }

    //5.0
    @Override
    public boolean onShowFileChooser(WebView webView,
                                     ValueCallback<Uri[]> filePathCallback,
                                     FileChooserParams fileChooserParams) {
        mFilePathCallbacks = filePathCallback;
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        mContext.startActivityForResult(Intent.createChooser(intent, "File Chooser"),
                REQUEST_FILE_PICKER);
        return true;
    }

}
