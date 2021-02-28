package com.example.sbs.myapplication;

import android.app.Application;

import com.example.sbs.myapplication.util.Util;
import com.facebook.stetho.Stetho;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Util.init(this);
        Stetho.initializeWithDefaults(this);
    }
}
