package com.subin.framework.mybatis.my;

/**
 * @ Author: levin
 * @ date: 2018/2/28 上午10:20
 * @ description:
 */
public interface Executor {
    <E> E query(String sql, Object parameter);
}
