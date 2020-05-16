# Logger
[![JitPack](https://jitpack.io/v/gzu-liyujiang/Logger.svg)](https://jitpack.io/#gzu-liyujiang/Logger)
[![Build Status](https://travis-ci.org/gzu-liyujiang/Logger.svg?branch=master)](https://travis-ci.org/gzu-liyujiang/Logger)

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
Logger.usePrinter(new BeautifulPrinter());
Logger.print("测试");
Logger.print(new RuntimeException("测试"));
```