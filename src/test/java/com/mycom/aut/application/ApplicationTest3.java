/*
 * Creation : 4 janv. 2018
 */
package com.mycom.aut.application;

import java.util.logging.Logger;

import org.testng.annotations.Test;

import com.google.common.base.Verify;
import com.main.allvariables.Declarations;
import com.main.application.pages.SignupPage;
import com.main.application.pages.HomePage;
import com.main.application.pages.LoginPage;
import com.main.application.popups.CartPopup;
import com.main.coreframework.Base;
import com.main.coreframework.SeleniumUtil;
import com.main.properties.Commonconfig;
import com.mycom.aut.base.BaseTest;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;


public class ApplicationTest3 extends BaseTest {
 
	@Test
	public void navigateToLoginPage() {
		HomePage homePage = new HomePage();
		homePage.waitForPageToLoad();
        test = report.startTest("in home page");
        test.log(LogStatus.INFO, "launched url");
        log.info("launched URL");
        homePage.loginLink.click();
        SeleniumUtil.switchToWindow();
	}
    @Test(priority=0, dataProvider = "loginDataFromExcel",dataProviderClass = BaseTest.class, dependsOnMethods= {"navigateToLoginPage"})
     public void loginWithMultipleUser(String email, String password) {
    	try {
        LoginPage loginPage = new LoginPage();
        loginPage.waitForPageToLoad();
        Assert.assertEquals(loginPage.verifyLoginPage(), Commonconfig.verifyTextLogin);
        loginPage.inputEmail.sendKeys(email);
        loginPage.inputPassword.sendKeys(password);
        loginPage.buttonLogin.click();
    }catch(Exception e) {
    	e.printStackTrace();
    	Verify.verify(false, "Testc case failed");
    	log.info("Test case failed");
       	test.log(LogStatus.ERROR, "testcase failed");
      }
    }
 

}
