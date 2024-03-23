package com.ruoyi.law.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelToMySQL {

    public static void main(String[] args) {
        String excelFilePath = "D:\\大三上课程\\万象生机\\law.xlsx";
        String mySQLUrl = "jdbc:mysql://localhost:3306/db_pss?characterEncoding=utf-8&useSSL=false";
        String mySQLUser = "root";
        String mySQLPassword = "@pwc20021015";
        String mySQLDatabase = "db_pss";


        try (Connection cnx = DriverManager.getConnection(mySQLUrl, mySQLUser, mySQLPassword)) {
            List<String> columnNames = new ArrayList<>();
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(excelFilePath));
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.STRING) {
                        String columnName = cell.getStringCellValue();
                        if (!columnNames.contains(columnName)) {
                            columnNames.add(columnName);
                        }
                    }
                }
            }

            String tableName = "law";
            String createTableQuery = "CREATE TABLE IF NOT EXISTS " + tableName + " (";
            for (String columnName : columnNames) {
                createTableQuery += columnName + " VARCHAR(255),";
            }
            createTableQuery = createTableQuery.substring(0, createTableQuery.length() - 1) + ")";
            cnx.createStatement().executeUpdate(createTableQuery);

            String insertDataQuery = "INSERT INTO " + tableName + " (";
            for (String columnName : columnNames) {
                insertDataQuery += columnName + ",";
            }
            insertDataQuery = insertDataQuery.substring(0, insertDataQuery.length() - 1) + ") VALUES (";
            for (int i = 0; i < columnNames.size(); i++) {
                insertDataQuery += "?,";
            }
            insertDataQuery = insertDataQuery.substring(0, insertDataQuery.length() - 1) + ")";

            PreparedStatement preparedStatement = cnx.prepareStatement(insertDataQuery);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row
                int index = 1;
                for (Cell cell : row) {
                    if (index > columnNames.size()) continue;
                    if (cell.getCellType() == CellType.STRING) {
                        preparedStatement.setString(index++, cell.getStringCellValue());
                    } else {
                        preparedStatement.setString(index++, String.valueOf(cell.getNumericCellValue()));
                    }
                }
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();

            System.out.println("Data from " + excelFilePath + " successfully inserted into " + mySQLDatabase + "." + tableName);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}

