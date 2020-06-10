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
    static {
        Logger.ENABLE = BuildConfig.DEBUG;
        Logger.TAG = Logger.TAG + "-" + MainActivity.class.getSimpleName();
    }

        // 没有设置打印器，默认使用android.util.Log
        Logger.print("测试1");
        // 添加一个打印器，需添加依赖（runtimeOnly 'com.orhanobut:logger:2.2.0'）
        Logger.addPrinter(new BeautifulPrinter());
        Logger.print("测试2");
        Logger.addPrinter(new IPrinter() {
            @Override
            public void printLog(String log) {
                System.out.println("诸如，可以将日志保存到文件：" + log);
            }
        });
        Logger.print("测试3");
        Logger.print(new RuntimeException("测试"));
```
```text
W/liyujiang-MainActivity: 测试1
W/liyujiang-MainActivity: ┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────
W/liyujiang-MainActivity: │ Activity.performCreate  (Activity.java:7868)
W/liyujiang-MainActivity: │    MainActivity.onCreate  (MainActivity.java:38)
W/liyujiang-MainActivity: ├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
W/liyujiang-MainActivity: │ 测试2
W/liyujiang-MainActivity: └────────────────────────────────────────────────────────────────────────────────────────────────────────────────
I/System.out: 诸如，可以将日志保存到文件：测试3
I/System.out: 诸如，可以将日志保存到文件：java.lang.RuntimeException: 测试
I/System.out:     at com.github.gzuliyujiang.demo.MainActivity.onCreate(MainActivity.java:46)
I/System.out:     at android.app.Activity.performCreate(Activity.java:7868)
I/System.out:     at android.app.Activity.performCreate(Activity.java:7857)
I/System.out:     at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1353)
I/System.out:     at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:3479)
I/System.out:     at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:3643)
I/System.out:     at android.app.servertransaction.LaunchActivityItem.execute(LaunchActivityItem.java:83)
I/System.out:     at android.app.servertransaction.TransactionExecutor.executeCallbacks(TransactionExecutor.java:135)
I/System.out:     at android.app.servertransaction.TransactionExecutor.execute(TransactionExecutor.java:95)
I/System.out:     at android.app.ActivityThread$H.handleMessage(ActivityThread.java:2225)
I/System.out:     at android.os.Handler.dispatchMessage(Handler.java:107)
I/System.out:     at android.os.Looper.loop(Looper.java:230)
I/System.out:     at android.app.ActivityThread.main(ActivityThread.java:7742)
I/System.out:     at java.lang.reflect.Method.invoke(Native Method)
I/System.out:     at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:508)
I/System.out:     at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:1034)
```