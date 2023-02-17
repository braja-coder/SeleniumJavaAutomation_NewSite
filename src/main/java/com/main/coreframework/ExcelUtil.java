package com.main.coreframework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private FileInputStream fis;
	private Map<String,String> map;
	private ArrayList<Map<String,String>> dataList;
	private ArrayList headerList;
	private String fileName = ".\\testdata\\TestData.xlsx";
	public void testDataExccel() {
	
		System.out.println(fileName);
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
	
	}
}
