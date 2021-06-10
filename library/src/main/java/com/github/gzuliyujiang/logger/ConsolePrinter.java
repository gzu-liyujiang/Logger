/*
 * Copyright (c) 2016-present 贵州纳雍穿青人李裕江<1032694760@qq.com>
 *
 * The software is licensed under the Mulan PSL v2.
 * You can use this software according to the terms and conditions of the Mulan PSL v2.
 * You may obtain a copy of Mulan PSL v2 at:
 *     http://license.coscl.org.cn/MulanPSL2
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND, EITHER EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT, MERCHANTABILITY OR FIT FOR A PARTICULAR
 * PURPOSE.
 * See the Mulan PSL v2 for more details.
 */
package com.github.gzuliyujiang.logger;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * 面向接口编程，使用接口对各模块进行解耦，增强对第三方库的管控，不强依赖某些三方库，使得三方库可自由搭配组装。
 * <p>
 * 使用该日志打印器需要在`app`的`build.gralde`里添加依赖`runtimeOnly 'com.orhanobut:logger:2.2.0'`
 *
 * @author 大定府羡民
 * @since 2020/4/25
 */
class ConsolePrinter implements IPrinter {
    private static final String MESSAGE = "Please add dependency `runtimeOnly 'com.orhanobut:logger:xxx'` in your app/build.gradle";
    private final String tag;

    public ConsolePrinter(String tag) {
        this.tag = tag;
        try {
            FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                    .showThreadInfo(false)
                    .methodCount(2)
                    .methodOffset(2)
                    .tag(tag)
                    .build();
            com.orhanobut.logger.Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
                @Override
                public boolean isLoggable(int priority, String tag) {
                    return true;
                }
            });
        } catch (NoClassDefFoundError e) {
            android.util.Log.e(tag, MESSAGE, e);
        }
    }

    @Override
    public void printLog(String log) {
        try {
            Class.forName("com.orhanobut.logger.Logger");
            com.orhanobut.logger.Logger.w(log);
        } catch (ClassNotFoundException | NoClassDefFoundError e) {
            try {
                // Android平台专用的日志打印器
                Class.forName("android.util.Log");
                android.util.Log.w(tag, log);
            } catch (ClassNotFoundException | NoClassDefFoundError e1) {
                // Java平台通用的日志打印器
                System.out.println("[" + tag + "]" + log);
            }
        }
    }

}
