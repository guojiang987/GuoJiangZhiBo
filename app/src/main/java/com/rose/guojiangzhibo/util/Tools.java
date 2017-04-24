package com.rose.guojiangzhibo.util;

import android.os.Build;
import android.util.Log;

/**
 * Created by xdy on 2017/4/15.
 * 工具类
 */

public class Tools {
    public static String getPlatform() {////app登录设备平台
        return "android";
    }

    public static String getDeviceName() {//app登录设备名称
        return Build.MODEL;
    }

    public static String getVersion() {
        return "3.0.1";
    }

    public static String getChannel() {
        return "and-xiaomi-0";
    }

    public static String getPackageId() {
        return "0";
    }

    public static String getAndroidVersion() {
        return android.os.Build.VERSION.SDK_INT + "";
    }

    public static void d(String msg) {
        d("xdy", msg);
    }

    public static void d(String tag, String msg) {//TODO 此为调试代码
        try {
            Log.d(tag, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
