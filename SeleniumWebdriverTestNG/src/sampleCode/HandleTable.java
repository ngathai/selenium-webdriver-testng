package sampleCode;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HandleTable {

	WebDriver driver = new FirefoxDriver();

	@BeforeTest
	public void setup() throws Exception {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://only-testing-blog.blogspot.in/2014/05/form.html");
	}

	@AfterTest
	public void tearDown() throws Exception {
		driver.close();
	}
	
	/*
	 * This test case is used for get all values in table, check a value is existed or not.
	 */

	@Test
	public void checkDataInTable() {
		String checkValue = "4";
		int count = 0;
		boolean existed = false;
		WebElement table = driver.findElement(By.xpath("//*[@id='post-body-8228718889842861683']/div[1]/table"));
		// Get all tr tag
		List<WebElement> tr_table = table.findElements(By.tagName("tr"));
		// Loop to get all td tag
		for (WebElement el : tr_table) {
			List<WebElement> td_table = el.findElements(By.tagName("td"));
			// Loop to get all values in tb tag
			for (WebElement elm : td_table) {
				String tableValue = elm.getText();
				// System.out.println(tableValue);
				if (tableValue.equals(checkValue)) {
					// Count how many time value is repeated
					count = count + 1;
				}
			}
		}
		System.out.println("Count: " + count);
		if (count >= 1) {
			existed = true;
		} else {
			existed = false;
		}
		Assert.assertEquals(existed, true);
	}
}
