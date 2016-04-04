package sampleCode;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
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
		/*
		 * The process is move mouse to hoverText then select clickText and click on it
		 */
		WebElement hoverText = driver.findElement(By.id("hover-text"));
		WebElement clickText = driver.findElement(By.id("click-text"));
		Actions builder = new Actions(driver);
		builder.moveToElement(hoverText).build().perform();
		clickText.click();

//		Action mouseOverName = builder.moveToElement(hoverName) //Mouse hover
//									  .click()
//									  .keyDown(text, Keys.SHIFT)
//									  .sendKeys(text, "nadie")
//									  .keyUp(text, Keys.SHIFT)
//									  .doubleClick()
//									  .contextClick()
//									  .build();
//		
//		mouseOverName.perform();
	}
}
