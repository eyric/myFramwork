package com.levin.hibernate;

import com.levin.hibernate.util.ConnectionUtil;
import com.levin.hibernate.util.FieldUtil;
import com.levin.hibernate.util.TableUtil;
import com.levin.model.User;
import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Session 实现
 *
 * @author Levin
 */
public class Session {
    /**
     * 插入模板
     */
    private static final String INSERT_FORMAT = "INSERT INTO %s ( %s ) VALUES ( %s ) ;";

    /**
     * 保存用户信息
     *
     * @param user
     * @throws SQLException
     */
    public void save(User user) throws SQLException {
        String sql = buildInsertSQL(user);

        Connection con = createConnection();
        PreparedStatement state = (PreparedStatement) con.prepareStatement(sql);
        state.execute();
        con.close();
    }

    /**
     * 构建插入语句
     *
     * @param user
     * @return
     */
    public String buildInsertSQL(User user) {
        String tableName = TableUtil.getTableName(user);
        String fieldString = FieldUtil.getFieldNameString(user);
        String valueString = FieldUtil.getFieldValueString(user);

        return String.format(INSERT_FORMAT, tableName, fieldString, valueString);
    }

    /**
     * 获取数据库链接信息
     *
     * @return
     */
    public Connection createConnection() {
        return ConnectionUtil.getConnection();
    }
}