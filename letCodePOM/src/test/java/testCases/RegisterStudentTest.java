package testCases;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import utilities.Constants;
import utilities.ExcelUtils;
import java.io.IOException;
//import org.apache.log4j.Logger;
//import org.apache.log4j.xml.DOMConfigurator;
//import org.apache.logging.slf4j.Log4jLoggerFactory;


public class RegisterStudentTest {

	//creating object of ExcelUtils class
	static ExcelUtils excelUtils = new ExcelUtils();

	//using the Constants class values for excel file path 
	static String excelFilePath =Constants.Path_TestData+Constants.File_TestData;

	//private static Logger Log = Logger.getLogger(RegisterStudentTest.class.getName());

	@Test
	public void dataDiven() throws IOException {

		//Logger logger = Logger.getLogger(RegisterStudentTest.class);
		//logger.info("as");

		//DOMConfigurator.configure("log4j.xml");

		//set the Chrome Driver path
		//System.setProperty("webdriver.chrome.driver","E:\\Selenium Automation\\Dependency Jar Files\\Selenium Driver Packages\\Chrome\\chromedriver.exe");
		//Creating an object of ChromeDriver
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();

		//launching the specified URL
		driver.get("https://demoqa.com/automation-practice-form");

		//Log.info("Launch URL");

		//Identify the WebElements for the student registration form
		WebElement firstName=driver.findElement(By.id("firstName"));
		WebElement lastName=driver.findElement(By.id("lastName"));
		WebElement email=driver.findElement(By.id("userEmail"));
		WebElement genderMale= driver.findElement(By.id("gender-radio-1"));
		WebElement mobile=driver.findElement(By.id("userNumber"));
		WebElement address=driver.findElement(By.id("currentAddress"));
		WebElement submitBtn=driver.findElement(By.id("submit"));

		//calling the ExcelUtils class method to initialise the workbook and sheet
		excelUtils.setExcelFile(excelFilePath,"STUDENT_DATA");

		//iterate over all the row to print the data present in each cell.
		for(int i=1;i<=excelUtils.getRowCountInSheet();i++)
		{
			//Enter the values read from Excel in firstname,lastname,mobile,email,address
			firstName.sendKeys(excelUtils.getCellData(i,0));
			lastName.sendKeys(excelUtils.getCellData(i,1));
			email.sendKeys(excelUtils.getCellData(i,2));
			mobile.sendKeys(excelUtils.getCellData(i,3));
			address.sendKeys(excelUtils.getCellData(i,4));

			//Click on the gender radio button using javascript
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", genderMale);

			//Close the Google Ads
			driver.findElement(By.id("close-fixedban")).click(); //Close Ad

			//Scroll into bottom of the page
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)", "");

			//Click on submit button
			submitBtn.click();

			//Verify the confirmation message
			WebElement confirmationMessage = driver.findElement(By.xpath("//div[text()='Thanks for submitting the form']"));

			//check if confirmation message is displayed
			if (confirmationMessage.isDisplayed()) {
				// if the message is displayed , write PASS in the excel sheet using the method of ExcelUtils
				excelUtils.setCellValue(i,6,"PASS",excelFilePath);
			} else {
				//if the message is not displayed , write FAIL in the excel sheet using the method of ExcelUtils
				excelUtils.setCellValue(i,6,"FAIL",excelFilePath);
			}

			//close the confirmation popup
			WebElement closebtn=driver.findElement(By.id("closeLargeModal"));
			closebtn.click();

			//wait for page to come back to registration page after close button is clicked
			//driver.manage().timeouts().implicitlyWait(2000,TimeUnit.SECONDS);
		}
		//closing the driver
		driver.quit();
	}
}