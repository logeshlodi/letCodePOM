package org.letcode.seleniumBase;

import java.time.Duration;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import utils.ReadExcel;

public class LetCodeBase{

	String URL = "https://www.phptravels.net/login";

	protected RemoteWebDriver driver = null;
	public String fileName = "";
	
	@DataProvider(name="data")
	public String[][] dataProvider() {
		String[][] excelData = ReadExcel.getExcelData(fileName);
		return excelData;
	}

	@BeforeMethod
	public void startApp() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(URL);
	}
	
	@AfterMethod
	public void closeApp() {
		driver.quit();
	}

}
