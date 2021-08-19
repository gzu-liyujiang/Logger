/*
 * Copyright (c) 2013-present, 贵州纳雍穿青人李裕江<1032694760@qq.com>, All Rights Reserved.
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
@SuppressWarnings({"WeakerAccess"})
public final class Logger {
    public static final String TAG = "liyujiang";
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static IPrinter consolePrinter = null;
    private static final List<IPrinter> otherPrinters = new ArrayList<>();

    private Logger() {
        super();
    }

    /**
     * 禁用所有的日志打印器
     */
    public static void disableAllPrinter() {
        disableConsolePrinter();
        disableOtherPrinter();
    }

    /**
     * 启用控制台日志打印器，日志会显示在开发者工具的日志窗口
     */
    public static void enableConsolePrinter(String tag) {
        if (tag == null || tag.trim().length() == 0) {
            tag = TAG;
        }
        consolePrinter = new ConsolePrinter(tag);
    }

    public static void enableConsolePrinter() {
        enableConsolePrinter(null);
    }

    @Deprecated
    public static void enableMainPrinter() {
        enableConsolePrinter();
    }

    @Deprecated
    public static void enableMainPrinter(String tag) {
        enableConsolePrinter(tag);
    }

    /**
     * @see ConsolePrinter
     */
    public static void setConsolePrinter(IPrinter iPrinter) {
        if (iPrinter == null) {
            return;
        }
        consolePrinter = iPrinter;
    }

    @Deprecated
    public static void setMainPrinter(IPrinter iPrinter) {
        setConsolePrinter(iPrinter);
    }

    /**
     * 禁用控制台日志打印器
     */
    public static void disableConsolePrinter() {
        consolePrinter = null;
    }

    @Deprecated
    public static void disableMainPrinter() {
        disableConsolePrinter();
    }

    /**
     * 添加其他日志打印器，如日志记录到本地文件
     */
    public static void addOtherPrinter(IPrinter iPrinter) {
        if (iPrinter == null) {
            return;
        }
        for (IPrinter printer : otherPrinters) {
            if (printer.getClass().getName().equals(iPrinter.getClass().getName())) {
                return;
            }
        }
        otherPrinters.add(iPrinter);
    }

    /**
     * 禁用除控制台日志打印器之外的所有日志打印器
     */
    public static void disableOtherPrinter() {
        otherPrinters.clear();
    }

    /**
     * 打印调试日志，用于开发阶段
     */
    public static synchronized void print(Object object) {
        if (consolePrinter != null) {
            consolePrinter.printLog(formatLog(object));
        }
        if (otherPrinters.size() > 0) {
            String log = formatLog(object);
            for (IPrinter printer : otherPrinters) {
                printer.printLog(log);
            }
        }
    }

    /**
     * 打印调试日志，用于开发阶段
     */
    public static synchronized void print(String format, Object... args) {
        print(String.format(format, args));
    }

    private static String formatLog(Object object) {
        String str;
        if (object == null) {
            str = "NULL";
        } else if (object instanceof Throwable) {
            str = getStackTraceString((Throwable) object);
        } else {
            str = object.toString();
        }
        if (str.length() > 10240) {
            str = str.substring(0, 10240) + "...";
        }
        return str;
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
