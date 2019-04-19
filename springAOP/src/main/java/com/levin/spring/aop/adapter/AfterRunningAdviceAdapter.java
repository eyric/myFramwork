package com.levin.spring.aop.adapter;

import com.levin.spring.aop.advisor.AfterRunningAdvice;
import com.levin.spring.aop.interceptor.AfterRunningAdviceInterceptor;
import com.levin.spring.aop.interceptor.AopMethodInterceptor;
import com.levin.spring.aop.advisor.Advisor;

/**
 * @ Author: levin
 * @ date: 2018/1/23 下午10:52
 * @ description:
 */
public class AfterRunningAdviceAdapter implements AdviceAdapter {

    private static final AfterRunningAdviceAdapter INSTANTS = new AfterRunningAdviceAdapter();

    private AfterRunningAdviceAdapter() {

    }

    public static AfterRunningAdviceAdapter getInstance() {
        return INSTANTS;
    }
    @Override
    public AopMethodInterceptor getInterceptor(Advisor advisor) {
        AfterRunningAdvice advice = (AfterRunningAdvice) advisor.getAdvice();
        return new AfterRunningAdviceInterceptor(advice);
    }
}
