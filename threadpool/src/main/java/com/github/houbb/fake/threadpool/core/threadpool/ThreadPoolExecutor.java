/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.threadpool.core.threadpool;


import com.github.houbb.fake.threadpool.core.handler.ThreadThrowableHandler;
import com.github.houbb.fake.threadpool.core.handler.impl.DefaultThreadThrowableHandler;
import com.github.houbb.fake.threadpool.util.ArgUtil;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 对于原来的线程处理进行覆盖。
 * 1. 因为原来的线程执行没有对异常进行任何的处理。
 *
 * @author bbhou
 * @date 2017/9/29
 */
public class ThreadPoolExecutor extends java.util.concurrent.ThreadPoolExecutor {

    /**
     * 异常处理方式
     */
    private ThreadThrowableHandler threadThrowableHandler;

    public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public ThreadThrowableHandler getThreadThrowableHandler() {
        return threadThrowableHandler;
    }

    public void setThreadThrowableHandler(ThreadThrowableHandler threadThrowableHandler) {
        this.threadThrowableHandler = threadThrowableHandler;
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        getActualHandler().handle(r, t);
    }

    /**
     * 获取实际的处理器
     * @return handler
     */
    private ThreadThrowableHandler getActualHandler() {
        ThreadThrowableHandler threadThrowableHandler = getThreadThrowableHandler();
        if(ArgUtil.isNull(threadThrowableHandler)) {
            threadThrowableHandler = new DefaultThreadThrowableHandler();
        }
        return threadThrowableHandler;
    }


}
