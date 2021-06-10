/*
 * Copyright (c) 2013-present, 贵州纳雍穿青人李裕江<1032694760@qq.com>, All Rights Reserved.
 */
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