package sampleCode;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ScrollPage {
	WebDriver driver = new FirefoxDriver();
	String url = "http://uat2.theluxurycloset.com/lykan/web/";

	@BeforeTest
	public void beforeTest() {
		driver.get(url);
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}

	@Test
	public void scrollPage() throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		//Scroll down
		jse.executeScript("window.scrollBy(0,1000)", "");
		Thread.sleep(6000);
		//Scroll up
		jse.executeScript("window.scrollBy(0,-1000)", "");
		Thread.sleep(6000);
		//Scroll down to bottom
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.END).perform();
		Thread.sleep(6000);
		//Scroll up to top
		actions.sendKeys(Keys.HOME).perform();
		Thread.sleep(6000);
		//Scroll to specific element
		WebElement element = driver.findElement(By.className("home-categories"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

	}
}
