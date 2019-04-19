package com.levin.framework.mybatis.em.session;

import com.levin.framework.mybatis.em.mapper.EmMapperProxy;
import com.levin.framework.mybatis.em.executor.Executor;
import com.levin.framework.mybatis.em.config.EmConfiguration;
import com.levin.framework.mybatis.em.config.EmMapperRegistory;

import java.lang.reflect.Proxy;


public class EmSqlSession {
    private EmConfiguration emConfiguration;
    private Executor executor;

    public EmConfiguration getEmConfiguration() {
        return emConfiguration;
    }

    public EmSqlSession(EmConfiguration emConfiguration, Executor executor) {
        this.emConfiguration = emConfiguration;
        this.executor = executor;
    }

    public <T> T getMapper(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz},new EmMapperProxy(this,clazz));
    }

    public <T> T selectOne(EmMapperRegistory.MapperData mapperData, Object parameter) throws Exception {
        return executor.query(mapperData, parameter);
    }
}
