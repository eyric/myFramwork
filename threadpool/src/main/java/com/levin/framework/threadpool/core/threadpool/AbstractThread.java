package com.levin.framework.threadpool.core.threadpool;

/**
 * @ Author Levin
 */
public abstract class AbstractThread implements Runnable {

    private ThreadResult threadResult;

    /**
     * 获取线程的名称
     *
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
