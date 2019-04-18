/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.threadpool.base;

import java.util.List;

/**
 * <p> 线程池接口 </p>
 *
 * <pre> Created: 2018/8/15 上午9:33  </pre>
 * <pre> Project: fake  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
public interface IThreadPool {

    /**
     * 添加 task
     * @param tasks 任务
     */
    void execute(List<Runnable> tasks);

    /**
     * 暂停所有
     */
    void stopAll();

}
