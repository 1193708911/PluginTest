package com.example.pluginlib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by rocky on 2019/9/20.
 * 动态代理控制真正的Activity的生命周期
 * 由于采用反射创建activity对象无法
 * 调用activity生命周期 故而采用代理
 * 实现控制activity声明周期
 */

public interface IPlugin {

    void attach(Activity activity);

    void onCreate(Bundle savedInstanceState);


    void onStart();


    void onRestart();

    void onResume();


    void onPause();


    void onStop();


    void onDestroy();

    void setContentView(int layoutResID);


}
