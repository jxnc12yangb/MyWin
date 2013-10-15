package com.yangbang;

import android.app.Application;

/**
 * Created by yangbang on 13-10-9.
 */
public class MainApp extends Application{

    private static MainApp globalContext;

    public static boolean Debug = true;

    @Override
    public void onCreate() {
        super.onCreate();

        globalContext = this;
    }

    public static MainApp getInstance() {
        return globalContext;
    }
}
