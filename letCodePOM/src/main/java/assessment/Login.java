package assessment;

import org.letcode.seleniumBase.Locators;
import org.letcode.seleniumBase.SeleniumBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Login extends SeleniumBase{
	@BeforeMethod
	void launch(){
		setUp("https://letcode.in/");
	}
	
	@AfterMethod(alwaysRun = true)
	void cleanUp() {
		quit();
	}
	
	@Test
	void login(){
		click(element(Locators.link, "Log in"));
		type(element(Locators.name, "email"), "koushik350@gmail.com");
		type(element(Locators.name, "password"), "Pass123$");
		click(element(Locators.css, "button.button.is-primary"));
		System.out.println("completed");
	}
}
