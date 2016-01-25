package sampleCode;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class Annotations {
	WebDriver driver;
	String url ="";
	
	/* Annotations
	 * priority=1: this test case will run first
	 * dataProvider="dp": get value from data provider has name "dp"
	 * timeOut=5000: if test case doesn't work within that time, force stop and failed.
	 */
	
	@Test(priority=1, dataProvider = "dp", timeOut=5000)
	public void loginDataProvider(String n, String s) {
		//
	}
	
	@Test(priority=2, dataProvider = "dp")
	public void registerDataProvider(String n, String s){
		
	}
	
	@BeforeMethod
	public void beforeMethod() {
		//Before run test case
	}

	@AfterMethod
	public void afterMethod() {
		//After run test case
	}

	@DataProvider
	/*
	 * first object value is "String n", second object value is "String s"
	 */
	public Object[][] dp() {
		return new Object[][] {
			new Object[] { "name1", "12345" },
			new Object[] { "name2", "1234" }, 
		};
	}

	@BeforeClass
	public void beforeClass() {
		//Before run all test cases in class
	}

	@AfterClass
	public void afterClass() {
		//After run all test cases in class
	}

	@BeforeTest
		@Parameters("browser")
	public void beforeTest(String browser) {
		//Before run test cases in all classes
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
		//After run test cases in all classes
	}
}
/* Configure XML file
 * thread-count = 2: run 2 thread same time
 * parallel = tests: all <test> will run the same time
 * set 2 class in <classes>: run multiple class
 * preserver-order = true: order the priority of classes (CrossBrowserOne run first)
 * include name = testCaseOne: run this test case
 * exclude name = testCaseThree: don't run this test case
 * 
<suite name="CrossBrowserSuite" thread-count="2" parallel="tests">
	<test name="ChromeTest" preserver-order="true">
		<parameter name="browser" value="Chrome" />
			<classes>
				<class name="sampleCode.CrossBrowserOne">
					<methods>
						<include name = "testCaseOne"/>
						<include name = "testCaseTwo"/>
						<exclude name = "testCaseThree"/>
					</method>
				</class>
				<class name="sampleCode.CrossBrowserTwo">
				</class>
			</classes>
	</test>
	<test name="FirefoxTest">
		<parameter name="browser" value="Firefox" />
			<classes>
				<class name="sampleCode.CrossBrowser">
				</class>
			</classes>
	</test>
</suite>
 */

