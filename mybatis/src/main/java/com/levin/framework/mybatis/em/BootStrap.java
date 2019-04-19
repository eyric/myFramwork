package com.levin.framework.mybatis.em;

import com.levin.framework.mybatis.em.config.EmConfiguration;
import com.levin.framework.mybatis.em.session.EmSqlSession;
import com.levin.framework.mybatis.beans.Test;
import com.levin.framework.mybatis.em.executor.ExecutorFactory;
import com.levin.framework.mybatis.my.TestMapper;

import java.io.IOException;


public class BootStrap {

    public static void main(String[] args) throws IOException {
        start();
    }

    private static void start() throws IOException {
        EmConfiguration configuration = new EmConfiguration();
        configuration.setScanPath("com.levin.framework.mybatis.em.config.mappers");
        configuration.build();
        EmSqlSession sqlSession = new EmSqlSession(configuration, ExecutorFactory.DEFAULT(configuration));
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        Test test = testMapper.selectByPrimaryKey(1);
        System.out.println(test);
    }
}
