package com.mycom.aut.application;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.main.application.pages.HomePage;
import com.main.application.pages.LoginPage;
import com.main.coreframework.SeleniumUtil;
import com.main.properties.Commonconfig;
import com.mycom.aut.base.BaseTest;

import junit.framework.Assert;

public class ApplicationTest2 extends BaseTest{

	
	@Test(dataProvider="loginData")
	public void LoginTest(String email, String password) throws InterruptedException {
		HomePage homePage = new HomePage();
		homePage.loginLink.click();
		LoginPage loginPage = new LoginPage();
	//	loginPage.waitForPageToLoad();
		SeleniumUtil.switchToWindow();
		String loginText = loginPage.verifyLoginPage.getText();
		Assert.assertEquals(Commonconfig.verifyTextLogin, loginText);
		loginPage.inputEmail.sendKeys(email);
		loginPage.inputPassword.sendKeys(password);
		Thread.sleep(30000);
		loginPage.buttonLogin.click();
		String failedText = loginPage.verifyLoginFailed.getText();
		System.out.println("**************************************" + failedText.trim());
		Assert.assertEquals(Commonconfig.verifyTextLoginFailed, failedText.trim());
		SeleniumUtil.switchToWindow();	
	}
	
	
}
