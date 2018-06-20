package com.prestashop.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PrestaShopTestsNegative {
	
WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		//WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver","/Users/lesia/Documents/selenium dependencies/drivers/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com/index.php");
		driver.findElement(By.linkText("Sign in")).click();
	}
	
	@Test
	public void wrongCredentials() {
		driver.findElement(By.id("email")).sendKeys("lesya@gmail.com");
		driver.findElement(By.id("passwd")).sendKeys("12345");
		driver.findElement(By.id("SubmitLogin")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("#center_column>div>ol>li")).getText(),
				"Authentication failed.");
	}
	
	@Test
	public void invalidEmail() {
		driver.findElement(By.id("email")).sendKeys("lesyagmail.com");
		driver.findElement(By.id("passwd")).sendKeys("12345");
		driver.findElement(By.id("SubmitLogin")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("#center_column>div>ol>li")).getText(),
				"Invalid email address.");
	}
	
	@Test
	public void blankEmail() {
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("passwd")).sendKeys("12345");
		driver.findElement(By.id("SubmitLogin")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("#center_column>div>ol>li")).getText(),
				"An email address required.");
	}
	
	@Test
	public void blankPassword() {
		driver.findElement(By.id("email")).sendKeys("lesialysiak@gmail.com");
		driver.findElement(By.id("passwd")).sendKeys("12345");
		driver.findElement(By.id("SubmitLogin")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("#center_column>div>ol>li")).getText(),
				"Password is required.");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	
	}

}
