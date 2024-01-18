package com.inetBanking.testCases;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.LoggerContext;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

//import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverInfo;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetBanking.utilities.ReadConfig;

public class BaseClass {
  
	ReadConfig readconfig = new ReadConfig();
	public String baseURL = readconfig.getApplicationURL();
	public String username=readconfig.getUsername();
	public String password= readconfig.getPassword();
	public  static WebDriver driver;
	public static  Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br)
	{
		if (br.equals("chrome"))
		{
		 System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
		 driver = new ChromeDriver();
		}
		else if(br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",readconfig.getfirefoxpath());
			 driver = new FirefoxDriver();	
		}
		else if(br.equals("msedge"))
		{
			System.setProperty("webdriver.msedge.driver",readconfig.msedgepath());
			 driver = new EdgeDriver();
		}
	//	logger = LogManager.getLogger(BaseClass);
		//PropertyConfigurator.configure("Log4j.properties");
		//driver= new EdgeDriver();
       // logger.info("This is");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(baseURL);
		driver.manage().window().maximize();
		
	}
	@AfterClass
	public void tearDown()
	{
		driver.quit();
		
	}
	public void captureScreen(WebDriver driver ,String tname) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"/Screenshots/" + tname + ".png");
	    FileUtils.copyFile(source, target);
	    System.out.println("Screenshot taken");
	}
	
	public String randomstring()
	{
		String generatedstring = RandomStringUtils.randomAlphabetic(8);
		return generatedstring;
	}
	
	public String randomNum()
	{
		String generatedstring2 = RandomStringUtils.randomNumeric(4);
		return generatedstring2;
	}
}
