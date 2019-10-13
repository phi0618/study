package com.iusername.base.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

public class UnitUtils {
    private static DisplayMetrics displayMetrics;

    static {
        displayMetrics = Resources.getSystem().getDisplayMetrics();
    }


    public static int dip2px(float dipValue) {
        float scale = displayMetrics.density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(float pxValue) {
        float scale = displayMetrics.density;
        return (int) (pxValue / scale + 0.5f);
    }

}
