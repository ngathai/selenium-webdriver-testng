package sampleCode;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AutocompleteText {
	WebDriver driver;
	WebDriverWait wait;// Wait until site is loaded completetly

	String URL = "http://jqueryui.com/autocomplete/";
	private By frameLocator = By.className("demo-frame");
	private By tagText = By.id("tags");

	@BeforeClass
	public void Setup() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 5);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void autoComplete() throws InterruptedException {
		driver.navigate().to(URL);
		WebElement frameElement = driver.findElement(frameLocator);
		driver.switchTo().frame(frameElement);
		wait.until(ExpectedConditions.presenceOfElementLocated(tagText));
		WebElement textBoxElement = driver.findElement(tagText);
		textBoxElement.sendKeys("a");
		Thread.sleep(5000);
		// First case can use arrow to select text
		textBoxElement.sendKeys(Keys.ARROW_DOWN);
		textBoxElement.sendKeys(Keys.ENTER);
		/*
		 * Second case can set value to select selectOptionWithText("Java");
		 */
		/*
		 * Third case can set value via index selectOptionWithIndex(2);
		 */
	}

	public void selectOptionWithText(String textToSelect) {
		try {
			WebElement autoOptions = driver.findElement(By.id("ui-id-1"));
			wait.until(ExpectedConditions.visibilityOf(autoOptions));

			List<WebElement> optionsToSelect = autoOptions.findElements(By.tagName("li"));
			for (WebElement option : optionsToSelect) {
				if (option.getText().equals(textToSelect)) {
					System.out.println("Trying to select: " + textToSelect);
					option.click();
					break;
				}
			}

		} catch (NoSuchElementException e) {
			System.out.println(e.getStackTrace());
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	public void selectOptionWithIndex(int indexToSelect) {
		try {
			WebElement autoOptions = driver.findElement(By.id("ui-id-1"));
			wait.until(ExpectedConditions.visibilityOf(autoOptions));

			List<WebElement> optionsToSelect = autoOptions.findElements(By.tagName("li"));
			if (indexToSelect <= optionsToSelect.size()) {
				System.out.println("Trying to select based on index: " + indexToSelect);
				optionsToSelect.get(indexToSelect).click();
			}
		} catch (NoSuchElementException e) {
			System.out.println(e.getStackTrace());
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}
}
