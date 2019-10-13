package com.iusername.zeus.app;

import android.app.Application;
import android.util.DisplayMetrics;

public class Common {
    public static Application application;
    public DisplayMetrics displayMetrics;

    public int dp2Px(int dipValue){
        DisplayMetrics dm =application.getResources().getDisplayMetrics();
        float scale = dm.density;
        return (int) (dipValue * scale + 0.5f);
    }

}
