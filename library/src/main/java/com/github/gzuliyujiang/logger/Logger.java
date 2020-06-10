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

import android.util.Log;

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
    public static String TAG = "liyujiang";
    public static boolean ENABLE = false;
    private static final List<IPrinter> PRINTERS = new ArrayList<>();

    private Logger() {
    }

    /**
     * Use {@link #addPrinter(IPrinter)} instead
     */
    @Deprecated
    public static void usePrinter(IPrinter iPrinter) {
        if (iPrinter == null) {
            return;
        }
        PRINTERS.clear();
        PRINTERS.add(iPrinter);
    }

    /**
     * @see SimplePrinter
     * @see BeautifulPrinter
     */
    public static void addPrinter(IPrinter iPrinter) {
        if (iPrinter == null) {
            return;
        }
        PRINTERS.clear();
        PRINTERS.add(iPrinter);
    }

    public static List<IPrinter> getPrinters() {
        return PRINTERS;
    }

    /**
     * 打印调试日志，用于开发阶段
     */
    public static void print(Object object) {
        if (!ENABLE) {
            return;
        }
        if (PRINTERS.size() == 0) {
            //没有设置打印器，则使用默认的打印器
            PRINTERS.add(new SimplePrinter());
        }
        String str;
        if (object == null) {
            str = "NULL";
        } else if (object instanceof Throwable) {
            str = Log.getStackTraceString((Throwable) object);
        } else {
            str = object.toString();
        }
        for (IPrinter printer : PRINTERS) {
            printer.printLog(str);
        }
    }


}
