package com.example.rocky.demo;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pluginlib.PluginConfig;
import com.example.pluginlib.PluginManager;
import com.example.pluginlib.ProxyActivity;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * button
     */
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        PermissionGen.needPermission(this, 100,
                new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,

                }
        );
    }

    private void initView() {
        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.button:
                PluginManager.getInstance().gotoActivity(MainActivity.this, PluginConfig.TEST_CLASS_PATH);
                break;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }


    @PermissionSuccess(requestCode = 100)
    public void doSomething() {
        PluginManager.getInstance().loadApk(this, PluginConfig.APK_PATH);
        Toast.makeText(this, "Contact permission is granted", Toast.LENGTH_SHORT).show();


    }

    @PermissionFail(requestCode = 100)
    public void doFailSomething() {
        Toast.makeText(this, "Contact permission is not granted", Toast.LENGTH_SHORT).show();
    }
}
