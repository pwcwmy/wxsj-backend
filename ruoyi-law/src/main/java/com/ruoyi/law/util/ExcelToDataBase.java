package com.ruoyi.law.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.*;
import java.util.Iterator;
import java.util.Properties;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ExcelToDataBase {
    private static final Logger log = LoggerFactory.getLogger(QueryMySQLTable.class);
    public static boolean checkPrimaryKeyExists(Connection conn, int key, String TABLE, String KEY_COLUMN) {
        log.info("checking primary key");
        String sql = "SELECT COUNT(*) FROM " + TABLE + " WHERE " + KEY_COLUMN + " = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, key);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                     return resultSet.getInt(1) > 0; // 如果计数大于0，则主键存在
                }
                return false; // 否则，主键不存在
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // 如果查询出现错误，返回false
        }
    }

    public static void main(String[] args) throws IOException {
        Properties dbConfig = new Properties();
        try (FileInputStream fis = new FileInputStream("ruoyi-law/src/main/resources/db.properties")) {
            dbConfig.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String excelFilePath = dbConfig.getProperty("db.excelFilePath");
        String url = dbConfig.getProperty("db.url");
        String username = dbConfig.getProperty("db.username");
        String password = dbConfig.getProperty("db.password");
        String table = dbConfig.getProperty("db.table");

        // 读取Excel文件
        Workbook workbook = new XSSFWorkbook(Files.newInputStream(new File(excelFilePath).toPath()));
        Sheet sheet = workbook.getSheetAt(0); // 假设数据在第一个sheet
//        Iterator<Row> iterator = sheet.iterator();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            // 获取表的元数据信息
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, table, null);
            StringBuilder columnBuilder = new StringBuilder();
            int columnCount = 0; // 用于统计字段数量的变量

            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                columnCount++; // 增加字段计数
                if (columnBuilder.length() > 0) {
                    columnBuilder.append(", "); // 从第二个字段开始添加逗号分隔符
                }
                columnBuilder.append(columnName); // 将字段名添加到StringBuilder中
            }

            String columnList = columnBuilder.toString();
            log.info(columnList);
            StringBuilder placeholder1 = new StringBuilder();
            while (columnCount > 0) {
                columnCount--;
                if (placeholder1.length() > 0) {
                    placeholder1.append(",");
                }
                placeholder1.append("?");
            }
            String placeholder = placeholder1.toString();
            log.info(placeholder);
            String sql = String.format("INSERT INTO %s (%s) VALUES (%s)",
                    table, columnList, placeholder);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            Row headerRow = sheet.getRow(0); // 假设第一行是表头
            int numberOfColumns = headerRow.getLastCellNum();

            for (Row row : sheet) {
                if (row.getRowNum() == 0) { // 跳过标题行
                    continue;
                }
                // 第一列转换为整数类型，并假设它是主键
                Cell cell1 = row.getCell(0);
                int firstCellValue = 0;
                if (cell1 != null && cell1.getCellType() == CellType.NUMERIC) {
                    firstCellValue = (int) cell1.getNumericCellValue();
                    log.info("1 numeric");
                } else {
                    log.info("First cell is not numeric, skipping row.");
                    continue; // 如果第一列不是数字，跳过当前行
                }
                // 检查主键是否已存在
                boolean keyExists = checkPrimaryKeyExists(connection, firstCellValue, table, "id");
                if (keyExists) {
                    log.info("Key already exists, skipping insert.");
                    continue; // 如果主键已存在，跳过插入
                }
                preparedStatement.setInt(1, firstCellValue);
                // 其他列保持为字符串类型
                for (int i = 1; i < numberOfColumns; i++) {
                    Cell cell = row.getCell(i);
                    String cellValue = cell != null ? cell.toString() : "";
                    preparedStatement.setString(i + 1, cellValue);
                }
//                log.info("preparedStatement" + preparedStatement);
                preparedStatement.executeUpdate();
            }

            preparedStatement.close();
            connection.close();
            workbook.close();
        } catch  (Exception     e) {
            e.printStackTrace();
        }
    }
}


