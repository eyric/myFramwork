package com.levin.spring.aop.adapter;

import com.levin.spring.aop.advisor.Advisor;
import com.levin.spring.aop.interceptor.AopMethodInterceptor;

/**
 * @ Author: levin
 * @ date: 2018/1/23 下午10:51
 * @ description:
 */
public interface AdviceAdapter {

    AopMethodInterceptor getInterceptor(Advisor advisor);
}
