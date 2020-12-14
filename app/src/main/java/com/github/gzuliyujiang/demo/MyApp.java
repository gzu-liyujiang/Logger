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

package com.github.gzuliyujiang.demo;

import android.app.Application;
import android.content.Context;

import com.github.gzuliyujiang.logger.Logger;

/**
 * Created by liyujiang on 2020/06/13 23:43
 *
 * @author 大定府羡民
 */
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
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Logger.print("Application attachBaseContext");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.print("Application onCreate");
    }

}
