package sampleCode;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;

public class MouseEvent {
	WebDriver driver = new FirefoxDriver();
	String url = "";

	@BeforeTest
	public void beforeTest() {
		driver.get(url);
	}

	@Test
	public void testMouseEvent() {
		WebElement text = driver.findElement(By.id("id-text"));
		Actions builder = new Actions(driver);
		WebElement hoverName = driver.findElement(By.id("id-hover"));
		//Multiple Actions
		Action mouseOverName = builder.moveToElement(hoverName) //Mouse hover
									  .click()
									  .keyDown(text, Keys.SHIFT)
									  .sendKeys(text, "nadie")
									  .keyUp(text, Keys.SHIFT)
									  .doubleClick()
									  .contextClick()
									  .build();
		
		mouseOverName.perform();
	}
}
