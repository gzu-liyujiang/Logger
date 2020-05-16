package com.github.gzuliyujiang.logger;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * Created by liyujiang on 2020-4-25
 *
 * @author 大定府羡民
 */
public class BeautifulPrinter implements IPrinter {

    static {
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
    }

    @Override
    public void printLog(String log) {
        com.orhanobut.logger.Logger.w(log);
    }

}
