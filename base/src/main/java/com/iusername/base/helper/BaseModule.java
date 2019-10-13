package com.iusername.base.helper;

import android.content.Context;
import android.content.Intent;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseModule {

    public abstract String getModuleName();

    public abstract String getModuleProtocol();

    public abstract Map<String, String> getHomeProtocols();

    public abstract Intent redirectProtocol(Context context, String urlStr, HashMap<String, String> params);

    public abstract void startActivity(Context ctx, String url);

}
