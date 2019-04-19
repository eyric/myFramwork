package com.levin.framework.threadpool.core.threadpool;


/**
 * 线程工厂
 *
 * @ Author Levin
 */
public class ThreadFactory implements java.util.concurrent.ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r);
    }

}
