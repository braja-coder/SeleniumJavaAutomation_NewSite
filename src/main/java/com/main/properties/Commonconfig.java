/*
 * Creation : 1 août 2017
 */
package com.main.properties;

import com.main.allvariables.AllVariables;

public class Commonconfig extends GetProperties{
		
	//common properties
	public static final String URL = getProperty("config.properties", "url");
	public static final String browser = getProperty("config.properties", "browser");
	public static final String delivarydate = getProperty("config.properties", "delivarydate");
	public static final String firstname = getProperty("config.properties", "firstname");
	public static final String lastname = getProperty("config.properties", "lastname");
	public static final String email = getProperty("config.properties", "email");
	public static final String telephone = getProperty("config.properties", "telephone");
	
	public static final String address1 = getProperty("config.properties", "address1");
	public static final String city = getProperty("config.properties", "city");
	public static final String postcode = getProperty("config.properties", "postcode");
	public static final String country = getProperty("config.properties", "country");
	public static final String state = getProperty("config.properties", "state");
	public static final String orderplacemessage = getProperty("config.properties", "orderplacemessage");
	
	
	
	
   
}
