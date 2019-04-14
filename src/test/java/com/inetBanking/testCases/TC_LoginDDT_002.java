package com.inetBanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.HomePage;
import com.inetBanking.pageObjects.LoginPage;
import com.inetBanking.utilities.ExcelUtils;

public class TC_LoginDDT_002 extends BaseClass{

	//WebDriver driver;
	//ExcelUtils exu;
	
	@Test(dataProvider="LoginData")
	public void LoginDDT(String uname, String password) {
		LoginPage loginpage=new LoginPage(driver);
		HomePage homepage=new HomePage(driver);
		loginpage.setUserName(uname);
		loginpage.setPassword(password);
		loginpage.clickSubmit();
		
		if(isAlertPresent()==true) {
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
		}
		else {
			homepage.clicklogout();
			driver.switchTo().alert().accept();
			//exu.setCellData(path, sheet, rownum, colnum, data);
		}
		
	}
	
	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		}
		catch(NoAlertPresentException e) {
			return false;
		}
		
	}

	@DataProvider(name="LoginData")
	String[][] getData() throws IOException {
		String path=System.getProperty("user.dir")+"\\src\\test\\java\\com\\inetBanking\\testData\\inetBanking_TestData.xlsx";
		int rowcount=ExcelUtils.getRowCount(path, "Sheet1");
		System.out.println("Rowcount = "+rowcount);
		int colcount=ExcelUtils.getColCount(path, "Sheet1", rowcount);
		String logindata[][]=new String[rowcount][colcount];
		for(int i=1;i<=rowcount;i++) {
			for(int j=0;j<colcount;j++) {
				logindata[i-1][j]=ExcelUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		return logindata;
		
	}
}
