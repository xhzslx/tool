package com.example.jpm.librarydemo.tools.util;

import android.widget.EditText;

public class ViewUtil {
    public static void setEdittextFocus(EditText edittextEnFocus, boolean flag) {
        edittextEnFocus.setFocusable(flag);
        edittextEnFocus.setCursorVisible(flag);
        edittextEnFocus.setFocusableInTouchMode(flag);
    }
}
