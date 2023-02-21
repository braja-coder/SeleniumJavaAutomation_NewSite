/*
 * Creation : 4 janv. 2018
 */
package com.mycom.aut.application;

import org.testng.annotations.Test;
import com.google.common.base.Verify;
import com.main.application.pages.HomePage;
import com.main.application.pages.LoginPage;
import com.main.coreframework.SeleniumUtil;
import com.main.properties.Commonconfig;
import com.mycom.aut.base.BaseTest;
import com.relevantcodes.extentreports.LogStatus;
import junit.framework.Assert;


public class ApplicationTest1 extends BaseTest {
 
	@Test
	public void navigateToLoginPage() {
		HomePage homePage = new HomePage();
		homePage.waitForPageToLoad();
        test = report.startTest(this.getClass().getSimpleName());
        test.log(LogStatus.INFO, "launched url");
        log.info("launched URL for" + this.getClass().getSimpleName()); 
        homePage.loginLink.click();
        SeleniumUtil.switchToWindow();
	}
    @Test(priority=0, dataProvider = "loginData", dependsOnMethods= {"navigateToLoginPage"})
     public void loginWithMultipleUser(String email, String password) {
    	try {
        LoginPage loginPage = new LoginPage();
        loginPage.waitForPageToLoad();
        test.log(LogStatus.INFO, "Login Page loaded");
        log.info("Login Page loaded");
        Assert.assertEquals(loginPage.verifyLoginPage(), Commonconfig.verifyTextLogin);
        loginPage.inputEmail.sendKeys(email);
        loginPage.inputPassword.sendKeys(password);
        loginPage.buttonLogin.click();
        log.info("Login in failed as expected");
    }catch(Exception e) {
    	e.printStackTrace();
    	Verify.verify(false, "Testc case failed");
    	log.error("Test case failed due to" + e.getMessage());
    	test.log(LogStatus.ERROR, "testcase failed");
      }
    }
 

}
