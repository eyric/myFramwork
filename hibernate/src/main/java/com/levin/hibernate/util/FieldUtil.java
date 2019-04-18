package com.levin.hibernate.util;


import com.levin.hibernate.annotations.Column;
import com.levin.hibernate.annotations.GenerateValue;
import com.levin.hibernate.annotations.Id;
import com.levin.hibernate.constants.TypeMap;
import com.levin.util.CollectionUtil;
import com.levin.util.StringUtil;
import com.levin.util.ReflectionUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * 字段工具类
 *
 * @author Levin
 */
public class FieldUtil {
    /**
     * 获取对应SQL字段类型
     *
     * @param field Field
     * @return String
     */
    public static String getSqlType(Field field) {
        return TypeMap.getTypeMap().get(field.getType().getTypeName());
    }

    /**
     * 获取字段名称
     *
     * @param field Field
     * @return String
     */
    public static String getFieldName(Field field) {
        String fieldName = field.getName();

        Column column = getFieldColumnAnnotation(field);
        if (column != null) {
            String columnValue = column.value();
            fieldName = StringUtil.isEmpty(columnValue) ? fieldName : columnValue;
        }

        return fieldName;
    }

    /**
     * 获取子弹注解信息
     *
     * @param field Field
     * @return @Column
     */
    public static Column getFieldColumnAnnotation(Field field) {
        Annotation annotation = ReflectionUtil.getAnnotation(field, Column.class);

        if (annotation != null) {
            return (Column) annotation;
        }

        return null;
    }

    /**
     * 获取ID字段
     * - 不存在则返回null
     *
     * @param t T
     * @return Field
     */
    public static <T> Field getIdField(T t) {
        for (Field field : ReflectionUtil.getFieldList(t)) {
            Annotation annotation = ReflectionUtil.getAnnotation(field, Id.class);
            if (annotation != null) {
                return field;
            }
        }

        return null;
    }

    /**
     * 获取自增长字段。
     *
     * @param <T> T
     * @return Field
     */
    public static <T> Field getGenerateValueField(T t) {
        for (Field field : ReflectionUtil.getFieldList(t)) {
            Annotation annotation = ReflectionUtil.getAnnotation(field, GenerateValue.class);
            if (annotation != null) {
                return field;
            }
        }

        return null;
    }

    /**
     * 是否为 GenerateValue 字段
     *
     * @param t     T
     * @param field Field
     * @return 是否GenerateValue 字段
     */
    public static <T> boolean isGenerateValueField(T t, Field field) {
        Field generateValueField = getGenerateValueField(t);
        return field.equals(generateValueField);
    }

    /**
     * 获取字段名称列表
     */
    public static <T> List<String> getFieldNameList(T t) {
        List<String> fieldNameList = new LinkedList<>();

        for (Field field : ReflectionUtil.getFieldList(t)) {
            fieldNameList.add(getFieldName(field));
        }

        return fieldNameList;
    }

    /**
     * 获取字段名称字符串形式
     */
    public static <T> String getFieldNameString(T t) {
        return CollectionUtil.concatCollection2Str(FieldUtil.getFieldNameList(t));
    }

    /**
     * 获取字段值字符串形式
     */
    public static <T> String getFieldValueString(T t) {
        List<String> valueStrList = new LinkedList<>();
        for (Field field : ReflectionUtil.getFieldList(t)) {
            valueStrList.add(String.format("'%s'", getValueString(t, field)));
        }

        return CollectionUtil.concatCollection2Str(valueStrList);
    }

    /**
     * 获取指定字段值字符串
     */
    private static <T> String getValueString(T t, Field field) {
        Object value = ReflectionUtil.getFieldValueForce(t, field.getName());

        String result = value.toString();

        if (ReflectionUtil.isType(field, Date.class)) {
            result = dateToString((Date) value);
        }

        return result;
    }

    /**
     * 日期转字符串
     *
     * @param dateTime 日期
     * @return 字符串
     */
    private static String dateToString(Date dateTime) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateTime);
    }

}
