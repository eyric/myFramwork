package com.levin.framework.threadpool;

import com.levin.framework.threadpool.base.IThreadPool;
import com.levin.framework.threadpool.base.ThreadPoolImpl;

import java.util.Arrays;

/**
 * 测试
 *
 * @author : Levin
 */
public class Client {
    private static int i = 0;

    public static void main(String[] args) {
        /*ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(() -> {
            int i = 0;
            while (i < 100) {
                System.out.println("---" + i++);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executorService.shutdown();*/


        IThreadPool threadPool = new ThreadPoolImpl(5);
        Runnable task1 = () -> {
            while (i < 100) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":" + i++);
            }
        };

        Runnable task2 = () -> {
            while (i < 100) {
                System.out.println(Thread.currentThread().getName() + ":" + i++);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        threadPool.execute(Arrays.asList(task1, task2));
        //threadPool.stopAll();
    }
}
