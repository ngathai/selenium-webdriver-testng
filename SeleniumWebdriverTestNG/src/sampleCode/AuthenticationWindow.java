package sampleCode;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

/*
 * Authentication Window is a popup require enter username and password whenever access an URL
 */

import org.testng.annotations.Test;

public class AuthenticationWindow {
	WebDriver driver = new FirefoxDriver();
	//Enter username and password in URL to pass the Basic Authentication form.
	String getURL = "http://tlcDev:TLC$2015$ev@uat2.theluxurycloset.com/lykan/web/";

	@Test
	public void authenticationWindow() {
		String actual = driver.getTitle();
		String expected = "The Luxury Closet - Buy & Sell - Authentic pre-owned luxury brands.";
		Assert.assertEquals(expected, actual);
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
