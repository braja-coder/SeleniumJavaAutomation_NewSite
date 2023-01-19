/*
 * Creation : 1 août 2017
 */
package com.main.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.main.allvariables.AllVariables;

public class GetProperties {
    static String path = "/src/test/resources/propertyfiles/common/";

    public static final String getProperty(String filename, String propname) {
        Properties prop = new Properties();
        InputStream fis = null;
        String propvalue = null;
        try {
            fis = new FileInputStream(System.getProperty("user.dir") + path + filename);
            prop.load(fis);
            propvalue = prop.getProperty(propname);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return propvalue;
    }

    public static List<String> getPropertyList(String filename, String propname) {

        Properties prop = new Properties();
        InputStream input = null;
        String[] propvalue = {};

        try {

            input = new FileInputStream(System.getProperty("user.dir") + path + filename);
            // load a properties file from class path, inside static method
            prop.load(input);
            // get the property value and print it out
            propvalue = prop.getProperty(propname).split(",");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        List<String> list = Arrays.asList(propvalue);
        return list;
    }
}
