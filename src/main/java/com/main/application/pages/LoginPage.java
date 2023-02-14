package com.main.application.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.main.allvariables.Declarations;

public class LoginPage {
	
	private WebDriver driver = Declarations.driver;
	public LoginPage() {
		PageFactory.initElements(driver, this);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 2), this);
	}
	@FindBy (xpath="//p[contains(text(),'Sign in to your account to continue.')]")
	public WebElement verifyLoginPage;
	
	@FindBy (xpath="//input[@id='inputEmail']")
	public WebElement inputEmail;
	
	@FindBy (xpath="//input[@id='inputPassword']")
	public WebElement inputPassword;
	
	@FindBy (xpath="//span[@id='recaptcha-anchor']/div[1]")
	public WebElement captcha;
	
	@FindBy (xpath="//button[@id='login']")
	public WebElement buttonLogin;

	@FindBy (xpath="//div[contains(text(),'Login Details Incorrect.')]")
	public WebElement verifyLoginFailed;
	
	public void waitForPageToLoad() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(verifyLoginPage));
	
	}


	
}
