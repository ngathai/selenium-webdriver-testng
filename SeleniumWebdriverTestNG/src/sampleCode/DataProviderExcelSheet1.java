package sampleCode;

import java.io.File;
import java.io.IOException;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/*
 * This class is used for read data provider from excel file.
 * Note: file excel must save as format: Microsoft excel 97...
 * 
 * 1. Download and add library jxl
 */

public class DataProviderExcelSheet1 {
	WebDriver driver = new FirefoxDriver();
	String url = "http://uat2.theluxurycloset.com/lykan/web";

	@BeforeTest
	public void beforeTest() {
		driver.get(url);
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}

	@Test(dataProvider = "testDataProviderExcel")
	public void testExcel(String email, String pass) throws InterruptedException {

		// System.out.println("The value of username: " + username);
		// System.out.println("The value of password: " + password);

		WebElement btnLoginHeader = driver.findElement(By.className("login-link"));
		btnLoginHeader.click();
		Thread.sleep(5000);
		WebElement txtEmail = driver.findElement(By.className("user-email"));
		txtEmail.clear();
		txtEmail.sendKeys(email);
		WebElement txtPass = driver.findElement(By.className("user-pwd"));
		txtPass.clear();
		txtPass.sendKeys(pass);
		WebElement btnLogin = driver.findElement(By.id("login_btn"));
		btnLogin.click();
		Thread.sleep(6000);
		WebElement myacc = driver.findElement(By.className("My-account"));
		String expected = "MY ACCOUNT";
		String actual = myacc.getText();
		Assert.assertEquals(actual, expected);

	}

	@DataProvider(name = "testDataProviderExcel")
	public String[][] readExcel() {
		File file = new File("testProviderExcel.xls");
		String inputData[][] = null;
		Workbook w;

		try {
			w = Workbook.getWorkbook(file);
			Sheet sheet = w.getSheet(0);
			int colcount = sheet.getColumns();
			int rowcount = sheet.getRows();
			inputData = new String[rowcount][colcount];
			for (int i = 0; i < rowcount; i++) {
				for (int j = 0; j < colcount; j++) {
					Cell cell = sheet.getCell(j, i);
					inputData[i][j] = cell.getContents();
				}
			}
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return inputData;
	}
}