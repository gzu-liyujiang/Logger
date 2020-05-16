package com.github.gzuliyujiang.logger;

import android.util.Log;

/**
 * 默认的日志打印器
 *
 * @author 大定府羡民
 */
public class SimplePrinter implements IPrinter {

    @Override
    public void printLog(String log) {
        Log.w(Logger.TAG, log);
    }

}
