package com.levin.spring.aop.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.levin.spring.aop.bean.AopBeanDefinition;
import com.levin.spring.ioc.bean.BeanDefinition;
import com.levin.spring.ioc.utils.ClassUtils;
import com.levin.spring.ioc.utils.JsonUtils;

import java.io.InputStream;
import java.util.List;

/**
 * @ Author: levin
 * @ date: 2018/1/24 下午11:50
 * @ description:
 */
public class AopApplictionContext extends AopBeanFactoryImpl {

    private String fileName;

    public AopApplictionContext(String fileName) {
        this.fileName = fileName;
    }

    public void init() {
        loadFile();
    }

    private void loadFile() {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

        List<AopBeanDefinition> beanDefinitions = JsonUtils.readValue(is, new TypeReference<List<AopBeanDefinition>>(){});

        if (beanDefinitions != null && !beanDefinitions.isEmpty()) {
            for (AopBeanDefinition beanDefinition : beanDefinitions) {
                Class clazz = ClassUtils.loadClass(beanDefinition.getClassName());
                if (clazz == ProxyFactoryBean.class) {
                    registerBean(beanDefinition.getName(), beanDefinition);
                } else {
                    registerBean(beanDefinition.getName(), (BeanDefinition) beanDefinition);
                }
            }
        }
    }
}
