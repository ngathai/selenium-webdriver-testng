package sampleCode;

import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class DataProviderExcelSheet2 {

	WebDriver driver;
	Workbook wb;
	Sheet sh1;
	int numrow;
	String username;
	String password;

	@BeforeTest
	public void Setup()

	{
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://uat2.theluxurycloset.com/lykan/web");
	}

	@Test(dataProvider = "testdata")
	public void TestFireFox(String email, String pass) throws InterruptedException

	{
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

	@DataProvider(name = "testdata")
	public Object[][] TestDataFeed() {

		try {
			wb = Workbook.getWorkbook(new File("testProviderExcel.xls"));
			sh1 = wb.getSheet(1);
			numrow = sh1.getRows();
		} catch (Exception e)

		{
			e.printStackTrace();
		}
		// Create 2 D array and pass row and columns
		Object[][] data = new Object[numrow][sh1.getColumns()];
		// This will run a loop and each iteration it will fetch new row
		for (int i = 0; i < numrow; i++) {
			// Fetch first row username
			data[i][0] = sh1.getCell(0, i).getContents();
			// Fetch first row password
			data[i][1] = sh1.getCell(1, i).getContents();
		}
		return data;
	}

	@AfterTest
	public void QuitTC() {
		driver.close();
	}

}
