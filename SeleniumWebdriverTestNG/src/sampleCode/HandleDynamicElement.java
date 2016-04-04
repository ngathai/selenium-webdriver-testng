package sampleCode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HandleDynamicElement {
	WebDriver driver = new FirefoxDriver();
	String url = "http://only-testing-blog.blogspot.in/";
/*
	@Test
	public void handleDynamicElement() {
		//Solution 1: Using absolute Xpath
		WebElement element = driver.findElement(By.xpath("html/body/div[30]/div[2]/div[2]/div/div/div/div[1]/table/tbody/tr/td[2]/table/tbody/tr/td[1]/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/em/button"));
		//Solution 2: Identify element by starting text
		WebElement element2 = driver.findElement(By.xpath("//button[starts-with(@id, 'Submit-')]"));
		//Solution 3: Identify element by contains text
		WebElement element3 = driver.findElement(By.xpath("//input[contains(@class, 'suggest')]"));
		//Solution 4: Identify element by index
		WebElement element4 = driver.findElements(By.xpath("//*submit")).get(0);
		//Solution 5: Identify Element by stable preceding text
		WebElement element5 = driver.findElement(By.xpath("//*[@id='post-body-5701624569290716001']/div[1]/label/following::input"));
		
	}
*/
	
	@Test
	public void handleDynamicElement(){
		//This is Xpath of label before the input field: //*[@id='post-body-5701624569290716001']/div[1]/label
		// Focus on input field using: /following::input
		WebElement element5 = driver.findElement(By.xpath("//*[@id='post-body-5701624569290716001']/div[1]/label/following::input"));
		element5.sendKeys("Text");
	}

	@BeforeTest
	public void beforeTest() {
		driver.manage().window().maximize();
		driver.get(url);
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}

}
