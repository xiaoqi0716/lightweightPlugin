package com.example.mainproject;

import java.io.File;
import java.io.FileOutputStream;

import com.leight.plugin.loader.PluginManager;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.btn_open);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//              如果宿主APK中保存了一份插件，那么只要把插件放到assets目录下，运行插件时自动会复制到/data/data/宿主程序包名/app_plugin/插件包名.apk，如果你不在宿主APK中保留一份插件，那么你在启动插件之前需要使用PluginManager.getInstance(MainActivity.this).getPluginPath(PluginActivity.PLUGIN_FILE_NAME)来判断插件是否存在
                Intent intent = new Intent(MainActivity.this, PluginActivity.class);
                startActivity(intent);
            }
        });
        
        
//        判断插件是否已经加载或已经运行可通过PluginManager.isContainer(包名)去检查
        
//        插件可以在APK中保存一份，也可以不保存现用现从网络上下载，直接根据PluginManager中的API判断插件是否存在，以及插件的版本号
        
//        检查是否有更新方式：
//        获取当前本地正在使用的插件版本号
//        PackageInfo pkgInfo = getPackageManager().getPackageArchiveInfo(PluginManager.getInstance(MainActivity.this).getPluginPath(PluginActivity.PLUGIN_FILE_NAME), 0);
//        int curPluginVersion = pkgInfo.versionCode; //插件APK的版本号
//        根据版本号检查更新
//        .................... 
//        if (有更新) {
//          PluginManager.getInstance(this).delPluginFile(PluginActivity.PLUGIN_FILE_NAME);
//          替换插件
//          File file = new File(PluginManager.getInstance(MainActivity.this).getPluginPath(PluginActivity.PLUGIN_FILE_NAME));
//          FileOutputStream fos = new FileOutputStream(file);
//        }
        
    }
}
