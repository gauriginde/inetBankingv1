package com.inetBanking.testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass{

	@Test
	public void LoginTest() throws IOException {
		//driver.get(baseURL);
		logger.info("URL opened");
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Entered username");
		lp.setPassword(password);
		logger.info("Entered password");
		lp.clickSubmit();
		logger.info("Clicked submit");
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage123")) {
			Assert.assertTrue(true);
			logger.info("Test passed");
		}
		else {
			captureScreen(driver, "LoginTest");
			Assert.assertTrue(false);
			logger.info("Test passed");
		}
		
		//Assert.assertEquals(driver.getTitle(), "Guru99 Bank Manager HomePage");
	}
}
