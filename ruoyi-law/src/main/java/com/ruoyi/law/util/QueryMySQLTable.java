package com.ruoyi.law.util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


public class QueryMySQLTable {
    private static final Logger log = LoggerFactory.getLogger(QueryMySQLTable.class);
    public static void main(String[] args) {
        Properties dbConfig = new Properties();
        try (FileInputStream fis = new FileInputStream("ruoyi-law/src/main/resources/db.properties")) {
            dbConfig.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String url = dbConfig.getProperty("db.url");
        String username = dbConfig.getProperty("db.username");
        String password = dbConfig.getProperty("db.password");
        log.info("db.url: {}", url);
        log.info("db.username: {}", username);
        log.info("db.password: {}", password);
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // 载入MySQL JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 建立数据库连接
            conn = DriverManager.getConnection(url, username, password);

            // 获取表的元数据信息
            DatabaseMetaData metaData = conn.getMetaData();

            // 查询表的字段列表
            String tableName = "oss_species";
            ResultSet columns = metaData.getColumns(null, null, tableName, null);
            StringBuilder columnBuilder = new StringBuilder(); // 创建StringBuilder对象用于构建输出字符串
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                // 如果不是第一个字段，就添加一个逗号分隔符
                if (columnBuilder.length() > 0) {
                    columnBuilder.append(", ");
                }
                columnBuilder.append(columnName); // 将字段名添加到StringBuilder中
            }
            System.out.println(columnBuilder.toString());
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                int columnType = columns.getInt("DATA_TYPE");
                System.out.println("Column Name: " + columnName + ", Type: " + columnType);
            }

            // 查询表数据
            String querySql = "SELECT * FROM " + tableName;
            pstmt = conn.prepareStatement(querySql);
            rs = pstmt.executeQuery();

            // 打印表数据
            while (rs.next()) {
                // 获取当前行的所有字段
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    String data = rs.getString(i);
                    System.out.print(rs.getMetaData().getColumnLabel(i) + ": " + data + "  ");
                }
                System.out.println();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}