package com.levin.spring.aop.interceptor;

import com.levin.spring.aop.invocation.MethodInvocation;
import com.levin.spring.aop.advisor.BeforeMethodAdvice;

/**
 * @ Author: levin
 * @ date: 2018/1/23 下午9:50
 * @ description:
 */
public class BeforeMethodAdviceInterceptor implements AopMethodInterceptor {

    private BeforeMethodAdvice advice;

    public BeforeMethodAdviceInterceptor(BeforeMethodAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        advice.before(mi.getMethod(), mi.getArguments(), mi);
        return mi.proceed();
    }
}
