package com.example.plugin;


import android.content.Intent;
import android.util.Log;

import com.lightweight.plugin.framework.PluginActivityProxy;

public class PluginMain extends  PluginActivityProxy {

    @Override
    public Class<?> getMainPageClass(Intent intent) {
        return FirstPage.class;
    }
    
    //在宿主程序中调用该方法例子，注意方法不要混淆
    public void setTestMethod(String value) {
        Log.e("PLUGIN", "value = " + value);
    }
}
