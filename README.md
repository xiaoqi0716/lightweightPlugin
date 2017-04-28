# lightweightPlugin

插件开发框架
适用范围：
SDK开发，实现SDK独立更新
APP开发，实现模块化独立开发和更新
APP界面中的一部分独立更新

插件运行方式：每次修改插件工程时，都要将插件工程导出为一个APK（可以是非签名的）放到宿主工程的assets目录下，文件名任意，然后运行宿主程序

插件运行后会自动复制到/data/data/宿主程序包名/插件包名.apk，如果该目录下有插件，并且版本号大于等于assets目录下插件版本号是不会复制，
所以经常调试插件代码时，需要去手机应用程序设置中清除宿主程序的应用数据，然后再运行宿主程序

如果觉得麻烦，可以在启动插件之前使用
PluginManager.getInstance().delPluginFile()方法，注意，上线或测试插件更新时把这一句删除，
