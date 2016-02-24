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

public class GetAllImagesAndCheckBroken {

	private WebDriver driver;
	private int statusCode;
	HSSFWorkbook workbook; // define an Excel Work sheet
	HSSFSheet sheet; // define a test result data object
	Map<String, Object[]> testresultdata;

	@BeforeClass(alwaysRun = true)
	public void setupBeforeSuite(ITestContext context) {
		workbook = new HSSFWorkbook();
		sheet = workbook.createSheet("All Links");
		testresultdata = new LinkedHashMap<String, Object[]>();
		testresultdata.put("1", new Object[] {"URLs", "Status Code" });

		try {

			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			throw new IllegalStateException("Can't start Web Driver", e);
		}

	}

	@Test
//	public void validateInvalidImages() {
//		driver.get("http://tlcDev:TLC$2016$ev@qa.theluxurycloset.com:8080/lykan/web/");
//		try {
//			List<WebElement> imagesList = driver.findElements(By.tagName("img"));
//			System.out.println("Total no. of images are " + imagesList.size());
//			for (WebElement imgElement : imagesList) {
//				if (imgElement != null) {
//					verifyimageActive(imgElement);
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//		}
//	}
	
	public void getAllImagesLinks() throws InterruptedException, IOException {
		driver.manage().window().maximize();
		driver.get("http://tlcDev:TLC$2016$ev@qa.theluxurycloset.com:8080/lykan/web/");
		List<WebElement> links = driver.findElements(By.tagName("img"));
		for (int i = 0; i < links.size(); i++) {
			String src = links.get(i).getAttribute("src");
			if (!(links.get(i).getAttribute("src") == null) && !(links.get(i).getAttribute("src").equals(""))) {

				if (links.get(i).getAttribute("src").contains("http")) {
					// Find HTTP Status-Code
					statusCode = getResponseCode(links.get(i).getAttribute("src").trim());
					// Check broken link
					if (statusCode != 200) {
						System.out.println("Broken link: "+ links.get(i).getAttribute("src") + ": Not found");
						testresultdata.put(" ", new Object[] { src, "Not found"});
					}
				}
			}
			testresultdata.put(" "+i, new Object[] {src, statusCode});
		}
	}
	public static int getResponseCode(String urlString) throws IOException {
		URL u = new URL(urlString);
		HttpURLConnection huc = (HttpURLConnection) u.openConnection();
		huc.setRequestMethod("GET");
		huc.connect();
		return huc.getResponseCode();
	}
//	public int verifyimageActive(WebElement imgElement) throws ClientProtocolException, IOException {
//			HttpClient client = HttpClientBuilder.create().build();
//			HttpGet request = new HttpGet(imgElement.getAttribute("src"));
//			HttpResponse response = client.execute(request);
//			response.getStatusLine().getStatusCode();
//			return response.getStatusLine().getStatusCode();
//	}

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
			FileOutputStream out = new FileOutputStream(new File("CheckImages.xls"));
			workbook.write(out);
			out.close();
			System.out.println("Excel written successfully..");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// close the browser
		driver.close();
	}
}
