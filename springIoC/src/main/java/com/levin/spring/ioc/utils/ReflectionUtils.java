package com.levin.spring.ioc.utils;

import java.lang.reflect.Field;

/**
 * @ Author: levin
 * @ date: 2018/1/14 上午11:00
 * @ description: 通过 Java 反射的原理来完成对象的依赖注入
 */
public class ReflectionUtils {

    /**
     * 设置 obj 的 field 为 value
     * @param field
     * @param obj
     * @param value
     * @throws IllegalAccessException
     */
    public static void injectField(Field field, Object obj, Object value) throws IllegalAccessException {
        if (field != null) {
            field.setAccessible(true);
            field.set(obj, value);
        }
    }
}
