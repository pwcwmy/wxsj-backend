package com.ruoyi.law.util;
import org.apache.poi.ss.usermodel. *;
import org.apache.poi.xssf.usermodel.XSSFWorkbook ;
import org.apache.ibatis.session.SqlSession ;
import org.apache.ibatis.session.SqlSessionFactory ;
import org.apache.ibatis.session.SqlSessionTemplate ;

import java.io.FileInputStream ;
import java.io.IOException ;
import java.util.Iterator ;

public class ExcelToDatabase {
    public static void main(String[] args) {
        try {
            // 加载Excel文件
            FileInputStream excelFile = new FileInputStream("species.xlsx ");
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt (0);

            // 连接数据库
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactory("your-config.xml ");
            SqlSession session = sqlSessionFactory.openSession ();
            SqlSessionTemplate sqlSession = new SqlSessionTemplate(session);

            // 遍历Excel文件中的每一行
            for (Row row : sheet) {
                // 假设我们有一个对应的POJO类来映射Excel数据
                Species species = new Species();
                // 设置POJO对象的属性值
                species.setName (row.getCell (0).getStringCellValue());
                species.setDesc (row.getCell (1).getStringCellValue());
                // 插入数据到数据库
                sqlSession.insert ("insertSpecies", species);
            }

            sqlSession.close ();
            workbook.close ();
            excelFile.close ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
}
