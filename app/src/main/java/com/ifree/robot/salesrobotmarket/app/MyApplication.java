package com.ifree.robot.salesrobotmarket.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ifree.robot.salesrobotmarket.utils.CrashHandler;
import com.ifree.robot.salesrobotmarket.utils.DensityUtils;


/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/14.
 * Description:
 */

public class MyApplication extends Application {
    public static Context CONTEXT;
    private static MyApplication myApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        CONTEXT = getApplicationContext();

        myApplication = this;
        //崩溃错误日志写入本地文档
        DensityUtils.setDensity(this);
        CrashHandler catchExcep = new CrashHandler(this);
        Thread.setDefaultUncaughtExceptionHandler(catchExcep);
    }

    public static Context getContext(){
        return CONTEXT;
    }
    public static MyApplication getApplication() {
        return myApplication;
    }
    //通过类名启动Activity
    public static void openActivity(Context context, Class<?> targetClass) {
        openActivity(context, targetClass, null);
    }

    //通过类名启动Activity，并且含有Bundle数据
    public static void openActivity(Context context, Class<?> targetClass,
                                    Bundle extras) {
        Intent intent = new Intent(context, targetClass);
        if (extras != null) {
            intent.putExtras(extras);
        }
        context.startActivity(intent);
    }

    public static void openActivityForResult(Activity activity,
                                             Class<?> targetClass, Bundle extras, int requestCode) {
        Intent intent = new Intent(activity, targetClass);
        if (extras != null) {
            intent.putExtras(extras);
        }
        activity.startActivityForResult(intent, requestCode);
    }

    public static void openActivityForResult(Activity activity,
                                             Class<?> targetClass, int requestCode) {
        openActivityForResult(activity, targetClass, null, requestCode);
    }

    //通过Action启动Activity
    public static void openActivity(Context context, String action) {
        openActivity(context, action, null);
    }

    //通过Action启动Activity，并且含有Bundle数据
    public static void openActivity(Context context, String action,
                                    Bundle extras) {
        Intent intent = new Intent(action);
        if (extras != null) {
            intent.putExtras(extras);
        }
        context.startActivity(intent);
    }

}
