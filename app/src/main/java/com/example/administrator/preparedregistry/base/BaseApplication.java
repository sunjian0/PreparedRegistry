package com.example.administrator.preparedregistry.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by sunjian on 2016/6/16.
 */
public class BaseApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }

}
