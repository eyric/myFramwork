/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.threadpool.core.threadpool;

/**
 * 线程运行结果
 *
 * @author bbhou
 * @date 2017/9/21
 */
public class ThreadResult {

    /**
     * 是否成功
     */
    private boolean isSuccess;

    /**
     * 错误异常信息
     */
    private Exception exception;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        return "ThreadResult{" +
                "isSuccess=" + isSuccess +
                ", exception=" + exception +
                '}';
    }
}
