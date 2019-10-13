package com.iusername.zeus.app;

import android.app.Application;

import com.iusername.base.helper.ModuleManager;

//aaa
public class ZeusApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ModuleManager.registerAllModules(this);
    }
}
