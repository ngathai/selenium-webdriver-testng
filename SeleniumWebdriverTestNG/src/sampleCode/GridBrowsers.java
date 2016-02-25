/*
 * HOW TO RUN THE SELENIUM GRID?
 * 1. Download selenium-server-standalone-2.52.0.jar, and all browser drivers.
 * 2. Terminal: java -jar -jar selenium-server-standalone-2.52.0.jar -role hub
 * 3. Open new terminal to register Firefox driver: 
 * 		java -jar selenium-server-standalone-2.52.0.jar -role webdriver -hub http://192.168.10.224/grid/register -browser browserName="firefox",platform=LINUX -port 5566
 * 4. Open new terminal to register Chrome driver:
 * 		java -jar selenium-server-standalone-2.52.0.jar -role webdriver -hub http://192.168.10.224:4444/grid/register -browser browserName="chrome",platform=LINUX -Dwebdriver.chrome.driver=chromedriver -port 5567
 * Same command for others driver.
 * 192.168.10.224 is IP address.
 */


package sampleCode;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GridBrowsers {
	WebDriver driver;
	String bareURL = "http://newtours.demoaut.com/";
	String nodeFirefox = "http://192.168.10.224:5568/wd/hub";
	String nodeChrome = "http://192.168.10.224:5567/wd/hub";

	@Test
	public void gridBrowsers() {
		driver.get(bareURL);
		Assert.assertEquals("Welcome: Mercury Tours", driver.getTitle());
	}

	@Parameters("browser")
	@BeforeTest
	public void beforeTest(String browser) throws MalformedURLException {
		if(browser.equalsIgnoreCase("firefox")){
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setBrowserName("firefox");
			cap.setPlatform(Platform.LINUX);
			driver = new RemoteWebDriver(new URL(nodeFirefox), cap);
		}else if(browser.equalsIgnoreCase("chrome")){
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setBrowserName("chrome");
			cap.setPlatform(Platform.LINUX);
			driver = new RemoteWebDriver(new URL(nodeChrome), cap);
		}
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}

}
