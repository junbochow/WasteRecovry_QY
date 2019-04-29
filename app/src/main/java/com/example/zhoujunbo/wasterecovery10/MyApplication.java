package com.example.zhoujunbo.wasterecovery10;

import android.app.Application;
import android.content.SharedPreferences;

/**
 *
 * 全局上下文，方便使用
 *
 */

public class MyApplication extends Application {


    public static SharedPreferences sp;
    public static SharedPreferences.Editor editor;
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
