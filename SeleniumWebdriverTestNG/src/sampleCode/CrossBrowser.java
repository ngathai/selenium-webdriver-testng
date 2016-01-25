package sampleCode;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class CrossBrowser {
	WebDriver driver;
	String url = "http://uat2.theluxurycloset.com/lykan/web/";
// Config running in xml: testCrossBrowser.xml
	@BeforeTest
	@Parameters("browser")
	public void beforeTest(String browser) {
		if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "//home//enclaveit//Desktop//chromedriver");
			driver = new ChromeDriver();
		}
		driver.get(url);
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}

	@Test(dataProvider = "LoginProvider")
	public void crossBrowser(String username, String password) throws InterruptedException {
		WebElement btnLoginHeader = driver.findElement(By.className("login-link"));
		btnLoginHeader.click();
		Thread.sleep(5000);
		WebElement txtEmail = driver.findElement(By.className("user-email"));
		txtEmail.sendKeys(username);
		WebElement txtPass = driver.findElement(By.className("user-pwd"));
		txtPass.sendKeys(password);
		WebElement btnLogin = driver.findElement(By.id("login_btn"));
		btnLogin.click();
		Thread.sleep(6000);
		WebElement myacc = driver.findElement(By.className("My-account"));
		String expected = "MY ACCOUNT";
		String actual = myacc.getText();
		Assert.assertEquals(actual, expected);
	}
	 @DataProvider(name = "LoginProvider")
	  public Object[][] getDataFromDataProvider() {
	    return new Object[][] {
	      new Object[] { "thaithianhnga@gmail.com", "123456789" }
	    };
	  }
}
