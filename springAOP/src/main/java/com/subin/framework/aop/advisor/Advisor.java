package com.subin.framework.aop.advisor;

import lombok.Data;

/**
 * @ Author: levin
 * @ date: 2018/1/23 下午10:14
 * @ description:
 */
@Data
public class Advisor {

    private Advice advice;

    private Pointcut pointcut;
}
