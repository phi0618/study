package com.iusername.base.helper;

import android.content.Context;

public class ProtocolManager {

    public static void redirectProtocol(Context context, String protocol) {
        BaseModule module = ModuleManager.getModuleByProtocol(protocol);
        module.startActivity(context, protocol);
    }
}
