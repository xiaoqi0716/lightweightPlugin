/*
 * File Name: OnPluginLoadCallback.java 
 * History:
 * Created by Administrator on 2016年2月3日
 */
package com.leight.plugin.loader;



public interface OnPluginLoadConfig {

    /**
     * 当前主程序assets或res/raw下的插件文件名
     * 
     * @return 插件文件名, 正式环境时返回null(通常在{@link #isDebugMode()}为true或程序需要带着插件一起发布时才返回相应的名称)
     */
    public String getLocalPluginFileName();

    public boolean downloadPlugin();
    

}
