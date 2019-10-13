package com.iusername.base;

import android.content.Context;
import android.view.View;

import com.iusername.base.helper.ProtocolManager;

public class Click2Protocol implements View.OnClickListener {
    private String protocolUrl;

    public Click2Protocol(String protocolUrl) {
        this.protocolUrl = protocolUrl;
    }

    @Override
    public void onClick(View v) {
        ProtocolManager.redirectProtocol(v.getContext(), protocolUrl);
    }
}
