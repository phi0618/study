package com.iusername.read;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.iusername.base.base.ActivityBase;

public class ZhihuActivity extends ActivityBase {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Intent intent = getIntent();
        String string = intent.getStringExtra("name");
        showMessage(string);
    }
}
