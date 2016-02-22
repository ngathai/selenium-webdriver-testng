package sampleCode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NavigateArrowInBrowser {
	
	WebDriver driver = new FirefoxDriver();
	String url = "http://blog.theluxurycloset.com/";
	
	@BeforeTest
	public void beforeTest() {
		driver.get(url);
		driver.manage().window().maximize();
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}

	@Test
	public void navigateArrow() throws InterruptedException {
		//Open Blog page
		System.out.println(driver.getTitle());
		//Open Authenticity page
		driver.findElement(By.linkText("AUTHENTICITY")).click();
		System.out.println(driver.getTitle());
		Thread.sleep(6000);
		//Go back to Blog page
		driver.navigate().back();
		System.out.println(driver.getTitle());
		Thread.sleep(6000);
		//Go forward to Authenticity page
		driver.navigate().forward();
		System.out.println(driver.getTitle());
	}
}
