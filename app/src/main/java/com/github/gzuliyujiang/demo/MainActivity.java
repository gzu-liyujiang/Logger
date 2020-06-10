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

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.gzuliyujiang.logger.BeautifulPrinter;
import com.github.gzuliyujiang.logger.IPrinter;
import com.github.gzuliyujiang.logger.Logger;

public class MainActivity extends AppCompatActivity {

    static {
        Logger.ENABLE = BuildConfig.DEBUG;
        Logger.TAG = Logger.TAG + "-" + MainActivity.class.getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 没有设置打印器，默认使用android.util.Log
        Logger.print("测试1");
        // 添加一个打印器，需添加依赖（runtimeOnly 'com.orhanobut:logger:2.2.0'）
        Logger.addPrinter(new BeautifulPrinter());
        Logger.print("测试2");
        Logger.addPrinter(new IPrinter() {
            @Override
            public void printLog(String log) {
                System.out.println("诸如，可以将日志保存到文件：" + log);
            }
        });
        Logger.print("测试3");
        Logger.print(new RuntimeException("测试"));
    }

}
