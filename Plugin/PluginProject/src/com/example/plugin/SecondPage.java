/*
 * File Name: SecondPage.java 
 * History:
 * Created by Administrator on 2017年4月28日
 */
package com.example.plugin;

import android.content.Intent;
import android.content.res.Configuration;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;

import com.lightweight.plugin.framework.PluginPage;

public class SecondPage extends PluginPage {
    
    @Override
    public void onPageCreate(Intent intent) {
        super.onPageCreate(intent);
        TextView text = new TextView(getApplicationContext());
        text.setText("这是插件中第二个界面");
        setPageContentView(text);
        //如果想调用宿主PluginActivity中的任意方法，通过这种方式实现
        invoke("test", new Class[]{String.class}, new Object[]{"插件中调用宿主的test方法"});
    }

    @Override
    public void onPageNewIntent(Intent intent) {
        super.onPageNewIntent(intent);
    }

    @Override
    public void onPageResume() {
        super.onPageResume();
    }

    @Override
    public void onPageStart() {
        super.onPageStart();
    }

    @Override
    public void onPageStop() {
        super.onPageStop();
    }

    @Override
    public void onPagePause() {
        super.onPagePause();
    }

    @Override
    public void onPageDestory() {
        super.onPageDestory();
    }

    @Override
    public void onPageResult(int requestCode, int resultCode, Intent data) {
        super.onPageResult(requestCode, resultCode, data);
    }
    
    /**
     * 运行模式，{@link com.lightweight.plugin.framework.PluginPage#LAUNCH_MODE_DEFAULT} or {@link com.lightweight.plugin.framework.PluginPage#LAUNCH_MODE_SINGLE}
     * 
     * @return default return {@link com.lightweight.plugin.framework.PluginPage#LAUNCH_MODE_DEFAULT}
     */
    @Override
    public int getLaunchMode() {
        return super.getLaunchMode();
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return super.onKeyUp(keyCode, event);
    }
    
}
