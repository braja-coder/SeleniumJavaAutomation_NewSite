/*
 * Creation : 1 août 2017
 */
package com.main.coreframework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import com.main.allvariables.Declarations;
import com.main.properties.Commonconfig;

public class Base extends Declarations{

       public  void setupDriver(String browser) {
        if (browser.equalsIgnoreCase("IE")) {
            System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/IEDriverServer.exe");
            driver = new InternetExplorerDriver();
            
        }

        else if (browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver.exe");
            driver = new ChromeDriver();
        }
    }

       public  WebDriver invokeDriver() {
    	   return driver;
       }
    public void launchUrl(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }

    public void quitBrowser() {
        driver.close();
        driver.quit();
    }

}
