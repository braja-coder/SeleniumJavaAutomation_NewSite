/*
 * Creation : 4 janv. 2018
 */
package com.mycom.aut.application;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.testng.annotations.Test;
import com.google.common.base.Verify;
import com.main.application.pages.HomePage;
import com.main.application.pages.LoginPage;
import com.main.coreframework.SeleniumUtil;
import com.main.properties.Commonconfig;
import com.mycom.aut.base.BaseTest;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;


public class ApplicationTest4 extends BaseTest {
	private String testName = this.getClass().getSimpleName();
	@Test
	public void navigateToLoginPage() {
		HomePage homePage = new HomePage();
		homePage.waitForPageToLoad();
        test = report.startTest(testName);
        test.log(LogStatus.INFO, "launched url");
        log.info("launched URL for" + this.getClass().getSimpleName());  
        homePage.loginLink.click();  
        SeleniumUtil.switchToWindow();  
	}
    @Test(priority=0, dataProvider = "loginDataFromExcelWithTestCasId",dataProviderClass = BaseTest.class, dependsOnMethods= {"navigateToLoginPage"})
     public void TC004_testWithMultipleUser (String email, String password) {
    	try {
        LoginPage loginPage = new LoginPage();
        loginPage.waitForPageToLoad();
        test.log(LogStatus.INFO, "Login Page loaded");
        log.info("Login Page loaded");
        Assert.assertEquals(loginPage.verifyLoginPage(), Commonconfig.verifyTextLogin);
        loginPage.inputEmail.sendKeys(email);
        loginPage.inputPassword.sendKeys(password);
        loginPage.buttonLogin.click();
        test.log(LogStatus.INFO, "clicked on login button");
        log.info("Login in failed as expected");
    }catch(Exception e) {
		test.log(LogStatus.FAIL, ExceptionUtils.getRootCauseMessage(e));
		log.error("Test case failed due to" + ExceptionUtils.getRootCauseMessage(e));
		Verify.verify(false);		
		
	}
    }
   
}
