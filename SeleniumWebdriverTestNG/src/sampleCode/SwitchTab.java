package sampleCode;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class SwitchTab {
	WebDriver driver = new FirefoxDriver();
	String url = "http://uat2.theluxurycloset.com/lykan/web";

	@BeforeMethod
	public void beforeTest() {
		driver.get(url);
	}

	@AfterMethod
	public void afterTest() {
		driver.close();
	}

	/*
	 * This test case is used for switch between two tabs (open new tab by new
	 * white tab) 1. Open http://uat2.theluxurycloset.com/lykan/web
	 */
	// @Test
	public void switchTabOne() throws InterruptedException {
		// 2. Get title of uat2
		System.out.println(driver.getTitle());
		// 3. Open new white tab
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
		// 4. Access http://blog.theluxurycloset.com on new tab
		driver.get("http://blog.theluxurycloset.com/");
		// 5. Get title of new tab
		System.out.println(driver.getTitle());
		// 6. Back to uat2 tab
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "\t");
		System.out.print(driver.getTitle());
		Thread.sleep(6000);
	}

	/*
	 * This test case is used for switch between two tabs (open new tab by
	 * right-click) 1. Open http://uat2.theluxurycloset.com/lykan/web
	 */
	// @Test
	public void switchTabTwo() throws InterruptedException {
		// 2. Get uat2 tab title
		System.out.println(driver.getTitle());
		// 3. Focus BLOG menu
		WebElement blog = driver.findElement(By.linkText("BLOG"));
		// 4. Right-lick, select open in new tab
		Actions builder = new Actions(driver);
		builder.moveToElement(blog).contextClick().sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(6000);
		// 5. Focus Blog tab
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "\t");
		System.out.println(driver.getTitle());
		Thread.sleep(6000);
	}
}
