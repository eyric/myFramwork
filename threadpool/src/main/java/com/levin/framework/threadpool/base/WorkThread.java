package com.levin.framework.threadpool.base;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p> 工作线程 </p>
 */
public final class WorkThread extends Thread {

    /**
     * 是否在运行
     */
    private volatile boolean isRunning = true;

    /**
     * 任务计数
     */
    private volatile AtomicInteger count = new AtomicInteger(0);

    /**
     * 任务队列
     */
    private final Queue<Runnable> taskQueue;

    /**
     * 线程名称
     */
    private final String threadName;

    public WorkThread(Queue<Runnable> taskQueue, final String threadName) {
        super(threadName);
        this.threadName = threadName;
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        Runnable runnable = null;
        //死循环
        while (isRunning) {
            //非线程安全，所以采用同步锁
            synchronized (taskQueue) {
                while (isRunning && taskQueue.isEmpty()) {
                    try {
                        //如果任务队列为空，等待20毫秒 监听任务到达
                        taskQueue.wait(20);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //任务队列不为空
                if (!taskQueue.isEmpty()) {
                    //获取第一个任务
                    runnable = taskQueue.poll();
                }
            }
            if (runnable != null) {
                runnable.run();
                System.out.println(threadName + " 执行第 [ " + count.incrementAndGet() + " ] 次任务");
            }
            runnable = null;
        }
    }

    /**
     * 停止运行
     */
    public void stopRunning() {
        this.isRunning = false;
    }

    /**
     * 获取执行的总数
     *
     * @return 总数
     */
    public AtomicInteger getCount() {
        return count;
    }

}
