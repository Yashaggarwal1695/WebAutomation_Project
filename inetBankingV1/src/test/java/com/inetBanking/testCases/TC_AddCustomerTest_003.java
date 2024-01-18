package com.inetBanking.testCases;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.AddCustomPage;
import com.inetBanking.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends  BaseClass{

	@Test
	public void addNewCustomer() throws InterruptedException, IOException{
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		lp.setPassword(password);
		lp.clickSubmit();
		
		Thread.sleep(3000);
		
		AddCustomPage addCust= new AddCustomPage(driver);
		addCust.clickAddNewCustomer();
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		addCust.clickAddNewCustomer();
		
		addCust.CustName("Yash Aggarwal");
		addCust.custgender("male");
		addCust.custdob("16", "05", "1995");
		Thread.sleep(3000);
		addCust.custaddress("INDIA");
		addCust.custcity("hyd");
		addCust.custstate("AP");
		addCust.custpinno(5000074);
		addCust.custtelephoneno("7890123456");
		
		String email=randomstring()+"@gmail.com";
		addCust.custemailid(email);
		addCust.custpassword("abcdef");
		addCust.custsubmit();
        
		Thread.sleep(3000);
		
		boolean res = driver.getPageSource().contains("Customer Registered Successfully");
		if(res==true)
		{
			Assert.assertTrue(true);
			
		}
		else
		{
			captureScreen(driver,"addNewCustomer");
			Assert.assertTrue(false);
			
		}
	}
    
	
}
