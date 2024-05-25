package PageObjects;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage {
	private WebDriver driver;
	WebDriverWait wait;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		 this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public void HomePageNavitems(String t) {
	WebElement  ele =driver.findElement(By.xpath("//ul[@class='navbar-nav ml-auto']/child::li/child::a[text()='"+t+"']"));
	ele.click();
	}
	
	
	public void setLoginCred(String id,String cred) {
		WebElement input = driver.findElement(By.xpath("//input[@id='" + id + "']"));
		input.clear();
		input.sendKeys(cred);
	}
	
	public void ClickBtn(String bt) {
		WebElement  btn =driver.findElement(By.xpath("//button[text()='"+ bt +"']"));
		btn.click();
	}
	
	public WebElement Userprofile() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='nameofuser']")));
        return driver.findElement(By.xpath("//a[@id='nameofuser']"));
    }
	
	public boolean isErrorMessageDisplayed() {
        try {
            
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());
		// Switch to the alert
        Alert alert = driver.switchTo().alert();
            
            // Get the text from the alert
            String alertText = alert.getText();
           
//             Check if the alert text contains the expected error message
            boolean isErrorMessageDisplayed = alertText.contains("Wrong password.") || alertText.contains("User does not exist.");
            
            // Dismiss the alert
            alert.accept();
            return isErrorMessageDisplayed;
//            
        } catch (NoAlertPresentException e) {
//            // If no alert is present, return false
            return false;
        }
    }

	
	
}