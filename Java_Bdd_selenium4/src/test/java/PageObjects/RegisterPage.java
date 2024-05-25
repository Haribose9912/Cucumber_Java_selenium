package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class RegisterPage {
	
	WebDriver driver;
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		
	}
	
	By signup_btn = By.xpath("//button[text()='Sign up']");
	
	public void click_signup_Btn() {
		driver.findElement(signup_btn).click();
	}
	
	
	
	
}
