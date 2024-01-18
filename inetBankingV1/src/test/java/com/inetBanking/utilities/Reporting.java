package com.inetBanking.utilities;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter{
  
	public ExtentSparkReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	public void onStart(ITestContext testContext) 
	{
	
	   String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
	   String repName = "Test Report"+timeStamp+".html";
       htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/"+repName);//Specify Location
       try {
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       
       extent=new ExtentReports();
       extent.attachReporter(htmlReporter);
       extent.setSystemInfo("Hostname", "localhost");
       extent.setSystemInfo("Environment", "QA");
       extent.setSystemInfo("user", "Yash Aggarwal");
       
       htmlReporter.config().setDocumentTitle("InetBanking Test Project");// Title of report
       htmlReporter.config().setReportName("Functional Test Report");//name of the report
       htmlReporter.config().setTheme(Theme.DARK);
       
	}
	  public void onTestSuccess(ITestResult tr)
	  {
		  logger = extent.createTest(tr.getName());// create new entry in th report
		  logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN)); //Send the passed information to extent reports
	  }
	  public void onTestFailure(ITestResult tr)
	  {
		  logger = extent.createTest(tr.getName());// create new entry in th report
		  logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED)); //Send the passed information to extent reports
		  String screenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
		  File f = new File(screenshotPath);
		  if(f.exists())
		  {
			  //logger.fail("Screenshot is below:" +logger.addScreenCaptureFromPath(screenshotPath));
			  logger.addScreenCaptureFromPath(screenshotPath);
		  }
	  } 
	  public void onTestSkipped(ITestResult tr)
	  {
		logger = extent.createTest(tr.getName());
		logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
	  }
	  public void onFinish(ITestContext testContext)
	  {
		  extent.flush();
	  }
}