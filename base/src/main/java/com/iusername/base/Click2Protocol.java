package com.iusername.base;

import android.view.View;

import com.iusername.base.helper.ProtocolManager;

import java.util.HashMap;
import java.util.Map;

public class Click2Protocol implements View.OnClickListener {
    private String protocolUrl;

    public Click2Protocol(String protocolUrl) {
        this.protocolUrl = protocolUrl;
    }

    @Override
    public void onClick(View v) {
        Map<String,String> map =new HashMap<>();
        map.put("name",protocolUrl);
        ProtocolManager.redirectProtocol(v.getContext(), protocolUrl,map);
    }
}
