package data_Provider;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExcelDataProvider {
	static WebDriver driver;

	@BeforeTest
	public void browserSetup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test(dataProvider = "testdata")
	public static void test1(String username, String password) {

		driver.get("https://www.facebook.com/");

		WebElement user = driver.findElement(By.id("email"));
		user.sendKeys(username);
		WebElement pass = driver.findElement(By.name("pass"));
		pass.sendKeys(password);
		WebElement login = driver.findElement(By.name("login"));
		login.click();
	}

	@DataProvider(name = "testdata")
	public Object[][] getData() throws IOException {
		Object data[][] = testdata("C:\\Users\\MY COMPUTER\\eclipse-workspace\\DataProvider\\Excel\\DataProviderr.xlsx",
				"Sheet1");
		return data;

	}

	private Object[][] testdata(String path, String Sname) throws IOException {
		ExcelUtils excel = new ExcelUtils(path, Sname);
		int rowCount = excel.getRowCount();
		int columnuCount = excel.getColumnuCount();
		
		Object[][] data = new Object[rowCount - 1][columnuCount];

		for (int i = 1; i < rowCount; i++) {

			for (int j = 0; j < columnuCount; j++) {
				String cellData = excel.getCellDataString(i, j);
				data[i - 1][j] = cellData;
			}

		}
		return data;

	}

}
