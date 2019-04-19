package com.levin.spring.aop.adapter;

import com.levin.spring.aop.advisor.Advisor;
import com.levin.spring.aop.advisor.BeforeMethodAdvice;
import com.levin.spring.aop.interceptor.AopMethodInterceptor;
import com.levin.spring.aop.interceptor.BeforeMethodAdviceInterceptor;

/**
 * @ Author: levin
 * @ date: 2018/1/23 下午10:59
 * @ description:
 */
public class BeforeMethodAdviceAdapter implements AdviceAdapter {
    private static final BeforeMethodAdviceAdapter INSTANTS = new BeforeMethodAdviceAdapter();

    private BeforeMethodAdviceAdapter() {

    }

    public static BeforeMethodAdviceAdapter getInstance() {
        return INSTANTS;
    }
    @Override
    public AopMethodInterceptor getInterceptor(Advisor advisor) {
        BeforeMethodAdvice advice = (BeforeMethodAdvice) advisor.getAdvice();
        return new BeforeMethodAdviceInterceptor(advice);
    }
}
