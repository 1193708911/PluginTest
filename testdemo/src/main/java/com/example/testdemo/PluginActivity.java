package com.example.testdemo;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import com.example.pluginlib.IPlugin;
import com.example.pluginlib.PluginConfig;
import com.example.pluginlib.PluginManager;

/**
 * Created by rocky on 2019/9/20.
 */

public class PluginActivity extends Activity implements IPlugin {

    private Activity proxy;

    private int from = PluginConfig.TYPE_INTENAL;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //判断一下当前是否是插件跳转，插件跳转
        if (savedInstanceState != null)
            from = savedInstanceState.getInt(PluginConfig.TYPE_RE_TO);

        if (from == PluginConfig.TYPE_INTENAL) {
            super.onCreate(savedInstanceState);
            proxy = this;
        }

    }


    @Override
    public void attach(Activity activity) {
        proxy = activity;
    }

    @Override
    public void onStart() {
        if (from == PluginConfig.TYPE_INTENAL)
            super.onStart();

    }

    @Override
    public void onRestart() {
        if (from == PluginConfig.TYPE_INTENAL)
            super.onRestart();
    }

    @Override
    public void onResume() {
        if (from == PluginConfig.TYPE_INTENAL)
            super.onResume();
    }

    @Override
    public void onPause() {
        if (from == PluginConfig.TYPE_INTENAL)
            super.onPause();
    }

    @Override
    public void onStop() {
        if (from == PluginConfig.TYPE_INTENAL)
            super.onStop();
    }

    @Override
    public void onDestroy() {
        if (from == PluginConfig.TYPE_INTENAL)
            super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (from == PluginConfig.TYPE_INTENAL) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    //下面是几个生命周期之外的重写函数
    @Override
    public void setContentView(int layoutResID) {//设置contentView分情况
        if (from == PluginConfig.TYPE_INTENAL) {
            super.setContentView(layoutResID);
        } else {
            proxy.setContentView(layoutResID);
        }
    }

    @Override
    public View findViewById(int id) {
        if (from == PluginConfig.TYPE_INTENAL) {
            return super.findViewById(id);
        } else {
            return proxy.findViewById(id);
        }
    }

    @Override
    public void startActivity(Intent intent) {
        if (from == PluginConfig.TYPE_INTENAL) {
            super.startActivity(intent);
        } else {
            PluginManager.getInstance().gotoActivity(proxy, intent.getComponent().getClassName());
        }
    }


}
