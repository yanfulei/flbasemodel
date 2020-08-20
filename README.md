##flbasemodel
一个Android通用的model库

##com.haozhang.libary:android-slanted-textview:1.2
简介：是一个倾斜的textview用于标签
GitHub地址：https://github.com/HeZaiJin/SlantedTextView

##com.github.lygttpod:SuperTextView
简介：是一个强大的textview扩展
GitHub地址：https://github.com/lygttpod/SuperTextView

##FlycoDialog_Lib
简介：是一个弹出框组件
GitHub地址：

##AVLoadingIndicatorView
简介：是一个loading
GitHub地址：https://github.com/81813780/AVLoadingIndicatorView
##SuperButton
简介：是一个通用按钮
GitHub地址：https://github.com/ansnail/SuperButton

##ACache
简介：
1、替换SharePreference当做配置文件
2、可以缓存网络请求数据，比如oschina的android客户端可以缓存http请求的新闻内容，缓存时间假设为1个小时，超时后自动失效，让客户端重新请求新的数据，减少客户端流量，同时减少服务器并发量。
##如何使用 ASimpleCache？ demo
ACache mCache = ACache.get(this);
mCache.put("test_key1", "test value");
mCache.put("test_key2", "test value", 10);//保存10秒，如果超过10秒去获取这个key，将为null
mCache.put("test_key3", "test value", 2 * ACache.TIME_DAY);//保存两天，如果超过两天去获取这个key，将为null
获取数据
ACache mCache = ACache.get(this);
String value = mCache.getAsString("test_key1");

##Logcat

简介：这是一个Android 上 效率极高的 Log 工具，主要功能为控制不同级别的Log输出,Log信息保存到文件、打印行号、函数调用、Json解析、点击跳转、多标签Tag 支持无限长字符串打印，无Logcat4000字符限制等功能

GitHub地址：https://github.com/iflove/Logcat.git


