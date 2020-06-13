# Logger
[![API 14+](https://img.shields.io/badge/API-14%2B-green.svg)](https://github.com/gzu-liyujiang/Logger)
[![bintray](https://api.bintray.com/packages/gzu-liyujiang/maven/Logger/images/download.svg) ](https://bintray.com/gzu-liyujiang/maven/Logger/_latestVersion)
[![jitpack](https://jitpack.io/v/gzu-liyujiang/Logger.svg)](https://jitpack.io/#gzu-liyujiang/Logger)
[![travis-ci](https://travis-ci.org/gzu-liyujiang/Logger.svg?branch=master)](https://travis-ci.org/gzu-liyujiang/Logger)

自用的 Android/Java 日志打印组件，面向接口编程，默认实现了android.util.Log、System.out.println及https://github.com/orhanobut/logger

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
public class MyApp extends Application {

    static {
        // 若使用打印器，需添加依赖（runtimeOnly 'com.orhanobut:logger:2.2.0'）
        Logger.useDefaultPrinter();
        //Logger.usePrinter(log -> System.out.println("替待默认的打印器：" + log));
        Logger.addOtherPrinter(log -> System.out.println("诸如，可以将日志保存到文件：" + log));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.print("Application onCreate");
    }

}

```
```groovy
        Logger.print("测试1");
        Logger.print(new RuntimeException("测试2"));
```
```text
W/liyujiang: ┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────
W/liyujiang: │ Instrumentation.callApplicationOnCreate  (Instrumentation.java:1236)
W/liyujiang: │    MyApp.onCreate  (MyApp.java:38)
W/liyujiang: ├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
W/liyujiang: │ Application onCreate
W/liyujiang: └────────────────────────────────────────────────────────────────────────────────────────────────────────────────
I/System.out: 诸如，可以将日志保存到文件：Application onCreate
W/liyujiang: ┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────
W/liyujiang: │ Activity.performCreate  (Activity.java:7868)
W/liyujiang: │    MainActivity.onCreate  (MainActivity.java:28)
W/liyujiang: ├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
W/liyujiang: │ 测试1
W/liyujiang: └────────────────────────────────────────────────────────────────────────────────────────────────────────────────
I/System.out: 诸如，可以将日志保存到文件：测试1
W/liyujiang: ┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────
W/liyujiang: │ Activity.performCreate  (Activity.java:7868)
W/liyujiang: │    MainActivity.onCreate  (MainActivity.java:29)
W/liyujiang: ├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
W/liyujiang: │ java.lang.RuntimeException: 测试2
W/liyujiang: │ 	at com.github.gzuliyujiang.demo.MainActivity.onCreate(MainActivity.java:29)
W/liyujiang: │ 	at android.app.Activity.performCreate(Activity.java:7868)
W/liyujiang: │ 	at android.app.Activity.performCreate(Activity.java:7857)
W/liyujiang: │ 	at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1353)
W/liyujiang: │ 	at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:3479)
W/liyujiang: │ 	at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:3643)
W/liyujiang: │ 	at android.app.servertransaction.LaunchActivityItem.execute(LaunchActivityItem.java:83)
W/liyujiang: │ 	at android.app.servertransaction.TransactionExecutor.executeCallbacks(TransactionExecutor.java:135)
W/liyujiang: │ 	at android.app.servertransaction.TransactionExecutor.execute(TransactionExecutor.java:95)
W/liyujiang: │ 	at android.app.ActivityThread$H.handleMessage(ActivityThread.java:2225)
W/liyujiang: │ 	at android.os.Handler.dispatchMessage(Handler.java:107)
W/liyujiang: │ 	at android.os.Looper.loop(Looper.java:230)
W/liyujiang: │ 	at android.app.ActivityThread.main(ActivityThread.java:7742)
W/liyujiang: │ 	at java.lang.reflect.Method.invoke(Native Method)
W/liyujiang: │ 	at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:508)
W/liyujiang: │ 	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:1034)
W/liyujiang: └────────────────────────────────────────────────────────────────────────────────────────────────────────────────
I/System.out: 诸如，可以将日志保存到文件：java.lang.RuntimeException: 测试2
I/System.out:     at com.github.gzuliyujiang.demo.MainActivity.onCreate(MainActivity.java:29)
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