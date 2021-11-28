package org.letcode.pages;

import org.letcode.seleniumBase.LetCodeBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

public class LoginPage extends LetCodeBase{

	public LoginPage(RemoteWebDriver driver) {
		this.driver  = driver;

	}

	//	RemoteWebDriver driver = null;
	// locators
	public boolean getUsernameLabel(){
		//driver.switchTo().frame(0);
		// wait
		//return driver.findElement(By.xpath("//label[@for='user_name']")).isDisplayed();
		return driver.findElement(By.name("email")).isDisplayed();
	}
	public boolean getPasswordLabel(){
		//return driver.findElement(By.xpath("//label[@for='user_password']")).isDisplayed();
		return driver.findElement(By.name("password")).isDisplayed();
	}
	/*
	 * public boolean getLanguageLabel(){ return
	 * driver.findElement(By.xpath("//label[@for='language_select']")).isDisplayed()
	 * ; }
	 */

	// actions
	/**
	 * @description - get the data from user and type on the user name field
	 * @param username -  pass the user to be login
	 * @return 
	 */
	public LoginPage enterUserName(String username){
		driver.findElement(By.name("email")).sendKeys(username);
		return this;
	}
	public LoginPage enterUserPassword(String password){
		driver.findElement(By.name("password")).sendKeys(password);
		return this;
	}

	/*
	 * public LoginPage selectLanguage(String lang) { WebElement languageDD =
	 * driver.findElement(By.id("language_select")); new
	 * Select(languageDD).selectByVisibleText(lang); return this; }
	 */
	
	public DashBoardPage clickLogin() {
		driver.findElement(By.xpath("//*[@type='submit'][1]")).click();
		return new DashBoardPage();
	}
	public ForgotPassWordPage  clickForgotPassword(){
		driver.findElement(By.linkText("Forgot Password ?")).click();
		return new ForgotPassWordPage();
	}
	/**
	 * @description this function used to login into the service now application
	 * @param username
	 * @param pass
	 */
	public void login(String username, String pass) {
		//driver.switchTo().frame(0);
		enterUserName(username);
		enterUserPassword(pass);
		clickLogin();
	}

}
