package com.levin.framework.mybatis.em.executor;

import com.levin.framework.mybatis.em.config.EmConfiguration;


public class ExecutorFactory {

    private static final String SIMPLE = "simple";


    public static SimpleExecutor DEFAULT(EmConfiguration configuration) {
        return get(SIMPLE, configuration);
    }

    public static SimpleExecutor get(String key, EmConfiguration configuration) {
        if (SIMPLE.equalsIgnoreCase(key)) {
            return new SimpleExecutor(configuration);
        }
        throw new RuntimeException("no executor found");
    }

    public enum ExecutorType {
        SIMPLE
    }
}
