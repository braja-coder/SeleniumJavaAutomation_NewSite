package com.main.application.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.main.coreframework.Base;

public class SignupPage extends Base {

	public SignupPage() {
		PageFactory.initElements(driver, this);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 2), this);
	}
	
	String verifySignUpPageLoaded = "//a[contains(text(),'PHPTRAVELS')]";
			
	@FindBy (xpath="//input[@id='inputFirstName']")
	public WebElement firstName;
	
	@FindBy (xpath="//input[@id='inputLastName']")
	public WebElement lastName;
	
	@FindBy (xpath="//input[@id='inputEmail']")
	public WebElement email;
	
	@FindBy (xpath="//div[@class='selected-flag']")
	public WebElement selectDialCode;
	
	@FindBy (xpath="//input[@id='inputAddress1']")
	public WebElement streetAddress;
	
	@FindBy (xpath="//input[@id='inputCity']")
	public WebElement city;
	
	@FindBy (xpath="//input[@id='stateinput']")
	public WebElement state;
	
	@FindBy (xpath="//input[@id='inputPostcode']")
	public WebElement postCode;
	
	@FindBy (xpath="//select[@id='inputCountry']")
	public WebElement selectCountry;
	
	@FindBy (xpath="//input[@id='customfield2']")
	public WebElement mobileNumber;
	
	@FindBy (xpath="//input[@id='inputNewPassword1']")
	public WebElement password;
	
	@FindBy (xpath="//input[@id='inputNewPassword2']]")
	public WebElement cnfPassword;
	
	@FindBy (xpath="//h3[text()='Join our mailing list']/../div")
	public WebElement notification;
	
	@FindBy (xpath="//span[@id='recaptcha-anchor']")
	public WebElement capctcha;
	
	@FindBy (xpath="//input[@type='submit']")
	public WebElement registerButton;
	
	public void waitForPageToLoad() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(verifySignUpPageLoaded)));
	
	}
	
	 public static void highLightElement(WebDriver driverHighLightElement, WebElement element) throws InterruptedException {
	        JavascriptExecutor driver = (JavascriptExecutor) driverHighLightElement;
	        driver.executeScript("arguments[0].setAttribute('style',arguments[1]);",element, "border: 2px solid red;");
	    }
}

