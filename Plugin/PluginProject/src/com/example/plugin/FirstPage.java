/*
 * File Name: MainPage.java 
 * History:
 * Created by Administrator on 2017年4月28日
 */
package com.example.plugin;

import android.content.Intent;
import android.content.res.Configuration;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.example.plugin.R;
import com.lightweight.plugin.framework.PluginPage;

public class FirstPage extends PluginPage {

    @Override
    public void onPageCreate(Intent intent) {
        super.onPageCreate(intent);
        setPageContentView(R.layout.content_main);
        Button btnOpen = (Button) findViewById(R.id.btn_open);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SecondPage.class);
                intent.putExtra("PARAMS", "测试");
                startPage(SecondPage.class, intent);
            }
        });
        Button btnClose = (Button) findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                finishPage();
            }
        });
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
