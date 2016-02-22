package sampleCode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GetCSSValue {
	WebDriver driver = new FirefoxDriver();
	String getURL = "http://enclave.vn/";

	@Test
	public void getCSSValue() {
		WebElement btnCSS = driver.findElement(By.className("title"));
		System.out.println("Before hover: "+btnCSS.getCssValue("color"));
		
		Actions action = new Actions(driver);
		action.moveToElement(btnCSS).perform();
		System.out.println("Hover in: "+btnCSS.getCssValue("color"));
	}

	@BeforeTest
	public void beforeTest() {
		driver.get(getURL);
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}

}
