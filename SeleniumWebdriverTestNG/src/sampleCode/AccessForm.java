package sampleCode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AccessForm {

	WebDriver driver = new FirefoxDriver();
	String url = "";

	@BeforeTest
	public void getUrl() {
		driver.get(url);
	}

	@Test
	public void accessForm() {
		//Clear default text
		driver.findElement(By.id("id")).clear();
		//Input new value
		driver.findElement(By.id("id-name")).sendKeys("nadie");
		//Radio button
		driver.findElement(By.cssSelector("input[value='Value']")).click();
		//Check box - the first check is TRUE, second is FALSE
		WebElement checkRemember = driver.findElement(By.id("id-remember"));
		for (int i=0; i<2; i++){
			checkRemember.click();
			System.out.println(checkRemember.isSelected());
		}
		//Link text
		driver.findElement(By.linkText("Register here")).click();
		driver.findElement(By.partialLinkText("Register")).click();
		//Drop-down list
		Select dropCountry = new Select(driver.findElement(By.name("country")));
		dropCountry.selectByVisibleText("America");
		//or
		dropCountry.deselectByVisibleText("EN");
		//Tooltip text: is a text when hover a button...
		WebElement tooltip = driver.findElement(By.id("id"));
		String tooltipText = tooltip.getAttribute("title");
		Assert.assertEquals("", tooltipText);
		//or
		WebElement element = driver.findElement(By.id("age"));
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		WebElement toolTipElement = driver.findElement(By.cssSelector(".ui-tooltip"));
		String toolTipText2 = toolTipElement.getText();
		Assert.assertEquals("", toolTipText2);
		//Multiple select
		Select multiColor = new Select(driver.findElement(By.name("color")));
		multiColor.selectByVisibleText("Blue"); 
		//or 
		multiColor.selectByIndex(0);
		//Submit a form
		driver.findElement(By.id("id-submit")).click();
		//or
		driver.findElement(By.id("id-password")).submit();
	}
}
