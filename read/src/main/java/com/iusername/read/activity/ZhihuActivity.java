package com.iusername.read.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.iusername.base.base.ActivityBase;

public class ZhihuActivity extends ActivityBase {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String string = intent.getStringExtra("name");
        showMessage(string);
    }

}
