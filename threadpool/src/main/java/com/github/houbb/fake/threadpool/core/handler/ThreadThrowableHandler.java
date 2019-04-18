/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.threadpool.core.handler;

/**
 * 线程异常处理
 *
 * @author bbhou
 * @date 2017/9/29
 */
public interface ThreadThrowableHandler {

    /**
     * 处理线程运行时异常
     * @param runnable 线程
     * @param throwable 异常
     */
    void handle(Runnable runnable, Throwable throwable);

}
