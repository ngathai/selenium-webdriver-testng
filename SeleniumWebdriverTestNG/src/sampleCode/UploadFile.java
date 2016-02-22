package sampleCode;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

//This function might not work on Ubuntu
public class UploadFile {
	WebDriver driver = new FirefoxDriver();
	String url = "http://uat2.theluxurycloset.com/lykan/web/";

	@BeforeTest
	public void beforeTest() {
		driver.get(url);
	}

	@Test(priority = 1)
	public void loginValid() throws InterruptedException {
		WebElement btnLoginHeader = driver.findElement(By.className("login-link"));
		btnLoginHeader.click();
		Thread.sleep(5000);
		WebElement txtEmail = driver.findElement(By.className("user-email"));
		txtEmail.sendKeys("thaithianhnga@gmail.com");
		WebElement txtPass = driver.findElement(By.className("user-pwd"));
		txtPass.sendKeys("123456789");
		WebElement btnLogin = driver.findElement(By.id("login_btn"));
		btnLogin.click();
		Thread.sleep(6000);
	}

	@Test(priority = 2)
	public void singleUpload() throws InterruptedException {
		Actions builder = new Actions(driver);
		WebElement sell = driver.findElement(By.className("sell-bkg"));
		WebElement submitItem = driver.findElement(By.xpath("//*[@id='backgroundimg']/div[3]/div[3]/ul/li[7]/div/div/div[1]/ul/li/a"));
		builder.moveToElement(sell).perform();
		submitItem.click();
		Thread.sleep(5000);
		WebElement btnUpload = driver.findElement(By.xpath("//*[@id='sell-item-0']/div[4]/div[2]/div/div[2]/p"));
		btnUpload.click();
		btnUpload.sendKeys("/home/enclaveit/Desktop/Images/BookTShirtk.jpg");
		driver.findElement(By.name("submit")).click();
		String checkText = driver.findElement(By.id("uploadMessage")).getText();
		Assert.assertEquals("File uploaded successfully", checkText);
	}
}
