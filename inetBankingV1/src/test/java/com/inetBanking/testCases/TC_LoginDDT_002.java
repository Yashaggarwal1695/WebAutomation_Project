package com.inetBanking.testCases;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.LoginPage;
import com.inetBanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass
{
   @Test(dataProvider="LoginData")
   public void loginDDT(String user,String pwd) throws InterruptedException
   {
	   LoginPage lp = new LoginPage(driver);
	   lp.setUserName(username);
	   lp.setPassword(pwd);
	   lp.clickSubmit();
	   Thread.sleep(3000);
	   if(isAlertPresent()==true)
	   {
		   driver.switchTo().alert().accept();// close alert
		   driver.switchTo().defaultContent();
		   Assert.assertTrue(false);
	   }
	   else
	   {
		   Assert.assertTrue(true);
		   lp.clickLogout();
		   Thread.sleep(3000);
		   driver.switchTo().alert().accept(); // close logout alert
		   driver.switchTo().defaultContent();
	   }
	   
   }
   public boolean isAlertPresent() //User Defined method created to check alert is present or not
   {
	   try {
	   driver.switchTo().alert(); //switch to alert
	   return true;
	   }catch(Exception e)
	   {
		   return false;
	   }
   }
   @DataProvider(name="LoginData")
   String[][] getData() throws IOException
   {
	   String path = System.getProperty("user.dir")+"/src/test/java/com/inetBankingtestData/LoginData.xlsx";
	   int rownum = XLUtils.getRowCount(path, "Sheet1");
	   int cocount = XLUtils .getCellCount(path, "Sheet1", 1);
       
	   String logindata[][]= new String[rownum][cocount];
	   
	   for(int i=1;i<=rownum;i++)
	   {
		   for(int j=0;j<cocount;j++)
		   {
			   logindata[i-1][j]=XLUtils.getCellData(path,"Sheet1",i,j); //1 0
		   }
	   }
	 return logindata ;
   }   
   
}
