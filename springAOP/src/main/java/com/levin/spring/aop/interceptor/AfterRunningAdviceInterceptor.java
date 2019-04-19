package com.levin.spring.aop.interceptor;

import com.levin.spring.aop.invocation.MethodInvocation;
import com.levin.spring.aop.advisor.AfterRunningAdvice;

/**
 * @ Author: levin
 * @ date: 2018/1/23 下午9:59
 * @ description:
 */
public class AfterRunningAdviceInterceptor implements AopMethodInterceptor {

    private AfterRunningAdvice advice;

    public AfterRunningAdviceInterceptor(AfterRunningAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        Object returnVal = mi.proceed();
        advice.after(returnVal, mi.getMethod(), mi.getArguments(), mi);
        return returnVal;
    }
}
