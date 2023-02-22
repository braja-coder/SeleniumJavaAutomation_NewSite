package com.main.coreframework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;
	private static FileInputStream fis;
	private static Map<String,String> map;
	private static List<Map<String,String>> dataList;
	private static ArrayList headerList;
	private static String fileName = ".\\src\\test\\resources\\excelsheets\\TestData.xlsx";
	private static String fileName_withTestName = ".\\src\\test\\resources\\excelsheets\\TestData_withTestCaseName.xlsx";
	private static Object [][] loginData;
	private static List<String> testCaseIdList;
	
	public static List<Map<String,String>> testDataExcel() {
		try {
			fis = new FileInputStream(fileName);
			workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			headerList = new ArrayList();
			dataList = new ArrayList<Map<String,String>>();
			int totalRows = sheet.getLastRowNum();
			int totalCols = sheet.getRow(0).getLastCellNum();
			for (int i = 0; i < totalCols; i++) {
				XSSFRow row = sheet.getRow(0);
					XSSFCell cell = row.getCell(i);
					String header = cell.getStringCellValue().trim();
					headerList.add(header);
				 }
			for (int i = 1; i <=totalRows; i++) {
				XSSFRow row = sheet.getRow(i);
				map = new TreeMap<String,String>();
				for (int j = 0; j < totalCols; j++) {
					XSSFCell cell = row.getCell(j);
					String value = cell.getStringCellValue().trim();
					map.put((String) headerList.get(j), value);
					}
		        	dataList.add(map);
		  }
	  }catch (IOException e) {
			e.printStackTrace();
		}
		return dataList;
	
	}
	public static List<String> getTestCaseIds() {
		try {
			fis = new FileInputStream(fileName_withTestName);
			workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			headerList = new ArrayList();
			testCaseIdList = new ArrayList<String>();
			int totalRows = sheet.getLastRowNum();
			int totalCols = sheet.getRow(0).getLastCellNum();
			for (int i = 1; i <=totalRows; i++) {
				XSSFRow row = sheet.getRow(i);
					XSSFCell cell = row.getCell(0);
					String testNamefromExcel = cell.getStringCellValue().trim();
					testCaseIdList.add(testNamefromExcel);
				 }
		}catch (IOException e) {
		e.printStackTrace();
		}
		return testCaseIdList;
	}
	public static Object[][] testDataExcel(String testCaseId) {
		try {
			fis = new FileInputStream(fileName_withTestName);
			workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			headerList = new ArrayList();
			testCaseIdList = new ArrayList<String>();
			int totalRows = sheet.getLastRowNum();
			int totalCols = sheet.getRow(0).getLastCellNum();
			for (int i = 0; i < totalCols; i++) {
				XSSFRow row = sheet.getRow(0);
					XSSFCell cell = row.getCell(i);
					String header = cell.getStringCellValue().trim();
					headerList.add(header);
				 }
			for (int i = 1; i <=totalRows; i++) {
				XSSFRow row = sheet.getRow(i);
					XSSFCell cell = row.getCell(0);
					String testNamefromExcel = cell.getStringCellValue().trim();
					testCaseIdList.add(testNamefromExcel);
				 }
			int count=1;
			for(String str: testCaseIdList) {
				if(str.equalsIgnoreCase(testCaseId)) {
					XSSFRow row = sheet.getRow(count);
					map = new TreeMap<String,String>();
					for (int j = 1; j < totalCols; j++) {
						XSSFCell cell = row.getCell(j);
						String value = cell.getStringCellValue().trim();
						map.put((String) headerList.get(j), value);
					}
			}
				count++;
		  }
			loginData = new Object[][]{map.values().toArray()};
	  }catch (IOException e) {
			e.printStackTrace();
		}
		return loginData;
	
	}
	
	public static int totalRowCount() {
		int totalRows=0;
		try {
		fis = new FileInputStream(fileName_withTestName);
		workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		totalRows = sheet.getLastRowNum();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return totalRows;
	}
	public static int totalColumnCount() {
		int totalCols=0;
		try {
			fis = new FileInputStream(fileName_withTestName);
			workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			totalCols = sheet.getRow(0).getLastCellNum();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return totalCols;
	}
	public static Object[][] testDataExcelReturnTwoDArray() {
		try {
			fis = new FileInputStream(fileName);
			workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int totalRows = sheet.getLastRowNum();
			System.out.println("totalRows"+totalRows);
			int totalCols = sheet.getRow(0).getLastCellNum();
			System.out.println("totalCols"+totalCols);
			loginData = new Object[totalRows][totalCols];
			int ci=0;
		  for (int i = 1; i <=totalRows; i++) {
				XSSFRow row = sheet.getRow(i);
				for (int j = 0; j < totalCols; j++) {
			     	XSSFCell cell = row.getCell(j);
					String value = cell.getStringCellValue().trim();
					loginData[ci][j] = value;
					}
				ci++;
		   }
	  }catch (IOException e) {
			e.printStackTrace();
		}
		return loginData;
	
	}
}
