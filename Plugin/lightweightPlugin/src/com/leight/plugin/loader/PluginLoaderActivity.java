/*
 * File Name: PluginLoaderActivity.java 
 * History:
 * Created by Administrator on 2016年1月26日
 */
package com.leight.plugin.loader;

import java.lang.reflect.Method;

import com.lightweight.plugin.util.LogPluginUtils;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

/**
 * <b> 使用范围 主程序(非插件工程)
 * <p>
 * 主程序显示插件的Activity需继承该类
 * 
 * @author Wang xiaoqi
 * @version 1.0
 */
public abstract class PluginLoaderActivity extends PluginBaseActivity implements PluginManager.OnLoaderListener {

    // ==========================================================================
    // Constants
    // ==========================================================================
    // ==========================================================================
    // Fields
    // ==========================================================================
    private Bundle mSavedInstanceState;
    private Handler mHandler = new Handler();
    private View mPluginRootView;
    private PluginManager mPluginMgr;

    // ==========================================================================
    // Constructors
    // ==========================================================================

    // ==========================================================================
    // Getters
    // ==========================================================================
    /**
     * 获取插件根布局
     * 
     * @return {@link View}
     */
    public final View getPluginRootView() {
        if (mPluginRootView == null) {
            if (mPluginMgr.isContainer(getPluginPackageName())) {
                mPluginRootView = invoke("getRootView", null, null);
            }
        }
        return mPluginRootView;
    }

    // ==========================================================================
    // Setters
    // ==========================================================================

    // ==========================================================================
    // Methods
    // ==========================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPluginMgr = PluginManager.getInstance(this);
        mPluginMgr.setLoaderListener(getPluginPackageName(), this);
        super.onCreate(savedInstanceState);
        mSavedInstanceState = savedInstanceState;
        load();

    }

    private void load() {
        onLoadPluginBefore();
        new Thread() {

            public void run() {
                mPluginMgr.loadPlugin(getPluginPackageName(), getRawPluginFileName());
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        onLoadPluginAfter();
                    }
                });

            }
        }.start();

    }

    Runnable onCreateRunnable = new Runnable() {

        @Override
        public void run() {
            invoke("setActivity", new Class[] { Activity.class }, new Object[] { PluginLoaderActivity.this });
            invoke("setHandler", new Class[] { Handler.class }, new Object[] { mHandler });
            invoke("onCreate", new Class[] { Bundle.class }, new Object[] { mSavedInstanceState });
            View contentView = invoke("getRootView", null, null);
            if (contentView != null) {
                setContentView(contentView);
            } else {
                LogPluginUtils.w("插件中没设置视图");
            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        if (mPluginMgr.isContainer(getPluginPackageName())) {
            invoke("onStart", null, null);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPluginMgr.isContainer(getPluginPackageName())) {
            invoke("onResume", null, null);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (mPluginMgr.isContainer(getPluginPackageName())) {
            invoke("onNewIntent", new Class[] { Intent.class }, new Object[] { intent });
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPluginMgr.isContainer(getPluginPackageName())) {
            invoke("onPause", null, null);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mPluginMgr.isContainer(getPluginPackageName())) {
            invoke("onStop", null, null);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPluginMgr.isContainer(getPluginPackageName())) {
            invoke("onDestroy", null, null);
        }
        mPluginMgr.removeLoaderListener(getPluginPackageName());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mPluginMgr.isContainer(getPluginPackageName())) {
            Boolean value = invoke("onKeyDown", new Class[] { int.class, KeyEvent.class }, new Object[] { keyCode,
                    event });
            if (value != null && value) {
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (mPluginMgr.isContainer(getPluginPackageName())) {
            Boolean value = invoke("onKeyUp", new Class[] { int.class, KeyEvent.class },
                    new Object[] { keyCode, event });
            if (value != null && value) {
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mPluginMgr.isContainer(getPluginPackageName())) {
            invoke("onAttachedToWindow", null, null);
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (mPluginMgr.isContainer(getPluginPackageName())) {
            Boolean value = invoke("dispatchKeyEvent", new Class[] { KeyEvent.class }, new Object[] { event });
            if (value != null && value) {
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mPluginMgr.isContainer(getPluginPackageName())) {
            Boolean value = invoke("dispatchTouchEvent", new Class[] { MotionEvent.class }, new Object[] { ev });
            if (value != null && value) {
                return true;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mPluginMgr.isContainer(getPluginPackageName())) {
            invoke("onActivityResult", new Class[] { int.class, int.class, Intent.class }, new Object[] { requestCode,
                    resultCode, data });
        }
    }

    /**
     * 反射方法的调用
     */
    public <T> T invoke(String methodName, Class<?>[] paramsTypes, Object[] params) {
        try {
            Method method = getMethod(mPluginMgr.getPluginLoaderClass(getPluginPackageName()), methodName, paramsTypes);
            if (method != null) {
                method.setAccessible(true);
                return (T) method.invoke(mPluginMgr.getPluginInstance(getPluginPackageName()), params);
            }
            LogPluginUtils.e(new Exception("invoke plugin method: " + methodName + " is not found!"));
        } catch (Exception e) {
            LogPluginUtils.e(e);
        }
        return null;
    }

    protected Method getMethod(Class<?> obj, String methodName, Class<?>[] parameterTypes) {
        if (obj == null) {
            return null;
        }
        Method method = null;
        try {
            method = obj.getDeclaredMethod(methodName, parameterTypes);
        } catch (Exception e) {
            try {
                method = obj.getMethod(methodName, parameterTypes);
            } catch (Exception e1) {
                if (obj.getSuperclass() == null) {
                    return null;
                } else {
                    method = getMethod(obj.getSuperclass(), methodName, parameterTypes);
                    if (method != null) {
                        return method;
                    }
                }
            }
        }
        return method;
    }

    @Override
    public void onLoaderCompleted(String pkgName) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                onCreateRunnable.run();
            }
        });
    }

    /**
     * 插件的包名
     * 
     * @return 插件包名
     */
    public abstract String getPluginPackageName();

    public abstract String getRawPluginFileName();

    public abstract void onLoadPluginBefore();

    public abstract void onLoadPluginAfter();
    // ==========================================================================
    // Inner/Nested Classes
    // ==========================================================================
}
