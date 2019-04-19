package com.levin.spring.aop.bean;

import com.levin.spring.ioc.bean.BeanDefinition;
import lombok.Data;

import java.util.List;

/**
 * @ Author: levin
 * @ date: 2018/1/23 下午10:40
 * @ description:
 */
@Data
public class AopBeanDefinition extends BeanDefinition {

    private String target;

    private List<String> interceptorNames;
}
