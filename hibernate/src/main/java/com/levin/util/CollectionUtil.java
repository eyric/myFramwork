package com.levin.util;

import java.util.Collection;

/**
 * 集合工具类
 * @author Levin
 *
 */
public class CollectionUtil {
    /**
     * 默认连接符
     */
    public static final String CONNECTER = ",";

    /**
     * 是否为空
     *
     * @param array
     * @return
     */
    public static boolean isEmpty(Object[] array) {
        return (array == null) || (array.length <= 0);
    }


    /**
     * Collection<String>,根据连接符转成字符串.
     * 注意：如set 是无序的。。。
     *
     * @param collection
     * @param connector  //连接符默认为","
     * @return
     */
    public static String concatCollection2Str(Collection<String> collection, String... connector) {
        String conn = isEmpty(connector) ? CollectionUtil.CONNECTER : connector[0];
        StringBuilder stringBuilder = new StringBuilder();

        for (String str : collection) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(conn);
            }
            stringBuilder.append(str);
        }

        return stringBuilder.toString();
    }


}
