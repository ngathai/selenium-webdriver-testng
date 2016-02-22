package sampleCode;

import static org.testng.AssertJUnit.assertEquals;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WriteTestCaseResultToExcel {
	private WebDriver browser;
	// define an Excel Work Book
	HSSFWorkbook workbook;
	// define an Excel Work sheet
	HSSFSheet sheet;
	// define a test result data object
	Map<String, Object[]> testresultdata;
	
	@Test(priority=1, description = "Open site")
	public void openSite() throws InterruptedException {
		browser.manage().window().maximize();
		browser.get("http://uat2.theluxurycloset.com/lykan/web");

		try {
			assertEquals(browser.getTitle(), "The Luxury Closet - Buy & Sell - Authentic pre-owned luxury brands.");
			// add pass entry to the excel sheet
			testresultdata.put("2", new Object[] { 1d, "Open site", "Check Title", "Pass" });
		}
		catch (Exception e) {
			// add fail entry to the excel sheet
			testresultdata.put("2", new Object[] { 1d, "Open site", "Check Title", "Fail" });
		}
	}

	@Test(priority=2, description = "Login")
	public void loginValid() throws InterruptedException {
		WebElement btnLoginHeader = browser.findElement(By.className("login-link"));
		btnLoginHeader.click();
		Thread.sleep(5000);
		WebElement txtEmail = browser.findElement(By.className("user-email"));
		txtEmail.sendKeys("thaithianhnga@gmail.com");
		WebElement txtPass = browser.findElement(By.className("user-pwd"));
		txtPass.sendKeys("123456789");
		WebElement btnLogin = browser.findElement(By.id("login_btn"));
		btnLogin.click();
		Thread.sleep(6000);
		try {
			assertEquals(browser.findElement(By.className("My-account")).getText(), "MY ACCOUNT");
			testresultdata.put("3", new Object[] { 2d, "Login valid account", "Login successfully", "Pass" });
		}
		catch (Exception e) {
			testresultdata.put("3",
					new Object[] { 2d, "Login valid account", "Not successfully", "Fail" });
		}
	}

	/*
	 * Create Excel sheet to save results
	 */
	@BeforeClass(alwaysRun = true)
	public void setupBeforeSuite(ITestContext context) {
		// create a new work book
		workbook = new HSSFWorkbook();
		// create a new work sheet
		sheet = workbook.createSheet("Test Result");
		testresultdata = new LinkedHashMap<String, Object[]>();
		// add test result excel file column header
		// write the header in the first row
		testresultdata.put("1", new Object[] { "Test Case ID", "Name", "Expected Result", "Actual Result" });

		try {

			browser = new FirefoxDriver();
			browser.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			throw new IllegalStateException("Can't start Web Driver", e);
		}

	}

	/*
	 * Create Excel file to save result
	 */
	@AfterClass
	public void setupAfterSuite() {
		// write excel file and file name is TestResult.xls
		Set<String> keyset = testresultdata.keySet();
		int rownum = 0;
		for (String key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object[] objArr = testresultdata.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof Date)
					cell.setCellValue((Date) obj);
				else if (obj instanceof Boolean)
					cell.setCellValue((Boolean) obj);
				else if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Double)
					cell.setCellValue((Double) obj);
			}
		}
		try {
			FileOutputStream out = new FileOutputStream(new File("WriteTestCaseResultToExcel.xls"));
			workbook.write(out);
			out.close();
			System.out.println("Excel written successfully..");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// close the browser
		browser.close();
	}
}
