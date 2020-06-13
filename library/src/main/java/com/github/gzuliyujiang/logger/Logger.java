/*
 * Copyright (c) 2019-2020 gzu-liyujiang <1032694760@qq.com>
 *
 * Logger is licensed under the Mulan PSL v1.
 * You can use this software according to the terms and conditions of the Mulan PSL v1.
 * You may obtain a copy of Mulan PSL v1 at:
 *     http://license.coscl.org.cn/MulanPSL
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND, EITHER EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT, MERCHANTABILITY OR FIT FOR A PARTICULAR
 * PURPOSE.
 * See the Mulan PSL v1 for more details.
 *
 */
package com.github.gzuliyujiang.logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

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
    public static final String TAG = "liyujiang";
    private static boolean logEnable = false;
    private static IPrinter mainPrinter;
    private static List<IPrinter> otherPrinters = new ArrayList<>();

    private Logger() {
        super();
    }

    public static void useDefaultPrinter() {
        useDefaultPrinter(null);
    }

    public static void useDefaultPrinter(String tag) {
        if (tag == null || tag.trim().length() == 0) {
            tag = TAG;
        }
        logEnable = true;
        mainPrinter = new DefaultPrinter(tag);
    }

    /**
     * @see DefaultPrinter
     */
    public static void usePrinter(IPrinter iPrinter) {
        if (iPrinter == null) {
            return;
        }
        mainPrinter = iPrinter;
    }

    public static void addOtherPrinter(IPrinter iPrinter) {
        if (iPrinter == null || otherPrinters.contains(iPrinter)) {
            return;
        }
        otherPrinters.add(iPrinter);
    }

    public static void clearOtherPrinters() {
        otherPrinters.clear();
    }

    /**
     * 打印调试日志，用于开发阶段
     */
    public static synchronized void print(Object object) {
        if (!logEnable) {
            return;
        }
        String str;
        if (object == null) {
            str = "NULL";
        } else if (object instanceof Throwable) {
            str = getStackTraceString((Throwable) object);
        } else {
            str = object.toString();
        }
        mainPrinter.printLog(str);
        for (IPrinter printer : otherPrinters) {
            printer.printLog(str);
        }
    }

    /**
     * Adapted from android.util.Log#getStackTraceString
     */
    public static String getStackTraceString(Throwable tr) {
        if (tr == null) {
            return "";
        }
        Throwable t = tr;
        while (t != null) {
            t = t.getCause();
        }
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        tr.printStackTrace(pw);
        pw.flush();
        return sw.toString();
    }

}
