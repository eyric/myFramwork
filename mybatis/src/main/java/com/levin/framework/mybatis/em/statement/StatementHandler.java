package com.levin.framework.mybatis.em.statement;

import com.levin.framework.mybatis.em.config.EmConfiguration;
import com.levin.framework.mybatis.em.config.EmMapperRegistory;
import com.levin.framework.mybatis.em.result.ResultSetHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 数据库操作和结果处理分发
 */
public class StatementHandler {

    private final EmConfiguration configuration;

    private final ResultSetHandler resultSetHandler;

    public StatementHandler(EmConfiguration configuration) {
        this.configuration = configuration;
        resultSetHandler = new ResultSetHandler(configuration);
    }

    public <E> E query(EmMapperRegistory.MapperData mapperData, Object parameter) throws Exception {
        try {
            //JDBC
            Connection conn = getConnection();
            //TODO ParamenterHandler
            PreparedStatement pstmt = conn.prepareStatement(String.format(mapperData.getSql(), Integer.parseInt(String.valueOf(parameter))));
            pstmt.execute();
            //ResultSetHandler
            return (E)resultSetHandler.handle(pstmt,mapperData);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Connection getConnection() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/em?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "root";
        String password = "";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
