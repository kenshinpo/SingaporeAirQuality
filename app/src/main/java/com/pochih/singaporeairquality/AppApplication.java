package com.pochih.singaporeairquality;

import android.app.Application;

/**
 * Created by PoChih(A-Po) on 2018/02/03.
 */

public class AppApplication extends Application {

    private static AppApplication instance;

    public static synchronized AppApplication getInstance() {
        if (instance == null) {
            instance = new AppApplication();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
