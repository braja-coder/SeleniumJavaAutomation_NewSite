/*
 * Creation : 1 août 2017
 */
package com.main.coreframework;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class SeleniumUtil extends Base {

    public static void mouseHover(WebElement mouseHoverToelement) {
        Actions action = new Actions(driver);
        action.moveToElement(mouseHoverToelement).build().perform();
    }

    public static void maximize() {
        driver.manage().window().maximize();
    }

    public static String captureScreenshot(WebDriver driver, String screenshotName) throws Exception {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        // after execution, you could see a folder "FailedTestsScreenshots" under src folder
        String destination = System.getProperty("user.dir") + "/target/FailedTestsScreenshots/" + screenshotName + dateName + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }

    public static boolean isElementPresent(WebElement element) {
        if (element.isDisplayed()) {
            return true;
        }
        return false;
    }

    public static void selectValuedropDownText(WebElement element, String text) {
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(text);
    }

    public static void selectValuedropDownValue(WebElement element, String value) {
        Select dropdown = new Select(element);
        dropdown.selectByValue(value);
    }

    public static void clickElementByCssSelector(WebElement element) {
        driver.findElement(By.cssSelector("")).click();
    }

    public void waitUntilPageLoads() {
        driver.manage().timeouts().pageLoadTimeout(5000, TimeUnit.SECONDS);
    }

    public static void pageDown() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_PAGE_DOWN);
    }

    public static void pageUp() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_PAGE_UP);
    }

   public static void switchToWindow() {
    	 String parentWindow = driver.getWindowHandle();
    	 Set<String> set  = driver.getWindowHandles();
		 for(String windowHandle : set) {
			 if(!windowHandle.equals(parentWindow)) {
				 driver.switchTo().window(windowHandle);
			}
      }
  }
  
   public static void switchToTab() {
	   driver.switchTo().newWindow(WindowType.TAB);
   }
   public void switchToFrame() {
        driver.switchTo().frame(0);
    }

    public static void javaScriptClick(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.getElementById(element).click();");
    }

    public static boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }
    }

    public void acceprtAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();

    }

    public void cancelAlert() {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();

    }
    
    public String getText(WebElement element) {
    
    	return element.getText();
    }
    
    public static void selectElementFromDropdown(WebElement element1, WebElement element2, String text ) {
    	element1.click();									
    	List<WebElement> list = element2.findElements(By.tagName("span"));
			System.out.println(list + "*********************************************************");
		for(WebElement ele : list) {
			ele.getText().contains(text);
			ele.click();
		}
    }
    
   
}
