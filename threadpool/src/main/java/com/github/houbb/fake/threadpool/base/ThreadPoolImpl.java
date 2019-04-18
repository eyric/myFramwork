/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.threadpool.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * <p> </p>
 *
 * <pre> Created: 2018/8/15 上午9:35  </pre>
 * <pre> Project: fake  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
public class ThreadPoolImpl implements IThreadPool {

    /**
     * 工作线程列表
     */
    private final List<WorkThread> workThreads;

    /**
     * 任务线程队列
     */
    private final Queue<Runnable> taskQueue = new LinkedBlockingQueue<>();

    public ThreadPoolImpl(int threadNum) {
        workThreads = new ArrayList<>(threadNum);

        for(int i = 0; i < threadNum; i++) {
            final String threadName = "thread-"+i;
            WorkThread workThread = new WorkThread(taskQueue, threadName);
            // 线程预先启动
            workThread.start();
            workThreads.add(workThread);
            System.out.println(threadName + " is start running");
        }
    }

    @Override
    public void execute(List<Runnable> tasks) {
        synchronized (taskQueue) {
            for(Runnable runnable : tasks) {
                taskQueue.offer(runnable);
            }
            taskQueue.notifyAll();
        }
    }

    @Override
    public void stopAll() {
        for(WorkThread workThread : workThreads) {
            workThread.stopRunning();
        }
    }

    public synchronized int getExecutedCount() {
        int count = 0;
        for(WorkThread workThread : workThreads) {
            count += workThread.getCount().get();
        }
        return count;
    }

}
