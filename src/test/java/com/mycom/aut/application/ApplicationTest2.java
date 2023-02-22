package com.mycom.aut.application;

import java.util.NoSuchElementException;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.helpers.SyslogWriter;
import org.testng.ITestResult;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.base.Verify;
import com.main.application.pages.HomePage;
import com.main.application.pages.LoginPage;
import com.main.application.pages.SignupPage;
import com.main.coreframework.SeleniumUtil;
import com.main.properties.Commonconfig;
import com.mycom.aut.base.BaseTest;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;

public class ApplicationTest2 extends BaseTest{

	private String testName = this.getClass().getSimpleName();
	@Test	
	public void navigateToSignUpPage() {
		HomePage homePage = new HomePage();
		homePage.waitForPageToLoad();
        test = report.startTest(testName);
        test.log(LogStatus.INFO, "launched url");
        log.info("launched URL for" + this.getClass().getSimpleName());  
        homePage.signupLink.click();  
        SeleniumUtil.switchToWindow();  
	}
	
	@Test
	public void signUpTest() throws InterruptedException {
		try {
		SignupPage signUpPage =  new SignupPage();
		signUpPage.waitForPageToLoad();
		test.log(LogStatus.INFO, "signup Page loaded");
		Assert.assertEquals(signUpPage.verifySignUpPage(), Commonconfig.verifyTextSignUpPage);
		signUpPage.firstName.sendKeys(Commonconfig.firstname);
		signUpPage.lastName.sendKeys(Commonconfig.lastname);
		signUpPage.email.sendKeys(Commonconfig.email);
		SeleniumUtil.selectElementFromDropdown(signUpPage.clickonDropDown, signUpPage.getElements, Commonconfig.countryName);
		}catch(Exception e) {
			test.log(LogStatus.FAIL, ExceptionUtils.getRootCauseMessage(e));
			log.error("Test case failed due to" + ExceptionUtils.getRootCauseMessage(e));
			Verify.verify(false);		
			
		}
	}
	
	
}
