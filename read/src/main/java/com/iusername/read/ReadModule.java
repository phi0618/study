package com.iusername.read;

import android.content.Context;
import android.content.Intent;

import com.iusername.base.helper.BaseModule;
import com.iusername.base.helper.Constant;
import com.iusername.read.activity.ZhihuActivity;

import java.util.HashMap;
import java.util.Map;

public class ReadModule extends BaseModule {
    private static final String PROTO_HEAD = Constant.PROTO_HEAD + "read";

    @Override
    public String getModuleName() {
        return "阅读";
    }

    @Override
    public String getModuleProtocol() {
        return PROTO_HEAD;
    }

    @Override
    public Map<String, String> getHomeProtocols() {
        Map<String, String> map = new HashMap<>();
        map.put("知乎", PROTO_HEAD + "/zhihu");
        map.put("微信", PROTO_HEAD + "/weixin");
        map.put("简书", PROTO_HEAD + "/jianshu");
        map.put("CSDN", PROTO_HEAD + "/csdn");
        map.put("CSDN2", PROTO_HEAD);
        return map;
    }

    @Override
    public Intent redirectProtocol(Context context, String urlStr) {
        Intent intent = new Intent(context, ZhihuActivity.class);
        return intent;
    }

}
