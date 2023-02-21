/*
 * Creation : 12 janv. 2018
 */
package com.mycom.aut.base;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import com.main.coreframework.Base;
import com.main.coreframework.ExcelUtil;
import com.main.coreframework.SeleniumUtil;
import com.main.properties.Commonconfig;
import com.main.report.ExtentReport;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest extends Base{
	protected static Logger log;
	
	 @BeforeSuite
	    public void initializeExtentReportAndSetLogs() {
	        report = ExtentReport.initializeReportConfig();
	        log = Logger.getLogger(BaseTest.class);
	        PropertyConfigurator.configure("D:\\Selenium_Cucumbe_-Karate_RestAssurred_API_Automation\\SeleniumJavaAutomation_NewSite\\src\\test\\resources\\propertyfiles\\log4j.properties");
	    }
	    @BeforeClass
	    public void launchApplication() {
	    	setupDriver(Commonconfig.browser);
	    	launchUrl(Commonconfig.URL);
	    }
	    
	   @AfterMethod(alwaysRun = true)
	    public void getResult(ITestResult result) throws Exception {
	        if (result.getStatus() == ITestResult.FAILURE) {
	            test.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
	            test.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
	            String screenshotPath = SeleniumUtil.captureScreenshot(driver, result.getName());
	            test.log(LogStatus.FAIL, test.addScreenCapture(screenshotPath));
	         
	        } else if (result.getStatus() == ITestResult.SKIP) {
	            test.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
	        }
	        report.endTest(test);
	        report.flush();
	    }

	   

	    @AfterClass
	    public void logoutFromApplication() {
	    	quitBrowser();
	        report.endTest(test);
	        report.flush();
	    }

	    @DataProvider(name = "loginData")
	    public Object[][] loginData() {
	    	
	    	Object[][] loginDetails= new Object[2][2];
	    	loginDetails[0][0]= Commonconfig.userName1;
	    	loginDetails[0][1]= Commonconfig.password1;
	    	loginDetails[1][0]= Commonconfig.userName2;
	    	loginDetails[1][1]= Commonconfig.password2;
	    	return loginDetails;
	    	
	    }
	    
	    @DataProvider(name = "loginDataFromExcel")
	    public Object[][] loginDataWithExcel() {
	    	
	    	Object[][] loginDetails= new Object[2][2];
	    	loginDetails=(Object[][]) ExcelUtil.testDataExcelReturnTwoDArray();
	    	return loginDetails;
	    	
	    }
}







