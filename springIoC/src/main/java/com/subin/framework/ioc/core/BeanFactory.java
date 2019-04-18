package com.subin.framework.ioc.core;

/**
 * @ Author: levin
 * @ date: 2018/1/14 上午11:03
 * @ description:
 */
public interface BeanFactory {
    Object getBean(String name) throws Exception;
}
