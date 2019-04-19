package com.levin.spring.aop.advisor;

import lombok.Data;

/**
 * @ Author: levin
 * @ date: 2018/1/23 下午10:18
 * @ description:
 */
@Data
public class TargetSource {
    private Class<?> targetClass;

    private Object targetObject;
}
