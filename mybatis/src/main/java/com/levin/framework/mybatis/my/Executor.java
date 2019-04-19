package com.levin.framework.mybatis.my;


public interface Executor {
    <E> E query(String sql, Object parameter);
}
