package com.github.gzuliyujiang.logger;

import android.util.Log;

/**
 * <pre>
 * 调试日志输出接口，{@link IPrinter}可选择使用以下开源项目进行实现：
 * https://github.com/orhanobut/logger
 * https://github.com/elvishew/xLog
 * https://github.com/ZhaoKaiQiang/KLog
 * https://github.com/fengzhizi715/SAF-Kotlin-log
 * https://github.com/EsotericSoftware/minlog
 * </pre>
 *
 * @author 大定府羡民
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public final class Logger {
    public static String TAG = "liyujiang";
    public static boolean ENABLE = false;
    private static IPrinter printer = new BeautifulPrinter();

    private Logger() {
    }

    /**
     * @see SimplePrinter
     * @see BeautifulPrinter
     */
    public static void usePrinter(IPrinter iPrinter) {
        if (iPrinter == null) {
            return;
        }
        printer = iPrinter;
    }

    /**
     * 打印调试日志，用于开发阶段
     */
    public static void print(Object object) {
        if (!ENABLE) {
            return;
        }
        if (object == null) {
            printer.printLog("NULL");
            return;
        }
        String str;
        if (object instanceof Throwable) {
            str = Log.getStackTraceString((Throwable) object);
        } else {
            str = object.toString();
        }
        printer.printLog(str);
    }


}
