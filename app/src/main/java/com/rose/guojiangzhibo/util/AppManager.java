package com.anbang.palm.util;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;

import java.util.List;

/**
 * Created by hao on 17/3/17.
 */

public class AppManager {
    public static int TOP_STACK = 1;
    public static int IN_STACK = 2;
    public static int NOT_STACK = 3;
    /**
     * 返回app运行状态
     * 1:程序在前台运行
     * 2:程序在后台运行
     * 3:程序未启动
     * 注意：需要配置权限<uses-permission android:name="android.permission.GET_TASKS" />
     */
    public static int getAppSatus(Context context, String pageName) {

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);

        //判断程序是否在栈顶
        if (list.get(0).topActivity.getPackageName().equals(pageName)) {
            return TOP_STACK;
        } else {
            //判断程序是否在栈里
            for (ActivityManager.RunningTaskInfo info : list) {
                if (info.topActivity.getPackageName().equals(pageName)) {
                    return IN_STACK;
                }
            }
            return NOT_STACK;//栈里找不到，返回3
        }
    }

    /**
     *
     * @param context
     * @return 是否锁屏
     */
    public static boolean isCloseScreen(Context context){
        KeyguardManager km = (KeyguardManager)context.getSystemService(Context.KEYGUARD_SERVICE);
        return km.inKeyguardRestrictedInputMode();
    }
}
