package com.example.jpm.librarydemo.tools.util;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {
    /**
     * @author lx
     * created at 2018/4/20 11:05
     * 作用：code 10000 get请求 如有多个请加0，1，2，3
     * code 10001 post请求 如有多个请加0，1，2，3
     * code 10009 下载文件 如有多个请加0，1，2，3
     */
    public static void okHttpGetMethod(final String url, final XCallBack callback, final int code) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                OkHttpClient okHttpClient = new OkHttpClient();
                final Request request = new Request.Builder()
                        .url(url)
                        .get()//默认就是GET请求，可以不写
                        .build();
                Call call = okHttpClient.newCall(request);

                call.enqueue(new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        if (callback != null) {
                            callback.onError(code);
                        }
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (callback != null) {
                            callback.onResponse(response.body().string(), code);
                        }
                    }
                });
            }
        }.start();
    }

    public static void okHttpPostMethod(final String url, final String bytes, final XCallBack callback, final int code) {
        // 创建客户端
        new Thread() {
            @Override
            public void run() {
                super.run();
                OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                        .connectTimeout(10, TimeUnit.SECONDS)//设置连接超时时间
                        .readTimeout(20, TimeUnit.SECONDS).build();//设置读取超时时间
                // 创建请求参数
                Request request = new Request.Builder().url(url).
                        post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=utf-8")
                                , bytes))
                        .addHeader("content-type", "binary/octet-stream")
                        .build();

                // 创建请求对象
                Call call = okHttpClient.newCall(request);
                // 发起请求
                call.enqueue(new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        callback.onError(code);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            String result = response.body().string();
                            callback.onResponse(result, code);
                        }
                    }
                });
            }
        }.start();

    }

    public interface XCallBack {
        void onResponse(String result, int code);

        void onError(int code);
    }

    public interface FCallBack {
        void onsuccess();

        void onError();

        void onLoading(long total, long current, boolean isDownloading);
    }
}
