package com.example.jpm.librarydemo.tools.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author lx
 * created at 2018/4/17 14:13
 * 作用：intent跳转工具类
 */
public class IntentActionUtil {

    private static final String IMAGE_TYPE = "image/*";
    private static Iterator<? extends Map.Entry<String, ?>> iterator;

    /**
     * 打开指定类型的文件的Intent
     * param - filePath : 文件路径：例如，
     */
    public static Intent openFileIntent(String filePath) {
        if (isFileExit(filePath)) {
            String endName = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length()).toLowerCase();//后缀名
            /* 依扩展名的类型决定MimeType */
            if (endName.equals("m4a") || endName.equals("mp3") || endName.equals("mid") ||
                    endName.equals("xmf") || endName.equals("ogg") || endName.equals("wav") || endName.equals("amr")) {
                return getAudioFileIntent(filePath);//播放音频
            } else if (endName.equals("3gp") || endName.equals("mp4")) {
                return getVideoFileIntent(filePath);//播放视频
            } else if (endName.equals("jpg") || endName.equals("gif") || endName.equals("png") ||
                    endName.equals("jpeg") || endName.equals("bmp")) {
                return getImageFileIntent(filePath);//打开图片
            } else if (endName.equals("apk")) {
                return getApkFileIntent(filePath);//安装软件
            } else if (endName.equals("ppt") || endName.equals("pptx")) {
                return getPptFileIntent(filePath);//打开PPT文档
            } else if (endName.equals("xls") || endName.equals("xlsx")) {
                return getExcelFileIntent(filePath);//打开excel文档
            } else if (endName.equals("doc") || endName.equals("docx")) {
                return getWordFileIntent(filePath);//打开doc文档
            } else if (endName.equals("pdf")) {
                return getPdfFileIntent(filePath);//打开PDF文档
            } else if (endName.equals("chm")) {
                return getChmFileIntent(filePath);//打开CHM文档
            } else if (endName.equals("txt")) {
                return getTextFileIntent(filePath);//打开txt文档
            } else if (endName.equals("zip")) {
                return getZipFileIntent(filePath);//打开zip压缩包
            } else if (endName.equals("rar")) {
                return getRarFileIntent(filePath);//打开rar压缩包
            } else if (endName.equals("html") || endName.equals("htm")) {
                return getHtmlFileIntent(filePath);//打开html文件
            } else {
                return getAllIntent(filePath);//打开其他的文件
            }
        } else {
            return null;
        }
    }

    /**
     * 判断文件是否存在
     * param - filePath:文件路径
     */
    private static boolean isFileExit(String filePath) {
        if (filePath == null) {
            return false;
        }
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return true;
    }

    /**
     * Android获取一个用于打开AUDIO（音频）文件的intent
     */
    private static Intent getAudioFileIntent(String param) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, "audio/*");
        return intent;
    }

    /**
     * Android获取一个用于打开VIDEO（视频）文件的intent
     */
    private static Intent getVideoFileIntent(String filePath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = Uri.fromFile(new File(filePath));
        intent.setDataAndType(uri, "video/*");
        return intent;
    }

    /**
     * Android获取一个用于打开图片文件的intent
     */
    private static Intent getImageFileIntent(String filePath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(filePath));
        intent.setDataAndType(uri, "image/*");
        return intent;
    }

    /**
     * Android获取一个用于安装APK文件的intent
     */
    private static Intent getApkFileIntent(String filePath) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(new File(filePath));
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        return intent;
    }

    /**
     * Android获取一个用于打开PPT文件的intent
     */
    private static Intent getPptFileIntent(String filePath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(filePath));
        intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
        return intent;
    }

    /**
     * Android获取一个用于打开Excel文件的intent
     */
    private static Intent getExcelFileIntent(String filePath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(filePath));
        intent.setDataAndType(uri, "application/vnd.ms-excel");
        return intent;
    }

    /**
     * Android获取一个用于打开Word文件的intent
     */
    private static Intent getWordFileIntent(String filePath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(filePath));
        intent.setDataAndType(uri, "application/msword");
        return intent;
    }

    /**
     * Android获取一个用于打开PDF文件的intent
     */
    private static Intent getPdfFileIntent(String filePath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(filePath));
        intent.setDataAndType(uri, "application/pdf");
        return intent;
    }

    /**
     * Android获取一个用于打开CHM文件的intent
     */
    private static Intent getChmFileIntent(String filePath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(filePath));
        intent.setDataAndType(uri, "application/x-chm");
        return intent;
    }

    /**
     * Android获取一个用于打开文本文件的intent
     */
    private static Intent getTextFileIntent(String filePath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(filePath));
        intent.setDataAndType(uri, "text/plain");
        return intent;
    }

    /**
     * Android获取一个用于打开ZIP文件的intent
     */
    private static Intent getZipFileIntent(String filePath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(filePath));
        intent.setDataAndType(uri, "application/zip");
        return intent;
    }

    /**
     * Android获取一个用于打开Rar文件的intent
     */
    private static Intent getRarFileIntent(String filePath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(filePath));
        intent.setDataAndType(uri, "application/rar");
        return intent;
    }

    /**
     * Android获取一个用于打开Html文件的intent【有点儿问题，无法实现选择浏览器查看预览效果，且在Android6.0上无法通过“HTML查看程序”进行查看】
     */
    private static Intent getHtmlFileIntent(String filePath) {
        Uri uri = Uri.parse(filePath).buildUpon().encodedAuthority("com.android.htmlfileprovider")
                .scheme("content").encodedPath(filePath).build();//content://com.android.htmlfileprovider/storage/emulated/0/intentFile/htmldemo.html
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setDataAndType(uri, "text/html");
        return intent;
    }

    /**
     * Android获取一个用于打开任意文件的intent
     */
    private static Intent getAllIntent(String filePath) {

        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(new File(filePath));
        intent.setDataAndType(uri, "*/*");
        return intent;
    }

    /**
     * 调用发邮件的Intent
     * param sendToEmail - 邮件主送人的地址
     * return
     */
    public static Intent getEmailIntent(String sendToEmail) {
        Uri emailUri = Uri.parse(sendToEmail);
        Intent intent = new Intent(Intent.ACTION_SENDTO, emailUri);
        return intent;
    }

    /**
     * 调用浏览器打开网页的Intent
     * <p>
     * param url - 网址：例如，http://www.baidu.com
     * return
     */
    public static Intent getWebViewIntent(String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        return intent;
    }

    /**
     * 调用地图软件显示地图定位的Intent
     * param x - 定位X坐标：116.398064
     * param y - 定位Y坐标：39.913703
     * return
     */
    public static Intent getMapViewIntent(double x, double y) {
        Uri uri = Uri.parse("geo:" + x + "," + y);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        return intent;
    }

    /**
     * 打开拨号程序，拨打电话的Intent
     * <p>
     * param telphoneNum - 电话号码
     * return
     */
    public static Intent getPhoneIntent(Context context, String telphoneNum) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        //url:统一资源定位符
        //uri:统一资源标示符（更广）
        intent.setData(Uri.parse("tel:" + telphoneNum));
        //开启系统拨号器
        context.startActivity(intent);
        return intent;
    }

    /**
     * 打开短信程序，发送短信的Intent
     * <p>
     * param telphoneNum - 电话号码
     * param smsBody - 短信内容文本
     * return
     */
    public static Intent getSMSIntent(String telphoneNum, String smsBody) {
        Uri uri = Uri.parse("smsto:" + telphoneNum);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", smsBody);
        return intent;
    }

    /**
     * 判断intent是否可用
     */
    public static boolean isIntentAvailable(Context mContext, Intent intent) {
        final PackageManager packageManager = mContext.getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);//PackageManager.GET_ACTIVITIES
        return list.size() > 0;
    }

    /**
     * 判断intent是否可用
     * 些时候你想要知道某个AP是否有注册了一个明确的intent
     * 比如说你想要检查某个receiver是否存在，然后根据是否存在来这个receiver来在你的AP里面enable某些功能
     */
    public static boolean isIntentAvailable(Context context, String action) {
        final PackageManager packageManager = context.getPackageManager();
        final Intent intent = new Intent(action);
        List<ResolveInfo> resolveInfo = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        if (resolveInfo.size() > 0) {
            return true;
        }
        return false;
    }

    public static void showIntent(Activity context, Class<?> clzz) {
        showIntent(context, clzz, null, null);
    }

    public static void showIntent(Activity context, Class<?> clzz, String[] keys, String[] values) {
        Intent intent = new Intent(context, clzz);
        if (values != null && values.length > 0) {
            for (int i = 0; i < values.length; i++) {
                if (!TextUtils.isEmpty(keys[i]) && !TextUtils.isEmpty(values[i])) {
                    intent.putExtra(keys[i], values[i]);
                }
            }
        }
        context.startActivity(intent);
    }

    public static void showIntent(Context context, Class<?> clzz) {
        showIntent(context, clzz, null, null);
    }

    public static void showIntent(Context context, Class<?> clzz, String[] keys, String[] values) {
        Intent intent = new Intent(context, clzz);
        if (values != null && values.length > 0) {
            for (int i = 0; i < values.length; i++) {
                if (!TextUtils.isEmpty(keys[i]) && !TextUtils.isEmpty(values[i])) {
                    intent.putExtra(keys[i], values[i]);
                }
            }
        }
        context.startActivity(intent);
    }

    /**
     * 功能描述：带数据的Activity之间的跳转
     *
     * @param activity
     * @param cls
     * @param hashMap
     * @Time 2016年4月25日
     * @Author lizy18
     */
    public static void skipAnotherActivity(Activity activity,
                                           Class<? extends Activity> cls,
                                           HashMap<String, ? extends Object> hashMap) {
        Intent intent = new Intent(activity, cls);
        iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            @SuppressWarnings("unchecked")
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iterator
                    .next();
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                intent.putExtra(key, (String) value);
            }
            if (value instanceof Boolean) {
                intent.putExtra(key, (boolean) value);
            }
            if (value instanceof Integer) {
                intent.putExtra(key, (int) value);
            }
            if (value instanceof Float) {
                intent.putExtra(key, (float) value);
            }
            if (value instanceof Double) {
                intent.putExtra(key, (double) value);
            }
            if (value instanceof List<?>) {
                intent.putExtra(key, (Serializable) value);
            }
        }
        activity.startActivity(intent);
    }

    public static HashMap<String, ? extends Object> getAnotherActivity() {
        HashMap<String, Object> map = new HashMap<>();
        while (iterator.hasNext()) {
            @SuppressWarnings("unchecked")
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iterator
                    .next();
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                map.put(key, (String) value);
            }
            if (value instanceof Boolean) {
                map.put(key, (boolean) value);
            }
            if (value instanceof Integer) {
                map.put(key, (int) value);
            }
            if (value instanceof Float) {
                map.put(key, (float) value);
            }
            if (value instanceof Double) {
                map.put(key, (double) value);
            }
            if (value instanceof List<?>) {
                map.put(key, (Serializable) value);
            }
        }
        return map;
    }

    public static void openCall(Context context, String tel) {
        tel = tel.replaceAll("-", "");
        Intent intent = new Intent();
        // 激活源代码,添加intent对象
        intent.setAction("android.intent.action.CALL");
        intent.setData(Uri.parse("tel:" + tel));
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    /***
     * 从相册中取图片
     */
    public static void pickPhoto(Activity context, int requestCode) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        context.startActivityForResult(intent, requestCode);
    }

    /**
     * 拍照获取图片
     */
    public static void takePhoto(Activity context, int requestCode, Uri cameraUri) {
        // 执行拍照前，应该先判断SD卡是否存在
        String SDState = Environment.getExternalStorageState();
        if (SDState.equals(Environment.MEDIA_MOUNTED)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// "android.media.action.IMAGE_CAPTURE"
            intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, cameraUri);
            context.startActivityForResult(intent, requestCode);
        } else {
            Toast.makeText(context, "内存卡不存在", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 拍照
     *
     * @param context
     * @param uri
     */
    public static void takePhoto(Activity context, Uri uri, int requestCode) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.DISPLAY_NAME, String.valueOf(System.currentTimeMillis()) + ".jpg");
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/*");
            uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            // intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            context.startActivityForResult(intent, requestCode);
        } else {
            Toast.makeText(context, "内存卡不存在", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 获取从本地图库返回来的时候的URI解析出来的文件路径
     *
     * @return
     */
    public static String getPhotoPathByLocalUri(Context context, Intent data) {
        Uri photoUri = data.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(photoUri, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        return picturePath;
    }

    /**
     * 打开照相机
     * 当前的activity
     * 拍照成功时activity forResult 的时候的requestCode
     * 拍照完毕时,图片保存的位置
     */
    @SuppressLint("SimpleDateFormat")
    public static Uri openCamera(Activity context, int requestCode) {
        // 执行拍照前，应该先判断SD卡是否存在
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
            String filename = timeStampFormat.format(new Date());
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, filename);
            Uri photoUri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            context.startActivityForResult(intent, requestCode);
            return photoUri;
        } else {
            Toast.makeText(context, "内存卡不存在", Toast.LENGTH_LONG).show();
        }
        return null;
    }

    /**
     * 选择图片后，获取图片的路径
     *
     * @param requestCode
     * @param data
     */
    public static void doPhoto(Activity context, Intent data, int requestCode, int value, EditText editText,
                               ImageView imageView, Uri photoUri) {
        // 从相册取图片，有些手机有异常情况，请注意
        if (requestCode == value) {
            if (data == null) {
                Toast.makeText(context, "选择图片文件出错", Toast.LENGTH_LONG).show();
                return;
            }
            photoUri = data.getData();
            if (photoUri == null) {
                Toast.makeText(context, "选择图片文件出错", Toast.LENGTH_LONG).show();
                return;
            }
        }
        String[] pojo = {MediaStore.Images.Media.DATA, MediaStore.Images.Media.DISPLAY_NAME};
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(photoUri, pojo, null, null, null);
        String picPath = null;
        String filename = null;
        if (cursor != null) {
            int columnIndex = cursor.getColumnIndexOrThrow(pojo[0]);
            cursor.moveToFirst();
            picPath = cursor.getString(columnIndex);
            filename = cursor.getString(cursor.getColumnIndexOrThrow(pojo[1]));
            editText.requestFocus();
            editText.setText(filename);
            cursor.close();
        }
        String dix = filename.substring(filename.lastIndexOf("."), filename.length());
        if (filename != null
                && (dix.equalsIgnoreCase(".png") || dix.equalsIgnoreCase(".jpg") || dix.equalsIgnoreCase(".gif")
                || dix.equalsIgnoreCase(".bmp") || dix.equalsIgnoreCase(".jpeg") || dix
                .equalsIgnoreCase(".tiff"))) {
            // lastIntent.putExtra(KEY_PHOTO_PATH, picPath);
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageURI(Uri.parse(picPath));
        } else {
            imageView.setVisibility(View.GONE);
            Toast.makeText(context, "选择图片文件不正确", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 获取图片的旋转角度
     *
     * @param path
     * @return
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 保存文件
     *
     * @param context
     * @param data
     * @param requestCode
     * @param imageView
     */
    public static void saveImage(Activity context, Intent data, int requestCode, ImageView imageView) {
        Bitmap photo = null;
        Uri photoUri = data.getData();
        cropImage(context, photoUri, 500, 500, requestCode);
        if (photoUri != null) {
            photo = BitmapFactory.decodeFile(photoUri.getPath());
        }
        if (photo == null) {
            Bundle extra = data.getExtras();
            if (extra != null) {
                photo = (Bitmap) extra.get("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            }
        }
        imageView.setImageBitmap(photo);
    }

    /**
     * 截取图片
     *
     * @param uri
     * @param outputX
     * @param outputY
     * @param requestCode
     */
    public static void cropImage(Activity context, Uri uri, int outputX, int outputY, int requestCode) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        // 图片格式
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", true);
        context.startActivityForResult(intent, requestCode);
    }

    /**
     * 保存照相后的图片
     *
     * @param context
     * @param
     * @param
     * @return
     */
    public static boolean saveCamera(Activity context, Intent data, Uri cameraUri, EditText editText,
                                     ImageView imageView) {
        try {
            final Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap photo = extras.getParcelable("data");
                imageView.setImageBitmap(photo);
            }
            if (cameraUri != null) {
                String path = cameraUri.getPath();
                String filename = path.substring(path.lastIndexOf("/") + 1, path.length());
                editText.setText(filename);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean openPhotosFinally(Activity context) {
        Toast.makeText(context, "您的系统没有文件浏览器或则相册支持,请安装！", Toast.LENGTH_LONG).show();
        return false;
    }

    /**
     * PopupMenu打开本地相册.
     */
    private boolean openPhotosNormal(Activity activity, int actResultCode) {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_TYPE);
        try {
            activity.startActivityForResult(intent, actResultCode);
        } catch (android.content.ActivityNotFoundException e) {
            return true;
        }
        return false;
    }

    /**
     * 打开其他的一文件浏览器,如果没有本地相册的话
     */
    private boolean openPhotosBrowser(Activity activity, int requestCode) {
        Toast.makeText(activity, "没有相册软件，运行文件浏览器", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT); // "android.intent.action.GET_CONTENT"
        intent.setType(IMAGE_TYPE); // 查看类型 String IMAGE_UNSPECIFIED =
        // "image/*";
        Intent wrapperIntent = Intent.createChooser(intent, null);
        try {
            activity.startActivityForResult(wrapperIntent, requestCode);
        } catch (android.content.ActivityNotFoundException e1) {
            return true;
        }
        return false;
    }

}
