/*
 * File Name: PluginActivity.java 
 * History:
 * Created by Administrator on 2017年4月28日
 */
package com.example.mainproject;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.leight.plugin.loader.PluginLoaderActivity;
import com.leight.plugin.loader.PluginManager;

public class PluginActivity extends PluginLoaderActivity {

    public final static String PLUGIN_PACKAGE_NAME = "com.example.plugin";
    public final static String PLUGIN_FILE_NAME = "plugin.apk";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
 
    @Override
    public void onHandleMessage(Message msg) {

    }
    
    //其它操作
    private void otherOperator() {
        //如果想调用插件中入口类的一些方法，可以通过下面方式去做
        invoke("setTestMethod", new Class[]{String.class}, new Object[]{"测试"});
    }
    
    //插件中可以反过来调用该方法，注意不要混淆该方法
    public void test(String value) {
        Log.e("STUDY", "value = " + value);
    }

    @Override
    public String getPluginPackageName() {
        return PLUGIN_PACKAGE_NAME;
    }

    @Override
    public String getAssetsPluginFileName() {
        return PLUGIN_FILE_NAME;
    }

    @Override
    public void onLoadPluginBefore() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onLoadPluginAfter() {
        
    }
}
