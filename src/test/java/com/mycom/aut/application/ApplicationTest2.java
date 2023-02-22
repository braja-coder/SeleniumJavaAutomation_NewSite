package com.mycom.aut.application;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.main.application.pages.HomePage;
import com.main.application.pages.LoginPage;
import com.main.application.pages.SignupPage;
import com.main.coreframework.SeleniumUtil;
import com.main.properties.Commonconfig;
import com.mycom.aut.base.BaseTest;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;

public class ApplicationTest2 extends BaseTest{

//	public String testName ;
	
	/*public String getTestName() {
		testName = this.getClass().getSimpleName();
		return testName;
	}*/
	@Test	
	public void navigateToSignUpPage() {
		HomePage homePage = new HomePage();
		homePage.waitForPageToLoad();
        test = report.startTest(testMethodName);
        test.log(LogStatus.INFO, "launched url");
        log.info("launched URL for" + this.getClass().getSimpleName());  
        homePage.signupLink.click();  
        SeleniumUtil.switchToWindow();  
	}
	
	@Test
	public void signUpTest(String email, String password) throws InterruptedException {
		SignupPage signUpPage =  new SignupPage();
		signUpPage.waitForPageToLoad();
		test.log(LogStatus.INFO, "signup Page loaded");
		Assert.assertEquals(signUpPage.verifySignUpPage(), Commonconfig.verifyTextSignUpPage);
		signUpPage.firstName.sendKeys(Commonconfig.firstname);
		signUpPage.firstName.sendKeys(Commonconfig.lastname);
		signUpPage.firstName.sendKeys(Commonconfig.email);
		SeleniumUtil.selectElementFromDropdown(signUpPage.clickonDropDown, signUpPage.getElements, Commonconfig.countryName);
		
	}
	
	
}
