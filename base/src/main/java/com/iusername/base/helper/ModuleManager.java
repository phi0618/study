package com.iusername.base.helper;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.iusername.base.utils.LogUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import dalvik.system.DexFile;

public class ModuleManager {

    private static Map<String, BaseModule> moduleMap;

    static {
        moduleMap = new HashMap<>();
    }

    public static Map<String, BaseModule> getModuleMap() {
        return moduleMap;
    }

    public static void registerModule(BaseModule module) {
        String protocol = module.getModuleProtocol();
        moduleMap.put(protocol, module);
    }

    static BaseModule getModuleByProtocol(String urlStr) {
        if (TextUtils.isEmpty(urlStr) || !urlStr.startsWith(Constant.PROTO_HEAD)) {
            return null;
        }

        String sub2 = urlStr.substring(Constant.PROTO_HEAD.length());
        int index = sub2.indexOf("/");
        if (index >= 0) {
            sub2 = sub2.substring(0, index);
        }
        sub2 = Constant.PROTO_HEAD + sub2;
        return moduleMap.get(sub2);
    }


    /**
     * 反射注册所有模块
     * 需要项目启动时调用
     */
    public static void registerAllModules(Application application) {
        ClassLoader loader = application.getClassLoader();
        // 得到instant run文件夹下的全部dex文件
        ArrayList<DexFile> dexFiles = null;
        try {
            dexFiles = getAppDexFile(application);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (dexFiles == null || dexFiles.isEmpty()) {
            return;
        }
        for (DexFile df : dexFiles) {
            LogUtils.defaultLog("dex result####:" + df.getName());
            for (Enumeration<String> iter = df.entries(); iter.hasMoreElements(); ) {
                String className = iter.nextElement();
                if (!className.endsWith("Module") || className.endsWith("BaseModule")) {
                    continue;
                }
                try {
                    Class clazz = loader.loadClass(className);
                    if (BaseModule.class.isAssignableFrom(clazz)) {
                        LogUtils.defaultLog("找到 module####:" + className);
                        BaseModule module = (BaseModule) clazz.newInstance();
                        registerModule(module);
                    }
                } catch (Exception e) {
                    LogUtils.defaultLog(e);
                }
            }
        }
    }


    /**
     * 获取dex文件位置(加固前加固后不同)
     *
     * @throws IOException
     */
    public static ArrayList<DexFile> getAppDexFile(Context appCtx) throws IOException {
        final ArrayList<DexFile> dexfile = new ArrayList<>();
        boolean isOpt = false;
        File safeFile = new File("/data/data/" + appCtx.getPackageName() + "/.cache/classes.dve");
        if (safeFile.exists()) {    //判断是否是加固包
            isOpt = true;
        }

        //	处理主dex文件==================================
        if (!isOpt) {//非加固包加载主dex
            dexfile.add(new DexFile(appCtx.getPackageResourcePath()));//只加载主dex
            LogUtils.defaultLog("getAppDexFile add main dex success####:" + appCtx.getPackageResourcePath());
        } else {//加固包加载主dex
            String jarpath = "/data/data/" + appCtx.getPackageName() + "/.cache/classes.jar";
            String dexpath = "/data/data/" + appCtx.getPackageName() + "/.cache/classes.dex";
            DexFile dex = DexFile.loadDex(jarpath, dexpath, 0);
            dexfile.add(dex);
        }

        //处理其他dex文件================================
        //拆分的dex路径要与用的mutildex.jar中的SECONDARY_FOLDER_NAME路径一致
        //加固包在5.0以上环境还需要加载其他dex,dex位置跟4.4以下不同
        if (isOpt) {
            dexfile.add(new DexFile(appCtx.getPackageResourcePath()));
            LogUtils.defaultLog("getAppDexFile add main dex success####:" + appCtx.getPackageResourcePath());
        }
        return dexfile;
    }

}
