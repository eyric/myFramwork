package com.levin.framework.threadpool.base;

import java.util.List;

/**
 * <p> 线程池接口 </p>
 */
public interface IThreadPool {

    /**
     * 添加 task
     *
     * @param tasks 任务
     */
    void execute(List<Runnable> tasks);

    /**
     * 暂停所有
     */
    void stopAll();

}
