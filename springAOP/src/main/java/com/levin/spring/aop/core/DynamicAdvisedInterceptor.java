package com.levin.spring.aop.core;

import com.levin.spring.aop.interceptor.AopMethodInterceptor;
import com.levin.spring.aop.invocation.CglibMethodInvocation;
import com.levin.spring.aop.invocation.MethodInvocation;
import com.levin.spring.aop.advisor.TargetSource;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @ Author: levin
 * @ date: 2018/1/23 下午10:26
 * @ description:
 */
public class DynamicAdvisedInterceptor implements MethodInterceptor {

    protected final List<AopMethodInterceptor> interceptorList;
    protected final TargetSource targetSource;

    public DynamicAdvisedInterceptor(List<AopMethodInterceptor> interceptorList, TargetSource targetSource) {
        this.interceptorList = interceptorList;
        this.targetSource = targetSource;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        MethodInvocation invocation = new CglibMethodInvocation(obj, targetSource.getTargetObject(), method, args, interceptorList, proxy);
        return invocation.proceed();
    }
}
