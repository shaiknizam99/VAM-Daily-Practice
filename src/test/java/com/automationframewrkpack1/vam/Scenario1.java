package com.automationframewrkpack1.vam;

import java.io.File;
import java.io.IOException;
import java.util.*;

import java.text.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenario1 
{
	
	public static WebDriver driver;	
	
	
	@Test(priority=1)
	public void launchBrowser() throws Exception
	{
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions(); 
		options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking","enable-automation")); 
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		Thread.sleep(3000);		
	}

	@Test(priority=2, dependsOnMethods = {"launchBrowser"})
	public void launchApplication() throws Exception
	{
		System.out.println("The Browser is launched");
		driver.navigate().to("https://parabank.parasoft.com/parabank/index.htm");
		Scenario1.takeScreenshot();
		driver.findElement(By.name("username")).sendKeys("Bobgally");
		driver.findElement(By.name("password")).sendKeys("Test123");
		driver.findElement(By.xpath("//input[@value=\"Log In\"]")).click();
		Scenario1.takeScreenshot();
		Thread.sleep(2000);			
	}
	
	
	public static void takeScreenshot() throws Exception
	{
		DateFormat df = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
	    df.setTimeZone(TimeZone.getTimeZone("IST"));		
	    TakesScreenshot scrsht = ((TakesScreenshot)driver);
		File src = scrsht.getScreenshotAs(OutputType.FILE);
		File dest = new File("C:\\Users\\NizamShaik\\OneDrive - ValueMomentum, Inc\\Pictures\\Screenshots\\test"+df.format(new Date())+".png");
		FileUtils.copyFile(src, dest);
	}

	@AfterClass
	public void closeBrowser()
	{
		driver.close();
	}


	
}
