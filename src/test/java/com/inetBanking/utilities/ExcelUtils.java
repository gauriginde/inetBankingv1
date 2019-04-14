package com.inetBanking.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static XSSFWorkbook workbook;
	public static XSSFSheet worksheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	public static int getRowCount(String path, String sheet) throws IOException {
		fis=new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		worksheet=workbook.getSheet(sheet);
		int rowcount=worksheet.getLastRowNum();
		workbook.close();
		fis.close();
		return rowcount;		
	}
	
	public static int getColCount(String path, String sheet,int rownum) throws IOException {
		fis=new FileInputStream(path);
		workbook=new XSSFWorkbook(fis);
		worksheet=workbook.getSheet(sheet);
		row=worksheet.getRow(rownum);
		int colcount=row.getLastCellNum();
		workbook.close();
		fis.close();
		return colcount;
	}
	
	public static String getCellData(String path, String sheet, int rownum, int colnum) throws IOException {
		fis=new FileInputStream(path);
		workbook=new XSSFWorkbook(fis);
		worksheet=workbook.getSheet(sheet);
		row=worksheet.getRow(rownum);
		cell=row.getCell(colnum);
		String data;
		try {
			DataFormatter formatter=new DataFormatter();
			String celldata=formatter.formatCellValue(cell);
			return celldata;
		}
		catch(Exception e) {
			data= "";
		}
		return data;
	}
	
	public void setCellData(String path, String sheet, int rownum, int colnum,String data) throws IOException {
		fis=new FileInputStream(path);
		fos=new FileOutputStream(path);
		workbook=new XSSFWorkbook(fis);
		worksheet=workbook.getSheet(sheet);
		row=worksheet.getRow(rownum);
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		workbook.write(fos);
		workbook.close();
		fis.close();
		fos.close();
	}
}
