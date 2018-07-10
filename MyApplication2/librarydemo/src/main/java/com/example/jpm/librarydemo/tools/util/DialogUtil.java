package com.example.jpm.librarydemo.tools.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DialogUtil {

    static AlertDialog.Builder builderSec;
    private static onButtonClick mButtonClickCallBack;

    public static void commontDialogShow(Context context, String title, String message, final int code) {
        builderSec = new AlertDialog.Builder(context);
        builderSec.setTitle(title);
        builderSec.setMessage(message);
        builderSec.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                mButtonClickCallBack.OnPositive(dialog,code);
            }
        });
        builderSec.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mButtonClickCallBack.OnNegative(dialog,code);
            }
        });
        builderSec.show();
    }

    public static void setOnButtonClick(onButtonClick mNegativeCallBack) {
        mButtonClickCallBack = mNegativeCallBack;
    }

    public interface onButtonClick {
        void OnPositive(DialogInterface dialog, int code);

        void OnNegative(DialogInterface dialog, int code);
    }

}
