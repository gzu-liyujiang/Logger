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
import java.util.StringTokenizer;

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
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static boolean logEnable = false;
    private static IPrinter mainPrinter;
    private static List<IPrinter> otherPrinters = new ArrayList<>();

    private Logger() {
        super();
    }

    public static void enableDefaultPrinter() {
        enableDefaultPrinter(null);
    }

    /**
     * @deprecated Use {@link #enableDefaultPrinter()} instead
     */
    @Deprecated
    public static void useDefaultPrinter() {
        enableDefaultPrinter();
    }

    public static void enableDefaultPrinter(String tag) {
        if (tag == null || tag.trim().length() == 0) {
            tag = TAG;
        }
        logEnable = true;
        mainPrinter = new DefaultPrinter(tag);
    }

    /**
     * @deprecated Use {@link #enableDefaultPrinter()} instead
     */
    @Deprecated
    public static void useDefaultPrinter(String tag) {
        enableDefaultPrinter(tag);
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
     * Adapted from {@code com.blankj.utilcode.util.ThrowableUtils#getFullStackTrace},
     * like {@link android.util.Log#getStackTraceString}
     */
    public static String getStackTraceString(Throwable throwable) {
        final List<Throwable> throwableList = new ArrayList<>();
        while (throwable != null && !throwableList.contains(throwable)) {
            throwableList.add(throwable);
            throwable = throwable.getCause();
        }
        final int size = throwableList.size();
        final List<String> frames = new ArrayList<>();
        List<String> nextTrace = getStackFrameList(throwableList.get(size - 1));
        for (int i = size; --i >= 0; ) {
            final List<String> trace = nextTrace;
            if (i != 0) {
                nextTrace = getStackFrameList(throwableList.get(i - 1));
                removeCommonFrames(trace, nextTrace);
            }
            if (i == size - 1) {
                frames.add(throwableList.get(i).toString());
            } else {
                frames.add(" Caused by: " + throwableList.get(i).toString());
            }
            frames.addAll(trace);
        }
        StringBuilder sb = new StringBuilder();
        for (final String element : frames) {
            sb.append(element).append(LINE_SEPARATOR);
        }
        return sb.toString();
    }

    private static List<String> getStackFrameList(final Throwable throwable) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        final String stackTrace = sw.toString();
        final StringTokenizer frames = new StringTokenizer(stackTrace, LINE_SEPARATOR);
        final List<String> list = new ArrayList<>();
        boolean traceStarted = false;
        while (frames.hasMoreTokens()) {
            final String token = frames.nextToken();
            // Determine if the line starts with <whitespace>at
            final int at = token.indexOf("at");
            if (at != -1 && token.substring(0, at).trim().isEmpty()) {
                traceStarted = true;
                list.add(token);
            } else if (traceStarted) {
                break;
            }
        }
        return list;
    }

    private static void removeCommonFrames(final List<String> causeFrames, final List<String> wrapperFrames) {
        int causeFrameIndex = causeFrames.size() - 1;
        int wrapperFrameIndex = wrapperFrames.size() - 1;
        while (causeFrameIndex >= 0 && wrapperFrameIndex >= 0) {
            // Remove the frame from the cause trace if it is the same
            // as in the wrapper trace
            final String causeFrame = causeFrames.get(causeFrameIndex);
            final String wrapperFrame = wrapperFrames.get(wrapperFrameIndex);
            if (causeFrame.equals(wrapperFrame)) {
                causeFrames.remove(causeFrameIndex);
            }
            causeFrameIndex--;
            wrapperFrameIndex--;
        }
    }

}
