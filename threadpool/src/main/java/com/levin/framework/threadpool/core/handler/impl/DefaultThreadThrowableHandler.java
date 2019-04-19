package com.levin.framework.threadpool.core.handler.impl;


import com.levin.framework.threadpool.core.handler.ThreadThrowableHandler;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 默认的线程异常处理方式
 *
 * @ Author Levin
 */
public class DefaultThreadThrowableHandler implements ThreadThrowableHandler {

    @Override
    public void handle(Runnable runnable, Throwable throwable) {

        if (throwable == null && runnable instanceof Future<?>) {
            try {
                Future<?> future = (Future<?>) runnable;
                if (future.isDone()) {
                    future.get();
                }
            } catch (CancellationException ce) {
                throwable = ce;
            } catch (ExecutionException ee) {
                throwable = ee.getCause();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }

        if (throwable != null) {
            String log = String.format("ExceptionName: %s,ExceptionMessage: %s\n", throwable.getClass().getName(), throwable.getMessage());
            System.err.println(log);
        }

    }

}
