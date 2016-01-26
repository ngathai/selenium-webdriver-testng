package sampleCode;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
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

	@Test
	public void f() {
		WebElement mytable = driver.findElement(By.xpath(".//*[@id='post-body-8228718889842861683']/div[1]/table/tbody"));
		//Save rows in list
		List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
		//Count how many rows
		int rows_count = rows_table.size();
		//Save columns in list
		for (int row = 0; row < rows_count; row++) {
			List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
			int columns_count = Columns_row.size();
			System.out.println("Number of cells In Row " + row + " are " + columns_count);
			for (int column = 0; column < columns_count; column++) {
				String celtext = Columns_row.get(column).getText();
				System.out.println("Cell Value Of row number " + row + " and column number " + column + " Is " + celtext);
			}
		}
	}
}
