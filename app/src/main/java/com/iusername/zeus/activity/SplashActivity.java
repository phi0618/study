package com.iusername.zeus.activity;

import android.os.Bundle;

import com.iusername.base.base.ActivityBase;
import com.iusername.base.helper.ModuleManager;
import com.iusername.read.ReadModule;
import com.iusername.zeus.R;

public class SplashActivity extends ActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        loadAllModule();
    }

    private void loadAllModule(){
        ModuleManager.registerModule(new ReadModule());
    }
}
