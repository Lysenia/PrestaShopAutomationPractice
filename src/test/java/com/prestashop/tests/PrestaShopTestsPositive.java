package com.prestashop.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.github.javafaker.Number;

public class PrestaShopTestsPositive {
	
WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		//WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver","/Users/lesia/Documents/selenium dependencies/drivers/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com/index.php");
		
	}
	
	@Test
	public void login()  {
		
		Faker faker = new Faker();
		String email = faker.internet().emailAddress();
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String password = faker.internet().password(5, 7);
		String phone = faker.phoneNumber().cellPhone();
		String zip = faker.address().zipCode().substring(0,5);
		String address = faker.address().streetAddress();
		String city = faker.address().city();
		
		driver.findElement(By.linkText("Sign in")).click();
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS) ;

		driver.findElement(By.xpath("//input[@id='email_create']")).sendKeys(email);
		driver.findElement(By.id("SubmitCreate")).click();
		WebElement radio1 = driver.findElement(By.id("id_gender2"));
		radio1.click();
		driver.findElement(By.id("customer_firstname")).sendKeys(firstName);
		driver.findElement(By.id("customer_lastname")).sendKeys(lastName);
		driver.findElement(By.id("passwd")).sendKeys(password);
		WebElement date = driver.findElement(By.id("days"));
		Select list = new Select (date);
		WebElement selected = list.getFirstSelectedOption();
		WebElement month = driver.findElement(By.id("months"));
		Select list2 = new Select (month);
		WebElement selected2 = list2.getFirstSelectedOption();
		WebElement year = driver.findElement(By.id("years"));
		Select list3 = new Select (year);
		WebElement selected3 = list.getFirstSelectedOption();
				
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
			

		driver.findElement(By.id("address1")).sendKeys(address);
		driver.findElement(By.id("city")).sendKeys(city);
		//	
		WebElement state = driver.findElement(By.id("id_state"));
		Select list4 = new Select (state);
		list4.selectByIndex(2);
		driver.findElement(By.id("postcode")).sendKeys(zip);
		
		driver.findElement(By.id("phone_mobile")).sendKeys(phone);
		driver.findElement(By.id("alias")).sendKeys(address);

		driver.findElement(By.id("submitAccount")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("a[title='View my customer account']>span")).getText(),
				firstName+ " "+ lastName);
		}
		

	
	@AfterMethod
	public void tearDown() {
		driver.close();
	
	}

}
