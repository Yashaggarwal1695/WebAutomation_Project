package com.inetBanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {

	@Test
	public void loginTest() throws IOException
	{
		driver.get(baseURL);
		LoginPage lp = new LoginPage(driver);
		lp.setUserName("mngr547529");
		lp.setPassword("sYmYqYr");
		
		lp.clickSubmit();
		
		
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			captureScreen(driver, "loginTest");
			Assert.assertTrue(false);
		}
	}
}
