package com.levin.framework.mybatis.em.executor;

import com.levin.framework.mybatis.em.config.EmMapperRegistory;


public interface Executor {
    <T> T query(EmMapperRegistory.MapperData mapperData, Object parameter) throws Exception;
}
