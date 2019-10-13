package com.iusername.base.base;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

public abstract class ActivityBase extends AppCompatActivity {
    protected Context mContext;

    @Override
    protected void onStart() {
        super.onStart();
        mContext = this;
    }
}
