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
import com.main.application.popups.CartPopup;
import com.main.coreframework.Base;
import com.main.coreframework.SeleniumUtil;
import com.main.properties.Commonconfig;
import com.mycom.aut.base.BaseTest;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;


public class ApplicationTest1 extends BaseTest {
 
    
    @Test(priority=0)
    public void untilHomePage() {
        HomePage homePage = new HomePage();
    try {
       test = report.startTest("in home page");
       test.log(LogStatus.INFO, "launched url");
       log.info("launched URL");
       homePage.signupLink.click();
       
       SignupPage signupPage = new SignupPage();
    
    }catch(Exception e) {
    	e.printStackTrace();
    	Verify.verify(false, "Testc case failed");
    	log.info("Test case failed");
       	test.log(LogStatus.ERROR, "testcase failed");
      }
    }
 

}
