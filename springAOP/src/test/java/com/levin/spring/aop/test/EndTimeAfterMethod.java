package com.levin.spring.aop.test;

import com.levin.spring.aop.advisor.AfterRunningAdvice;

import java.lang.reflect.Method;

/**
 * @ Author: levin
 * @ date: 2018/1/24 下午11:44
 * @ description:
 */
public class EndTimeAfterMethod implements AfterRunningAdvice {
    @Override
    public Object after(Object returnVal, Method method, Object[] args, Object target) {
        long endTime = System.currentTimeMillis();
        long startTime = ThreadLocalUtils.get();
        ThreadLocalUtils.remove();
        System.out.println("方法耗时：" + (endTime - startTime) + "ms");
        return returnVal;
    }
}
