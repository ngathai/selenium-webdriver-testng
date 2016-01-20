package sampleCode;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TakeScreenShotFailTestCase {
	WebDriver driver = new FirefoxDriver();
	String url = "http://qa.theluxurycloset.com/lykan/web/";

	@BeforeTest
	public void beforeTest() {
		driver.get(url);
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}

	@Test
	public void takeScreenshot() throws IOException, InterruptedException {
		WebElement logo = driver.findElement(By.className("logo"));
		logo.click();
		String expectedTitle = "The Luxury Closet";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
		// If call it here, it will take screenshot whether PASS or FAIL
		// screenshot("loginPopupFirstClick");
	}

	public String screenshot(String screenshotName) throws IOException {
		String date = new Date().toString();
		screenshotName = screenshotName + "-" + date;
		File scrShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrShot, new File("./ScreenShots/" + screenshotName + ".png"));
		return screenshotName;
	}

	@AfterMethod
	// Just take screenshot for FAILED test case
	public void TearDown(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			screenshot("loginPopupFirstClick");
		}
	}
}
