package com.example.pluginlib;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dalvik.system.DexClassLoader;

public class ProxyActivity extends AppCompatActivity {

    private IPlugin iPlugin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            String realClassName = getIntent().getStringExtra(PluginConfig.REAL_CLASS_NAME);
            Class<?> aClass = PluginManager.getInstance().getDexClassLoader().loadClass(realClassName);
            Object obj = aClass.newInstance();
            if (obj instanceof IPlugin) {
                iPlugin = (IPlugin) obj;
                iPlugin.attach(this);
                Bundle bundle = new Bundle();
                bundle.putInt(PluginConfig.TYPE_RE_TO, PluginConfig.TYPE_OUT);
                iPlugin.onCreate(bundle);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @Override
    protected void onStart() {
        iPlugin.onStart();
        super.onStart();

    }


    @Override
    protected void onRestart() {
        iPlugin.onRestart();
        super.onRestart();
    }

    @Override
    protected void onResume() {
        iPlugin.onResume();
        super.onResume();
    }


    @Override
    protected void onPause() {
        iPlugin.onPause();
        super.onPause();
    }


    @Override
    protected void onStop() {
        iPlugin.onStop();
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        iPlugin.onDestroy();
        super.onDestroy();
    }


    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getDexClassLoader();
    }


    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getResources();
    }
}
