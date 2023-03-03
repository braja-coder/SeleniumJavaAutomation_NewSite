package com.main.coreframework;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtil {
	private static String path = ".\\src\\test\\resources\\Json\\Data.json";
	private static Object [][] jsonData;
	private static JSONParser parser = new JSONParser();
	private static JSONArray jArray1;
	private static int totalRows,totalCols;
	
	public static int getRowCount() {
		try {
			Object obj = parser.parse(new FileReader(path));
			JSONObject jsonObject = (JSONObject) obj;
			JSONObject menu1 = (JSONObject) jsonObject.get("menu1"); // case sensitive
			JSONObject popup1 = (JSONObject) menu1.get("popup1");
			jArray1 = (JSONArray) popup1.get("menuitem1");
		
		for(int i=0;i<jArray1.size();i++) {
			
			totalRows++;
		}
		}catch (IOException e) {
			e.printStackTrace();
		}
		 catch (ParseException e) {
			e.printStackTrace();
		}
		return totalRows;
	}
	
	public static int getColCount() {
		try {
			Object obj = parser.parse(new FileReader(path));
			JSONObject jsonObject = (JSONObject) obj;
			JSONObject menu1 = (JSONObject) jsonObject.get("menu1"); // case sensitive
			JSONObject popup1 = (JSONObject) menu1.get("popup1");
			jArray1 = (JSONArray) popup1.get("menuitem1");
		
		JSONObject jObjects = (JSONObject) jArray1.get(0);
		totalCols =	jObjects.size();
		}catch (IOException e) {
			e.printStackTrace();
		}
		 catch (ParseException e) {
			e.printStackTrace();
		}
		return totalCols;
	}
	
	public static Object[][] readDataFromJson() {
	jsonData = new Object[totalRows][totalCols];	
	int counter=0;
	for(int i=0;i<jArray1.size();i++) {
		JSONObject jsonObjects = (JSONObject) jArray1.get(i);
		jsonData[i][counter] = (String) jsonObjects.get("username");
		counter++;
		jsonData[i][counter] = (String) jsonObjects.get("password");
		counter=0;
	}
	return jsonData;
	}

}
