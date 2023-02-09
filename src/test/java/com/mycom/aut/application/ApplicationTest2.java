package com.mycom.aut.application;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.main.application.pages.HomePage;
import com.main.application.pages.LoginPage;
import com.main.properties.Commonconfig;
import com.mycom.aut.base.BaseTest;

import junit.framework.Assert;

public class ApplicationTest2 extends BaseTest{

	
	@Test(dataProvider="loginData", dataProviderClass = BaseTest.class)
	public void LoginTest(String email, String password) {
		HomePage homePage = new HomePage();
		homePage.loginLink.click();
		
		LoginPage loginPage = new LoginPage();
	//	loginPage.waitForPageToLoad();
		String text = loginPage.verifyLoginPage.getText();
		Assert.assertEquals(Commonconfig.verifyText, text);
		loginPage.inputEmail.sendKeys(email);
		loginPage.inputPassword.sendKeys(password);
		loginPage.buttonLogin.click();
		
	}
	
	
}
