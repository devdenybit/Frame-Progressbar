package com.virtual.puppy;

import android.app.Application;
import android.content.Context;

import com.virtual.puppy.utils.LocaleHelper;

public class MainApplication extends Application {

    private static MainApplication APP_INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        APP_INSTANCE = this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

    public static MainApplication getInstance() {
        return APP_INSTANCE;
    }
}
