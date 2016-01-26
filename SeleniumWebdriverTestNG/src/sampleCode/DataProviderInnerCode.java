package sampleCode;

import org.testng.annotations.Test;
import java.lang.reflect.Method;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class DataProvider {
	WebDriver driver = new FirefoxDriver();
	String url = "http://uat2.theluxurycloset.com/lykan/web/";

	@BeforeMethod
	public void beforeMethod() {
		driver.get(url);
	}

	@AfterMethod
	public void afterMethod() {
		driver.get(url);
	}
	
	@AfterTest
	public void afterTest(){
		driver.close();
	}

	@Test(priority = 2, dataProvider = "LoginProvider")
	public void testDataProviderLoginValid(String username, String password) throws InterruptedException {
		WebElement btnLoginHeader = driver.findElement(By.className("login-link"));
		btnLoginHeader.click();
		Thread.sleep(5000);
		WebElement txtEmail = driver.findElement(By.className("user-email"));
		txtEmail.sendKeys(username);
		WebElement txtPass = driver.findElement(By.className("user-pwd"));
		txtPass.sendKeys(password);
		WebElement btnLogin = driver.findElement(By.id("login_btn"));
		btnLogin.click();
		Thread.sleep(6000);
		WebElement myacc = driver.findElement(By.className("My-account"));
		String expected = "MY ACCOUNT";
		String actual = myacc.getText();
		Assert.assertEquals(actual, expected);
	}

	@Test(priority = 1, dataProvider = "LoginProvider")
	public void testDataProviderLoginInvalid(String username, String password) throws InterruptedException {
		WebElement btnLoginHeader = driver.findElement(By.className("login-link"));
		btnLoginHeader.click();
		Thread.sleep(5000);
		WebElement txtEmail = driver.findElement(By.className("user-email"));
		txtEmail.sendKeys("");
		WebElement txtPass = driver.findElement(By.className("user-pwd"));
		txtPass.sendKeys("");
		WebElement btnLogin = driver.findElement(By.id("login_btn"));
		btnLogin.click();
		Thread.sleep(6000);
		Alert alert = driver.switchTo().alert();
		String actual = alert.getText();
		String expected = "Please enter your e-mail address and password.";
		Assert.assertEquals(actual, expected);
		alert.accept();
	}

	@org.testng.annotations.DataProvider(name = "LoginProvider")
	public Object[][] getDataFromDataprovider(Method m) {
		if (m.getName().equalsIgnoreCase("testDataProviderLoginValid")) {
			return new Object[][] { { "thaithianhnga@gmail.com", "123456789" } };
		} else {
			return new Object[][] { { "", "123456" }, {"", "654321"} };
		}
	}
}
