package com.iusername.zeus.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.iusername.base.base.ActivityBase;
import com.iusername.base.helper.BaseModule;
import com.iusername.base.helper.ModuleManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends ActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecyclerView recyclerView = new RecyclerView(this);
        setContentView(recyclerView);
        List<Map.Entry<String, String>> mapList = new ArrayList<>();
        Map<String, BaseModule> moduleMap = ModuleManager.getModuleMap();
        for (BaseModule value : moduleMap.values()) {
            mapList.addAll(value.getHomeProtocols().entrySet());
        }
        HomeProtocolAdapter adapter = new HomeProtocolAdapter(mapList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(adapter);
    }


}
