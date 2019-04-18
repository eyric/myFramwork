/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.threadpool.core.threadpool;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程管理类
 *
 * @author bbhou
 * @date 2017/9/21
 */
public class ThreadManager {

    private static ThreadManager threadManager = new ThreadManager();

    /**
     * 线程池
     */
    private static ExecutorService _pool;

    /**
     * 线程运行结果列表
     */
    private List<Future<ThreadResult>> resultList = new LinkedList<>();

    static {
        //会一直自动扩容的线程池
        _pool = Executors.newCachedThreadPool(new ThreadFactory());
    }

    public static ThreadManager getInstance() {
        return threadManager;
    }


    /**
     * 添加线程
     * @param abstractThread 抽象的线程
     */
    public void addThread(AbstractThread abstractThread) {
        //会将线程运行的结果返回
        System.out.println("add one thread "+abstractThread.name());
        Future<ThreadResult> resultFuture = _pool.submit(abstractThread, abstractThread.getThreadResult());

        try {
            System.out.println("add one thread result: "+resultFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        resultList.add(resultFuture);
    }

    /**
     * 获取运行的结果
     * @return 运行的结果
     */
    public List<Future<ThreadResult>> getResultList() {
        return resultList;
    }


}
