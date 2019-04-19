package com.levin.spring.aop.interceptor;

import com.levin.spring.aop.invocation.MethodInvocation;

/**
 * @ Author: levin
 * @ date: 2018/1/23 下午9:49
 * @ description:
 */
public interface AopMethodInterceptor {

    Object invoke(MethodInvocation mi) throws Throwable;
}
