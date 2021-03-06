package assessment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Transform {
	
	public static void main(String[] args) throws InterruptedException {
		
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.saucedemo.com/");
		WebElement element = driver.findElement(By.cssSelector(".submit-button"));
		String attribute = element.getAttribute("value");
		System.out.println(attribute);
		String cssValue = element.getCssValue("text-transform");
		System.out.println(cssValue);
	}

}
