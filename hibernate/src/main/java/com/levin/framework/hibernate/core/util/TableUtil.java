package com.levin.framework.hibernate.core.util;

import com.levin.framework.hibernate.core.annotations.Entity;
import com.levin.framework.hibernate.util.ReflectionUtil;
import com.levin.framework.hibernate.util.StringUtil;

import java.lang.annotation.Annotation;

/**
 * 列表工具类
 *
 * @author Levin
 */
public class TableUtil {
    private TableUtil() {
    }

    /**
     * 获取表名称
     *
     * @param t Entity
     * @return 表名称
     */
    public static <T> String getTableName(T t) {
        Annotation annotation = ReflectionUtil.getAnnotation(t, Entity.class);

        String tableName = ReflectionUtil.getClassName(t);

        if (annotation != null) {
            Entity entity = (Entity) annotation;
            String tableValue = entity.value();
            tableName = StringUtil.isEmpty(tableValue) ? tableName : tableValue;
        }

        return tableName;
    }
}
