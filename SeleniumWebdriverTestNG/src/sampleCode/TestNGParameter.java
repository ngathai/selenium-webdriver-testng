package sampleCode;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class TestNGParameter {
	//Config value in xml file
	WebDriver driver = new FirefoxDriver();
	String url = "http://uat2.theluxurycloset.com/lykan/web/";

	@Test
	@Parameters({"username", "password"})
	public void testNGParameter(String username, String password) throws InterruptedException {
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

	@BeforeTest
	public void beforeTest() {
		driver.get(url);
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}

}
