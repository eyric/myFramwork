package com.levin.spring.aop.invocation;

/**
 * @ Author: levin
 * @ date: 2018/1/23 下午9:45
 * @ description:
 */
public interface ProxyMethodInvocation extends MethodInvocation {
    /**
     * 获取代理
     * @return
     */
    Object getProxy();
}
