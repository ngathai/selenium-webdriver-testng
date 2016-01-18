package sampleCode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DownloadFile {
	WebDriver driver;

	@BeforeTest 
	public void StartBrowser() {
		FirefoxProfile fprofile = new FirefoxProfile(); 
		//fprofile.setPreference("browser.download.dir", "D:\\WebDriverdownloads"); 
		/*
		 * browser.download.folderList = 0 means save file in Desktop
		 * browser.download.folderList = 1 means save file in somewhere, where user usually save file.
		 * browser.download.folderList = 2 means save file in Downloads
		 */
		fprofile.setPreference("browser.download.folderList", 0);
		fprofile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;"
				+ "application/pdf;"
				+ "application/vnd.openxmlformats-officedocument.wordprocessingml.document;"
				+ "text/plain;" 
				+ "text/csv");
		fprofile.setPreference( "browser.download.manager.showWhenStarting", false ); 
		fprofile.setPreference( "pdfjs.disabled", true ); 
		driver = new FirefoxDriver(fprofile); } 
	@Test public void OpenURL() throws InterruptedException{ 
		driver.get("http://only-testing-blog.blogspot.in/2014/05/login.html");
		driver.findElement(By.xpath("//a[contains(.,'Download Text File')]")).click(); 
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[contains(.,'Download PDF File')]")).click(); 
		Thread.sleep(5000); 
		driver.findElement(By.xpath("//a[contains(.,'Download CSV File')]")).click(); 
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[contains(.,'Download Excel File')]")).click(); 
		Thread.sleep(5000); 
		driver.findElement(By.xpath("//a[contains(.,'Download Doc File')]")).click(); 
		Thread.sleep(5000); 
		} 
	@AfterTest 
	public void CloseBrowser() {
		driver.quit(); 
		} 
}
