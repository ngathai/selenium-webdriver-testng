package sampleCode;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class HandlingAlertPopup {

	WebDriver driver = new FirefoxDriver();
	String url = "http://";

	@BeforeTest
	public void beforeTest() {
		driver.get(url);
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}

	@Test
	// Alert just has one button is OK
	public void handlingAlert() {
		// Click to show the popup
		driver.findElement(By.id("btnShowAlert")).click();
		// Focus to alert window
		Alert alert = driver.switchTo().alert();
		// Get text
		String actual = alert.getText();
		String expected = "Please enter your e-mail address and password.";
		Assert.assertEquals(actual, expected);
		// Click OK
		alert.accept();
	}

	@Test
	// Confirmation has two buttons are OK and Cancel
	public void handlingConfirmationPopup() {
		// Click to show the popup
		driver.findElement(By.id("btnShowAlert")).click();
		// Focus to alert window
		Alert alert = driver.switchTo().alert();
		// Get text
		String actual = alert.getText();
		String expected = "Please enter your e-mail address and password.";
		Assert.assertEquals(actual, expected);
		// Click OK
		alert.accept();
		// Click Cancel
		alert.dismiss();
	}
	@Test
	public void handlingDatePopup(){
		//Just select date via xpath or id and click on it.
		//Example this page: http://www.makemytrip.com/
	}
}
