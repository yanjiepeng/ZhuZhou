package com.zk.zhuzhou.application;

import android.app.Application;

import com.zk.zhuzhou.Utils.L;


/**
 * Created by Administrator on 2016/6/29.
 */
public class MyApplication extends Application {

    String local_address ;

    private static MyApplication instance;
    public static MyApplication getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this ;
        L.isDebug = true;
    }
}
