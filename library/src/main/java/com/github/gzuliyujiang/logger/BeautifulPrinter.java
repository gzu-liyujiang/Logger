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

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * 使用该日志打印器需要在`app`的`build.gralde`里添加依赖`com.orhanobut:logger:latest.version`
 * Created by liyujiang on 2020-4-25
 *
 * @author 大定府羡民
 */
public class BeautifulPrinter implements IPrinter {

    static {
        try {
            FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                    .showThreadInfo(false)
                    .methodCount(2)
                    .methodOffset(2)
                    .tag(Logger.TAG)
                    .build();
            com.orhanobut.logger.Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
                @Override
                public boolean isLoggable(int priority, String tag) {
                    return true;
                }
            });
        } catch (NoClassDefFoundError e) {
            if (Logger.ENABLE) {
                android.util.Log.e(Logger.TAG, "runtimeOnly 'com.orhanobut:logger:latest.version' in your app/build.gradle ?", e);
            }
        }
    }

    @Override
    public void printLog(String log) {
        try {
            com.orhanobut.logger.Logger.w(log);
        } catch (NoClassDefFoundError e) {
            if (Logger.ENABLE) {
                android.util.Log.e(Logger.TAG, "runtimeOnly 'com.orhanobut:logger:latest.version' in your app/build.gradle ?", e);
            }
        }
    }

}
