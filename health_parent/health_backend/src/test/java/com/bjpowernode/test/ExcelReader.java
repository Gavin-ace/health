package com.bjpowernode.test;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelReader {
    public static void main(String[] args) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream("C:\\Users\\NINGMEI\\Desktop\\美女.xls"));

        HSSFSheet sheet = workbook.getSheet("sister");

        HSSFRow row1 = sheet.getRow(1);

        String name = row1.getCell(0).getStringCellValue();
        String age = row1.getCell(1).getStringCellValue();
        System.out.println(name+"---------"+age);
    }
}
