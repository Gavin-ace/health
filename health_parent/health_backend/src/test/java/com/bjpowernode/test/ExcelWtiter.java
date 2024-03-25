package com.bjpowernode.test;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelWtiter {

    public static void main(String[] args) throws IOException {
        //创建工作簿 Workbook
        HSSFWorkbook workbook = new HSSFWorkbook();

        //创建工作表 sheet
        HSSFSheet sheet = workbook.createSheet("sister");

        //创建行 row
        HSSFRow row0 = sheet.createRow(0);
        //创建单元格并赋值 cell
        row0.createCell(0).setCellValue("姓名");
        row0.createCell(1).setCellValue("年龄");

        HSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("刘亦菲");
        row1.createCell(1).setCellValue("18");


        // 新建一输出文件流
        FileOutputStream fOut = new FileOutputStream("C:\\Users\\NINGMEI\\Desktop\\美女.xls");
        // 把相应的Excel 工作簿存盘
        workbook.write(fOut);
        fOut.flush();
        // 操作结束，关闭文件
        fOut.close();
        System.out.println("文件生成");
    }
}
