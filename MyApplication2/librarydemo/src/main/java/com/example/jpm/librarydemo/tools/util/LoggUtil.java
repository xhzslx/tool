package com.example.jpm.librarydemo.tools.util;

import android.util.Log;

public class LoggUtil {
    public static final boolean DEBUG = true;

    private LoggUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void v(String tag, String message) {
        if (DEBUG) {
            Log.v(tag, message);
        }
    }

    public static void d(String tag, String message) {
        if (DEBUG) {
            Log.d(tag, message);
        }
    }

    public static void i(String tag, String message) {
        if (DEBUG) {
            Log.i(tag, message);
        }
    }

    public static void w(String tag, String message) {
        if (DEBUG) {
            Log.w(tag, message);

        }
    }

    public static void e(String tag, String message) {
        if (DEBUG) {
            Log.e(tag, message);
        }
    }
}
