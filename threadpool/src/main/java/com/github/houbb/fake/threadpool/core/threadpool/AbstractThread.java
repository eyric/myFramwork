/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.threadpool.core.threadpool;

/**
 *
 * @author bbhou
 * @date 2017/9/21
 */
public abstract class AbstractThread implements Runnable {

    private ThreadResult threadResult;

    /**
     * 获取线程的名称
     * @return 线程的名称
     */
    public String name() {
        return "";
    }


    public ThreadResult getThreadResult() {
        return threadResult;
    }

    public void setThreadResult(ThreadResult threadResult) {
        this.threadResult = threadResult;
    }
}
