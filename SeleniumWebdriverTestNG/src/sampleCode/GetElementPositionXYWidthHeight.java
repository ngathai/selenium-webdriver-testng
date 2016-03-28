package sampleCode;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GetElementPositionXYWidthHeight {
	WebDriver driver = new FirefoxDriver();
	String getURL = "http://only-testing-blog.blogspot.in/";

	@Test
	public void getElementCoordinate() {
		WebElement element = driver.findElement(By.id("header-inner"));
		Point point = element.getLocation();
		// From left side
		int elementX = point.getX();
		// From the top
		int elementY = point.getY();
		System.out.println("The coordinate of title is (" + elementX + "," + elementY + ")");
	}

	@Test
	public void getElementWidthHeight() {
		WebElement element = driver.findElement(By.id("header-inner"));
		int elementWidth = element.getSize().getWidth();
		int elementHeight = element.getSize().getHeight();
		System.out.println("The coordinate of title is (" + elementWidth + "," + elementHeight + ")");
	}

	@BeforeTest
	public void beforeTest() {
		driver.manage().window().maximize();
		driver.get(getURL);
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}

}
