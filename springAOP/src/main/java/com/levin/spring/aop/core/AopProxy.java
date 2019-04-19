package com.levin.spring.aop.core;

/**
 * @ Author: levin
 * @ date: 2018/1/23 下午10:22
 * @ description:
 */
public interface AopProxy {

    Object getProxy();

    Object getProxy(ClassLoader classLoader);
}
