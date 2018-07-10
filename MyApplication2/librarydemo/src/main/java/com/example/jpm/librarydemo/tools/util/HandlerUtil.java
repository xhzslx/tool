package com.example.jpm.librarydemo.tools.util;

import android.os.Handler;
import android.os.Message;

public class HandlerUtil {

    public static void sendMessage(Handler handler, int what) {
        Message message = Message.obtain();
        if (what != -1) {
            message.what = what;
        }
        handler.sendMessage(message);
    }

    public static void sendMessage(Handler handler, int what, String result) {
        Message message = Message.obtain();
        if (what != -1) {
            message.what = what;
        }
        message.obj = result;
        handler.sendMessage(message);
    }
}
