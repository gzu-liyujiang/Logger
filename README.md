# Logger

![Release APK](https://github.com/gzu-liyujiang/Logger/workflows/Release%20APK/badge.svg)
![Gradle Package](https://github.com/gzu-liyujiang/Logger/workflows/Gradle%20Package/badge.svg)
[![jitpack](https://jitpack.io/v/gzu-liyujiang/Logger.svg)](https://jitpack.io/#gzu-liyujiang/Logger)

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
        implementation 'com.github.gzu-liyujiang:Logger:版本号'
        debugRuntimeOnly 'com.orhanobut:logger:2.2.0'
    }
```

```java
public class MyApp extends Application {
    
    static {
        if (BuildConfig.DEBUG) {
            // 若使用打印器，需添加依赖（runtimeOnly 'com.orhanobut:logger:2.2.0'）
            Logger.enableConsolePrinter();
            //Logger.setConsolePrinter(log -> System.out.println("替待默认的打印器：" + log));
            Logger.addOtherPrinter(log -> System.out.println("诸如，可以将日志保存到文件：" + log));
        } else {
            Logger.disableConsolePrinter();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.print("Application onCreate");
    }

}

```

```java
        Logger.print("测试1");
        Logger.print(new RuntimeException("测试2"));
        Logger.print("当前类：%s", getClass().getName());
```

```text
W/liyujiang: ┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────
W/liyujiang: │ Activity.performCreate  (Activity.java:7802)
W/liyujiang: │    MainActivity.onCreate  (MainActivity.java:28)
W/liyujiang: ├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
W/liyujiang: │ 测试1
W/liyujiang: └────────────────────────────────────────────────────────────────────────────────────────────────────────────────
I/System.out: 诸如，可以将日志保存到文件：测试1
W/liyujiang: ┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────
W/liyujiang: │ Activity.performCreate  (Activity.java:7802)
W/liyujiang: │    MainActivity.onCreate  (MainActivity.java:29)
W/liyujiang: ├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
W/liyujiang: │ java.lang.RuntimeException: 测试2
W/liyujiang: │ 	at com.github.gzuliyujiang.demo.MainActivity.onCreate(MainActivity.java:29)
W/liyujiang: │ 	at android.app.Activity.performCreate(Activity.java:7802)
W/liyujiang: │ 	at android.app.Activity.performCreate(Activity.java:7791)
W/liyujiang: │ 	at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1299)
W/liyujiang: │ 	at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:3245)
W/liyujiang: │ 	at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:3409)
W/liyujiang: │ 	at android.app.servertransaction.LaunchActivityItem.execute(LaunchActivityItem.java:83)
W/liyujiang: │ 	at android.app.servertransaction.TransactionExecutor.executeCallbacks(TransactionExecutor.java:135)
W/liyujiang: │ 	at android.app.servertransaction.TransactionExecutor.execute(TransactionExecutor.java:95)
W/liyujiang: │ 	at android.app.ActivityThread$H.handleMessage(ActivityThread.java:2016)
W/liyujiang: │ 	at android.os.Handler.dispatchMessage(Handler.java:107)
W/liyujiang: │ 	at android.os.Looper.loop(Looper.java:214)
W/liyujiang: │ 	at android.app.ActivityThread.main(ActivityThread.java:7356)
W/liyujiang: │ 	at java.lang.reflect.Method.invoke(Native Method)
W/liyujiang: │ 	at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:492)
W/liyujiang: │ 	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:930)
W/liyujiang: └────────────────────────────────────────────────────────────────────────────────────────────────────────────────
I/System.out: 诸如，可以将日志保存到文件：java.lang.RuntimeException: 测试2
I/System.out:     at com.github.gzuliyujiang.demo.MainActivity.onCreate(MainActivity.java:29)
I/System.out:     at android.app.Activity.performCreate(Activity.java:7802)
I/System.out:     at android.app.Activity.performCreate(Activity.java:7791)
I/System.out:     at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1299)
I/System.out:     at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:3245)
I/System.out:     at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:3409)
I/System.out:     at android.app.servertransaction.LaunchActivityItem.execute(LaunchActivityItem.java:83)
I/System.out:     at android.app.servertransaction.TransactionExecutor.executeCallbacks(TransactionExecutor.java:135)
I/System.out:     at android.app.servertransaction.TransactionExecutor.execute(TransactionExecutor.java:95)
I/System.out:     at android.app.ActivityThread$H.handleMessage(ActivityThread.java:2016)
I/System.out:     at android.os.Handler.dispatchMessage(Handler.java:107)
I/System.out:     at android.os.Looper.loop(Looper.java:214)
I/System.out:     at android.app.ActivityThread.main(ActivityThread.java:7356)
I/System.out:     at java.lang.reflect.Method.invoke(Native Method)
I/System.out:     at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:492)
I/System.out:     at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:930)
W/liyujiang: ┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────
W/liyujiang: │ MainActivity.onCreate  (MainActivity.java:30)
W/liyujiang: │    Logger.print  (Logger.java:145)
W/liyujiang: ├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
W/liyujiang: │ 当前类：com.github.gzuliyujiang.demo.MainActivity
W/liyujiang: └────────────────────────────────────────────────────────────────────────────────────────────────────────────────
I/System.out: 诸如，可以将日志保存到文件：当前类：com.github.gzuliyujiang.demo.MainActivity
```
