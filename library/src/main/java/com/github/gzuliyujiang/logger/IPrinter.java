package com.github.gzuliyujiang.logger;

/**
 * 日志打印接口
 *
 * @author 大定府羡民
 */
public interface IPrinter {
    /**
     * 将调试日志打印到控制台，一般用于开发阶段
     */
    void printLog(String log);

}