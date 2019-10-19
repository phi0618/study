package com.iusername.base.helper;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.TextureView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class ProtocolManager {

    public static void redirectProtocol(Context context, String protocol) {
        redirectProtocol(context, protocol, null);
    }

    public static void redirectProtocol(Context context, String protocol, Map<String, String> params) {
        //第一步：从url取出所有参数，并添加到map
        Map<String, String> paramMap = urlToParams(protocol);
        if (paramMap != null && !paramMap.isEmpty()) {
            if (params != null) {
                params.putAll(paramMap);
            } else {
                params = paramMap;
            }
        }
        //第二步：取出参数以外的url
        int index = protocol.indexOf('?');
        if (index > 0) {
            protocol = protocol.substring(0, index);
        }
        //第三步：获取对应的模块
        BaseModule module = ModuleManager.getModuleByProtocol(protocol);
        if (module == null) {
            return;
        }
        //第4步：获取Intent 并传递参数
        Intent intent = module.redirectProtocol(context, protocol);
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                intent.putExtra(entry.getKey(), entry.getValue());
            }
        }
        context.startActivity(intent);
    }

    /**
     * 从url中获取参数
     */
    private static Map<String, String> urlToParams(String url) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        int index = url.indexOf('?');
        if (index == -1 || index == url.length()) {
            return null;
        }
        String paramsString = url.substring(index + 1);
        String[] params = paramsString.split("&");

        Map<String, String> map = new HashMap<>(params.length);
        String[] p;
        for (String param : params) {
            p = param.split("=");
            try {
                map.put(p[0], URLDecoder.decode(p[1], "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

}
