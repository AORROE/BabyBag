package com.hwt.babybag;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    private static Context context;
    public static final String TAG = "Arrow_test";

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContextObj(){
        return context;
    }
}
