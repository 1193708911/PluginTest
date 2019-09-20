package com.example.pluginlib;

import android.os.Environment;

/**
 * Created by rocky on 2019/9/20.
 * 跳转类型配置
 */

public class PluginConfig {
    public static final String REAL_CLASS_NAME = "real_class_name";
    public static final String TYPE_RE_TO = "type_re_to";//跳转类型key
    public static final int TYPE_INTENAL = 1;//默认app内部跳转
    public static final int TYPE_OUT = 2;//外部

    public static final String UNZIP_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();//apk解压目录

    public static final String APK_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/testdemo-debug.apk";


    public static final String TEST_CLASS_PATH = "com.example.testdemo.DemoActivity";//测试跳转地址
}
