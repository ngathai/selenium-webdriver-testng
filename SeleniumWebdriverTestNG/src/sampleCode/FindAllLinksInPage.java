package sampleCode;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FindAllLinksInPage {
	WebDriver driver = new FirefoxDriver();
	String url = "http://uat2.theluxurycloset.com/lykan/web";
	HSSFWorkbook workbook = new HSSFWorkbook();
	HSSFSheet sheet;
	Map<String, Object[]> testresultdata;

	@BeforeTest
	public void beforeTest() {
		driver.get(url);
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}

	@Test
	public void findAllLinks() {
		List<WebElement> links = driver.findElements(By.tagName("a"));
		for (WebElement link : links) {
			String linkText = link.getText();
			String href = link.getAttribute("href");
			System.out.println(linkText + ": " + href);
			testresultdata.put("2", new Object[] { 1d, "Open site", "Check Title", "Pass" });
		}
	}

	@Test
	public void findAllImageSource() {
		List<WebElement> images = driver.findElements(By.tagName("img"));
		for (WebElement img : images) {
			String src = img.getAttribute("src");
			System.out.println(src);
		}
	}
}
