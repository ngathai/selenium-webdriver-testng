package sampleCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
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

public class GetAllLinksAndCheckBroken {
	private WebDriver browser; // define an Excel Work Book
	HSSFWorkbook workbook; // define an Excel Work sheet
	HSSFSheet sheet; // define a test result data object
	private int statusCode;
	Map<String, Object[]> testresultdata;

	@Test(priority = 1, description = "Get All Links")
	public void getAllLinksAndCheckBroken() throws InterruptedException, IOException {
		browser.manage().window().maximize();
		browser.get("http://tlcDev:TLC$2016$ev@qa.theluxurycloset.com:8080/lykan/web/");
		List<WebElement> links = browser.findElements(By.tagName("a"));
		for (int i = 0; i < links.size(); i++) {
			String linkText = links.get(i).getText();
			String href = links.get(i).getAttribute("href");
			if (!(links.get(i).getAttribute("href") == null) && !(links.get(i).getAttribute("href").equals(""))) {

				if (links.get(i).getAttribute("href").contains("http")) {
					// Find HTTP Status-Code
					statusCode = getResponseCode(links.get(i).getAttribute("href").trim());
					// Check broken link
					if (statusCode == 404) {
						System.out.println("Broken link: " + i + " " + links.get(i).getAttribute("href") + ": 404");
						testresultdata.put(" "+i, new Object[] { linkText, href, statusCode});
					}
				}
			}
			testresultdata.put(" "+i, new Object[] { linkText, href, statusCode});
		}
	}

	/*
	 * Create Excel sheet to save results
	 */
	@BeforeClass(alwaysRun = true)
	public void setupBeforeSuite(ITestContext context) {
		workbook = new HSSFWorkbook();
		sheet = workbook.createSheet("All Links");
		testresultdata = new LinkedHashMap<String, Object[]>();
		testresultdata.put("1", new Object[] { "Link Text", "URLs", "Status Code" });

		try {

			browser = new FirefoxDriver();
			browser.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			throw new IllegalStateException("Can't start Web Driver", e);
		}

	}
	
	public static int getResponseCode(String urlString) throws IOException {
		URL u = new URL(urlString);
		HttpURLConnection huc = (HttpURLConnection) u.openConnection();
		huc.setRequestMethod("GET");
		huc.connect();
		return huc.getResponseCode();
	}

	/*
	 * Create Excel file to save result
	 */
	@AfterClass
	public void setupAfterSuite() {
		// write excel file and file name is GetAllLinks.xls
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
			FileOutputStream out = new FileOutputStream(new File("GetAllLinks.xls"));
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
