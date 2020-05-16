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
