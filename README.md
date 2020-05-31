# Logger
[![API 14+](https://img.shields.io/badge/API-14%2B-green.svg)](https://github.com/gzu-liyujiang/Logger)
[![bintray](https://api.bintray.com/packages/gzu-liyujiang/maven/Logger/images/download.svg) ](https://bintray.com/gzu-liyujiang/maven/Logger/_latestVersion)
[![jitpack](https://jitpack.io/v/gzu-liyujiang/Logger.svg)](https://jitpack.io/#gzu-liyujiang/Logger)
[![travis-ci](https://travis-ci.org/gzu-liyujiang/Logger.svg?branch=master)](https://travis-ci.org/gzu-liyujiang/Logger)

Android 日志打印组件，面向接口编程  Logger

```groovy
    allprojects {
        repositories {
            maven { url 'https://jitpack.io' }
        }
    }
```
```groovy
    dependencies {
        implementation 'com.github.gzu-liyujiang:Logger:latest.version'
    }
```
```groovy
        Logger.ENABLE = BuildConfig.DEBUG;
        //Logger.usePrinter(new BeautifulPrinter());
        Logger.print("测试");
        Logger.print(new RuntimeException("测试"));
```
```text
W/liyujiang: ┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────
W/liyujiang: │ Activity.performCreate  (Activity.java:6324)
W/liyujiang: │    MainActivity.onCreate  (MainActivity.java:31)
W/liyujiang: ├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
W/liyujiang: │ 测试
W/liyujiang: └────────────────────────────────────────────────────────────────────────────────────────────────────────────────
W/liyujiang: ┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────
W/liyujiang: │ Activity.performCreate  (Activity.java:6324)
W/liyujiang: │    MainActivity.onCreate  (MainActivity.java:32)
W/liyujiang: ├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
W/liyujiang: │ java.lang.RuntimeException: 测试
W/liyujiang: │ 	at com.github.gzuliyujiang.demo.MainActivity.onCreate(MainActivity.java:32)
W/liyujiang: │ 	at android.app.Activity.performCreate(Activity.java:6324)
W/liyujiang: │ 	at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1108)
W/liyujiang: │ 	at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2447)
W/liyujiang: │ 	at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:2556)
W/liyujiang: │ 	at android.app.ActivityThread.access$1200(ActivityThread.java:155)
W/liyujiang: │ 	at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1416)
W/liyujiang: │ 	at android.os.Handler.dispatchMessage(Handler.java:102)
W/liyujiang: │ 	at android.os.Looper.loop(Looper.java:148)
W/liyujiang: │ 	at android.app.ActivityThread.main(ActivityThread.java:5645)
W/liyujiang: │ 	at java.lang.reflect.Method.invoke(Native Method)
W/liyujiang: │ 	at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:726)
W/liyujiang: │ 	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:616)
W/liyujiang: └────────────────────────────────────────────────────────────────────────────────────────────────────────────────
```