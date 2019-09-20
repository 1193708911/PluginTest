package com.example.pluginlib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * Created by rocky on 2019/9/20.
 * 该类用于动态加载apk 同时创建插件化下的resourece对象
 * 和 加载资源文件
 */

public class PluginManager {

    private static PluginManager instance;
    private static DexClassLoader dexClassLoader;
    private static AssetManager assetManager;


    private static Resources resources;

    private PackageInfo packageInfo;

    public static PluginManager getInstance() {
        if (instance == null) {
            synchronized (PluginManager.class) {
                if (instance == null) {
                    instance = new PluginManager();
                }
            }
        }
        return instance;
    }


    public void loadApk(Context context, String apkPath) {
        try {
            packageInfo = context.getPackageManager().getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
            dexClassLoader = new DexClassLoader(apkPath, PluginConfig.UNZIP_PATH, null, context.getClassLoader());
            assetManager = AssetManager.class.newInstance();
            Method invokeMethod = AssetManager.class.getDeclaredMethod("addAssetPath", String.class);
            invokeMethod.invoke(assetManager, apkPath);
            resources = new Resources(assetManager, context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Resources getResources() {
        return resources;
    }

    public DexClassLoader getDexClassLoader() {
        return dexClassLoader;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public PackageInfo getPackageInfo() {
        return packageInfo;
    }

    public void gotoActivity(Activity proxy, String className) {
        Intent intent = new Intent(proxy, ProxyActivity.class);
        intent.putExtra(PluginConfig.REAL_CLASS_NAME, className);
        proxy.startActivity(intent);
    }
}
